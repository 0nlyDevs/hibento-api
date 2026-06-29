package org.onlydevs.hibento.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.onlydevs.hibento.endpoint.rest.controller.NotFoundException;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateEvent;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateEvent;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedEvent;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedEvent;
import org.onlydevs.hibento.mapper.EventMapper;
import org.onlydevs.hibento.model.Event;
import org.onlydevs.hibento.model.Venue;
import org.onlydevs.hibento.model.exception.BadRequestException;
import org.onlydevs.hibento.repository.EventRepository;
import org.onlydevs.hibento.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class EventService {

  private final EventRepository eventRepository;
  private final VenueRepository venueRepository;
  private final EventMapper eventMapper;

  @Transactional
  public CreatedEvent createEvent(CreateEvent request) {
    if (request.getStartDate().isAfter(request.getEndDate())) {
      throw new BadRequestException("Start date must be before end date");
    }

    if (!request.isOnline() && request.getVenueId() == null) {
      throw new BadRequestException("Venue is required for in-person events");
    }

    Event event = new Event();
    event.setTitle(request.getTitle());
    event.setDescription(request.getDescription());
    event.setOnline(request.isOnline());
    event.setStartDate(request.getStartDate());
    event.setEndDate(request.getEndDate());

    if (request.getVenueId() != null) {
      Venue venue =
          venueRepository
              .findById(request.getVenueId())
              .orElseThrow(() -> new NotFoundException("Venue not found"));
      event.setVenue(venue);
    }

    Event savedEvent = eventRepository.save(event);
    return eventMapper.toCreatedEvent(savedEvent);
  }

  @Transactional
  public UpdatedEvent updateEvent(UUID id, UpdateEvent request) {
    Event event =
        eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));

    if (event.isOnline() != request.isOnline()) {
      throw new BadRequestException("Cannot change event online status after creation");
    }

    UUID currentVenueId = event.getVenue() != null ? event.getVenue().getId() : null;
    if (!java.util.Objects.equals(currentVenueId, request.getVenueId())) {
      throw new BadRequestException("Cannot change event venue after creation");
    }

    if (!event.getStartDate().equals(request.getStartDate())) {
      throw new BadRequestException("Cannot change event start date after creation");
    }

    if (!event.getEndDate().equals(request.getEndDate())) {
      throw new BadRequestException("Cannot change event end date after creation");
    }

    if (request.getStartDate().isAfter(request.getEndDate())) {
      throw new BadRequestException("Start date must be before end date");
    }

    event.setTitle(request.getTitle());
    event.setDescription(request.getDescription());

    Event updatedEvent = eventRepository.save(event);
    return eventMapper.toUpdatedEvent(updatedEvent);
  }

  @Transactional
  public void deleteEvent(UUID id) {
    if (!eventRepository.existsById(id)) {
      throw new NotFoundException("Event not found");
    }
    eventRepository.deleteById(id);
  }
}
