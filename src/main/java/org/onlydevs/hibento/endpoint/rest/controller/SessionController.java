package org.onlydevs.hibento.endpoint.rest.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateSession;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateSession;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedSession;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedSession;
import org.onlydevs.hibento.service.SessionService;
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
@RequiredArgsConstructor
public class SessionController {

  private final SessionService sessionService;

  @PostMapping("/admin/events/{eventId}/sessions")
  public ResponseEntity<CreatedSession> createSession(
      @PathVariable UUID eventId, @Valid @RequestBody CreateSession request) {
    CreatedSession saved = sessionService.createSession(eventId, request);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @PutMapping("/admin/sessions/{id}")
  public ResponseEntity<UpdatedSession> updateSession(
      @PathVariable UUID id, @Valid @RequestBody UpdateSession request) {
    return ResponseEntity.status(HttpStatus.OK).body(sessionService.updateSession(id, request));
  }

  @DeleteMapping("/admin/sessions/{id}")
  public ResponseEntity<Void> deleteSession(@PathVariable UUID id) {
    sessionService.deleteSession(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
