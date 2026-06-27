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
public class CreatedVenue {
  private UUID id;
  private String name;
  private String city;
  private String neighborhood;
  private int totalRooms;
  private Instant createdAt;
  private Instant updatedAt;
}
