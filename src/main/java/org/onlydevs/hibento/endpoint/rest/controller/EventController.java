package org.onlydevs.hibento.endpoint.rest.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateEvent;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateEvent;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedEvent;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedEvent;
import org.onlydevs.hibento.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;

  @PostMapping
  public ResponseEntity<CreatedEvent> createEvent(@Valid @RequestBody CreateEvent request) {
    CreatedEvent saved = eventService.createEvent(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdatedEvent> updateEvent(
      @PathVariable UUID id, @Valid @RequestBody UpdateEvent request) {
    return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEvent(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
    eventService.deleteEvent(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
