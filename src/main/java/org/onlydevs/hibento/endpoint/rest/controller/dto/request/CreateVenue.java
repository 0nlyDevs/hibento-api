package org.onlydevs.hibento.endpoint.rest.controller.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class CreateVenue {
  @NotBlank(message = "Venue name cannot be blank")
  private String name;

  @NotBlank(message = "City cannot be blank")
  private String city;

  @NotBlank(message = "Neighborhood cannot be blank")
  private String neighborhood;

  @Min(value = 1, message = "Total rooms must be at least 1")
  private int totalRooms;
}
