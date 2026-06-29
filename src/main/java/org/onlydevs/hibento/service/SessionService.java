package org.onlydevs.hibento.service;

import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.NotFoundException;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateSession;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateSession;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedSession;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedSession;
import org.onlydevs.hibento.mapper.SessionMapper;
import org.onlydevs.hibento.model.Event;
import org.onlydevs.hibento.model.EventSession;
import org.onlydevs.hibento.model.EventSessionSpeaker;
import org.onlydevs.hibento.model.Room;
import org.onlydevs.hibento.model.Speaker;
import org.onlydevs.hibento.model.exception.BadRequestException;
import org.onlydevs.hibento.model.exception.ConflictException;
import org.onlydevs.hibento.repository.EventRepository;
import org.onlydevs.hibento.repository.RoomRepository;
import org.onlydevs.hibento.repository.SessionRepository;
import org.onlydevs.hibento.repository.SpeakerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;
  private final EventRepository eventRepository;
  private final RoomRepository roomRepository;
  private final SpeakerRepository speakerRepository;
  private final SessionMapper sessionMapper;

  @Transactional
  public CreatedSession createSession(UUID eventId, CreateSession request) {
    if (request.getStartTime().isAfter(request.getEndTime())) {
      throw new BadRequestException("Start time must be before end time");
    }

    Event event =
        eventRepository
            .findById(eventId)
            .orElseThrow(() -> new NotFoundException("Event not found"));

    Room room = null;
    if (request.getRoomId() != null) {
      room =
          roomRepository
              .findById(request.getRoomId())
              .orElseThrow(() -> new NotFoundException("Room not found"));

      if (sessionRepository.existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
          request.getRoomId(), request.getEndTime(), request.getStartTime())) {
        throw new ConflictException("Room already has a session during this time");
      }
    }

    EventSession session = new EventSession();
    session.setTitle(request.getTitle());
    session.setDescription(request.getDescription());
    session.setStartTime(request.getStartTime());
    session.setEndTime(request.getEndTime());
    session.setCapacity(request.getCapacity());
    session.setEvent(event);
    session.setRoom(room);

    if (request.getSpeakerIds() != null) {
      var speakers =
          request.getSpeakerIds().stream()
              .map(
                  speakerId -> {
                    Speaker speaker =
                        speakerRepository
                            .findById(speakerId)
                            .orElseThrow(
                                () -> new NotFoundException("Speaker not found: " + speakerId));
                    EventSessionSpeaker ess = new EventSessionSpeaker();
                    ess.setEventSession(session);
                    ess.setSpeaker(speaker);
                    return ess;
                  })
              .toList();
      for (var ess : speakers) {
        if (sessionRepository.existsBySpeakerIdAndOverlappingTime(
            ess.getSpeaker().getId(), request.getStartTime(), request.getEndTime())) {
          throw new ConflictException(
              "Speaker "
                  + ess.getSpeaker().getName()
                  + " is already assigned to another session during this time");
        }
      }
      session.setEventSessionSpeakers(speakers);
    }

    EventSession savedSession = sessionRepository.save(session);
    return sessionMapper.toCreatedSession(savedSession);
  }

  @Transactional
  public UpdatedSession updateSession(UUID id, UpdateSession request) {
    EventSession session =
        sessionRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Session not found"));

    if (request.getStartTime().isAfter(request.getEndTime())) {
      throw new BadRequestException("Start time must be before end time");
    }

    Room room = session.getRoom();
    if (request.getRoomId() != null) {
      room =
          roomRepository
              .findById(request.getRoomId())
              .orElseThrow(() -> new NotFoundException("Room not found"));

      if (sessionRepository
          .existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndIdNot(
              request.getRoomId(), request.getEndTime(), request.getStartTime(), id)) {
        throw new ConflictException("Room already has a session during this time");
      }
    }

    if (request.getSpeakerIds() != null) {
      var speakers =
          request.getSpeakerIds().stream()
              .map(
                  speakerId -> {
                    Speaker speaker =
                        speakerRepository
                            .findById(speakerId)
                            .orElseThrow(
                                () -> new NotFoundException("Speaker not found: " + speakerId));
                    EventSessionSpeaker ess = new EventSessionSpeaker();
                    ess.setEventSession(session);
                    ess.setSpeaker(speaker);
                    return ess;
                  })
              .toList();
      for (var ess : speakers) {
        if (sessionRepository.existsBySpeakerIdAndOverlappingTimeAndIdNot(
            ess.getSpeaker().getId(), request.getStartTime(), request.getEndTime(), id)) {
          throw new ConflictException(
              "Speaker "
                  + ess.getSpeaker().getName()
                  + " is already assigned to another session during this time");
        }
      }
    }

    session.setTitle(request.getTitle());
    session.setDescription(request.getDescription());
    session.setStartTime(request.getStartTime());
    session.setEndTime(request.getEndTime());
    session.setCapacity(request.getCapacity());
    session.setRoom(room);

    if (request.getSpeakerIds() != null) {
      var currentSpeakerIds =
          session.getEventSessionSpeakers().stream()
              .map(ess -> ess.getSpeaker().getId())
              .collect(Collectors.toSet());
      if (!currentSpeakerIds.equals(new HashSet<>(request.getSpeakerIds()))) {
        session.getEventSessionSpeakers().clear();
        sessionRepository.flush();
        var newSpeakers =
            request.getSpeakerIds().stream()
                .map(
                    speakerId -> {
                      Speaker speaker =
                          speakerRepository
                              .findById(speakerId)
                              .orElseThrow(
                                  () -> new NotFoundException("Speaker not found: " + speakerId));
                      EventSessionSpeaker ess = new EventSessionSpeaker();
                      ess.setEventSession(session);
                      ess.setSpeaker(speaker);
                      return ess;
                    })
                .toList();
        session.getEventSessionSpeakers().addAll(newSpeakers);
      }
    }

    EventSession updatedSession = sessionRepository.save(session);
    return sessionMapper.toUpdatedSession(updatedSession);
  }

  @Transactional
  public void deleteSession(UUID id) {
    if (!sessionRepository.existsById(id)) {
      throw new NotFoundException("Session not found");
    }
    sessionRepository.deleteById(id);
  }
}
