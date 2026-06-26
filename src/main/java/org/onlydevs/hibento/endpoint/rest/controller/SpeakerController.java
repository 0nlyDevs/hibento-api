package org.onlydevs.hibento.endpoint.rest.controller;

import org.onlydevs.hibento.endpoint.rest.controller.dto.PaginatedResponse;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.SpeakerSummary;
import org.onlydevs.hibento.service.SpeakerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/speakers")
@RequiredArgsConstructor
public class SpeakerController {
  private final SpeakerService speakerService;

  @GetMapping
  public ResponseEntity<PaginatedResponse<SpeakerSummary>> getAllSpeaker(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "20") Integer limit,
      @RequestParam(defaultValue = "name") String sortBy,
      @RequestParam(defaultValue = "asc") String sortDir) {

    Sort sort = sortDir.equalsIgnoreCase("desc")
        ? Sort.by(sortBy).descending()
        : Sort.by(sortBy).ascending();

    var pageable = PageRequest.of(page - 1, limit, sort);

    return ResponseEntity.status(HttpStatus.OK)
        .body(speakerService.getAllSpeaker(pageable));
  }
}
