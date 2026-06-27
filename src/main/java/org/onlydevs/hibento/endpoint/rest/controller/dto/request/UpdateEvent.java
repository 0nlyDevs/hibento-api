package org.onlydevs.hibento.endpoint.rest.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateEvent {
  @NotBlank(message = "Event title cannot be blank")
  private String title;

  private String description;

  private boolean isOnline;

  @NotNull(message = "Start date is required")
  private Instant startDate;

  @NotNull(message = "End date is required")
  private Instant endDate;

  private UUID venueId;
}
