package org.onlydevs.hibento.endpoint.rest.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateVenue;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateVenue;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedVenue;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedVenue;
import org.onlydevs.hibento.service.VenueService;
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
@RequestMapping("/admin/venues")
@RequiredArgsConstructor
public class VenueController {

  private final VenueService venueService;

  @PostMapping
  public ResponseEntity<CreatedVenue> createVenue(@Valid @RequestBody CreateVenue request) {
    CreatedVenue saved = venueService.createVenue(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdatedVenue> updateVenue(
      @PathVariable UUID id, @Valid @RequestBody UpdateVenue request) {
    return ResponseEntity.status(HttpStatus.OK).body(venueService.updateVenue(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVenue(@PathVariable UUID id) {
    venueService.deleteVenue(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
