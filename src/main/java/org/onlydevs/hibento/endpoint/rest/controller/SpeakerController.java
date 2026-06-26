package org.onlydevs.hibento.endpoint.rest.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedSpeaker;
import org.onlydevs.hibento.service.SpeakerService;
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
@RequestMapping("/admin/speakers")
@RequiredArgsConstructor
public class SpeakerController {
  private final SpeakerService speakerService;

  @PostMapping
  public ResponseEntity<CreatedSpeaker> createSpeaker(@Valid @RequestBody CreateSpeaker request) {
    CreatedSpeaker saved = speakerService.createSpeaker(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdatedSpeaker> updateSpeaker(
      @PathVariable UUID id, @Valid @RequestBody UpdateSpeaker request) {
    return ResponseEntity.status(HttpStatus.OK).body(speakerService.updateSpeaker(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSpeaker(@PathVariable UUID id) {
    speakerService.deleteSpeaker(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
