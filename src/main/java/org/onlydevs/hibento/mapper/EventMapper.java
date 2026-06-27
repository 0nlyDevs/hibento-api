package org.onlydevs.hibento.mapper;

import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedEvent;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedEvent;
import org.onlydevs.hibento.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

  public CreatedEvent toCreatedEvent(Event event) {
    return new CreatedEvent(
        event.getId(),
        event.getTitle(),
        event.getDescription(),
        event.isOnline(),
        event.getStartDate(),
        event.getEndDate(),
        event.getVenue() != null ? event.getVenue().getId() : null,
        event.getCreatedAt(),
        event.getUpdatedAt());
  }

  public UpdatedEvent toUpdatedEvent(Event event) {
    return new UpdatedEvent(
        event.getId(),
        event.getTitle(),
        event.getDescription(),
        event.isOnline(),
        event.getStartDate(),
        event.getEndDate(),
        event.getVenue() != null ? event.getVenue().getId() : null,
        event.getCreatedAt(),
        event.getUpdatedAt());
  }
}
