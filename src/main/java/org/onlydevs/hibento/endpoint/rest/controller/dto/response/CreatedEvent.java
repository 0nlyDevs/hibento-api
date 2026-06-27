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
public class CreatedEvent {
  private UUID id;
  private String title;
  private String description;
  private boolean isOnline;
  private Instant startDate;
  private Instant endDate;
  private UUID venueId;
  private Instant createdAt;
  private Instant updatedAt;
}
