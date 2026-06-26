package org.onlydevs.hibento.endpoint.rest.controller;

import lombok.RequiredArgsConstructor;

import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedSpeaker;
import org.onlydevs.hibento.service.SpeakerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

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
}
