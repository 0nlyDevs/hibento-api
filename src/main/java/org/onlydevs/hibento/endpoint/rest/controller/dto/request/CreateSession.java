package org.onlydevs.hibento.endpoint.rest.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
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
public class CreateSession {
  @NotBlank(message = "Session title cannot be blank")
  private String title;

  private String description;

  @NotNull(message = "Start time is required")
  private Instant startTime;

  @NotNull(message = "End time is required")
  private Instant endTime;

  private UUID roomId;

  private Integer capacity;

  @NotEmpty(message = "At least one speaker is required")
  private List<UUID> speakerIds;
}
