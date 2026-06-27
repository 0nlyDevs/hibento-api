package org.onlydevs.hibento.endpoint.rest.controller.dto.response;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedRoom {
  private UUID id;
  private String name;
  private Integer capacity;
  private UUID venueId;
  private Instant createdAt;
  private Instant updatedAt;
}
