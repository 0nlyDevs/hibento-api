package org.onlydevs.hibento.endpoint.rest.controller.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateRoom {
  @NotBlank(message = "Room name cannot be blank")
  private String name;

  @Min(value = 1, message = "Capacity must be at least 1")
  private Integer capacity;

  @NotNull(message = "Venue ID is required")
  private UUID venueId;
}
