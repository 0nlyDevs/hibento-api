package org.onlydevs.hibento.mapper;

import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedSession;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedSession;
import org.onlydevs.hibento.model.EventSession;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

  public CreatedSession toCreatedSession(EventSession session) {
    return new CreatedSession(
        session.getId(),
        session.getEvent().getId(),
        session.getTitle(),
        session.getDescription(),
        session.getStartTime(),
        session.getEndTime(),
        session.getRoom().getId(),
        session.getRoom().getName(),
        session.getCapacity(),
        session.getEventSessionSpeakers().stream().map(ess -> ess.getSpeaker().getId()).toList(),
        session.getCreatedAt(),
        session.getUpdatedAt());
  }

  public UpdatedSession toUpdatedSession(EventSession session) {
    return new UpdatedSession(
        session.getId(),
        session.getEvent().getId(),
        session.getTitle(),
        session.getDescription(),
        session.getStartTime(),
        session.getEndTime(),
        session.getRoom().getId(),
        session.getRoom().getName(),
        session.getCapacity(),
        session.getEventSessionSpeakers().stream().map(ess -> ess.getSpeaker().getId()).toList(),
        session.getCreatedAt(),
        session.getUpdatedAt());
  }
}
