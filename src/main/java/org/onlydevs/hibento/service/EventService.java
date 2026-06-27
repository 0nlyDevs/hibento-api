package org.onlydevs.hibento.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
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

    if (request.getStartDate().isAfter(request.getEndDate())) {
      throw new BadRequestException("Start date must be before end date");
    }

    if (!request.isOnline() && request.getVenueId() == null) {
      throw new BadRequestException("Venue is required for in-person events");
    }

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
    } else {
      event.setVenue(null);
    }

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
