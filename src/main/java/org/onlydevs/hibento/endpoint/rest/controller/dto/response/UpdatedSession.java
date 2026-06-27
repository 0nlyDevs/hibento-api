package org.onlydevs.hibento.endpoint.rest.controller.dto.response;

import java.time.Instant;
import java.util.List;
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
public class UpdatedSession {
  private UUID id;
  private UUID eventId;
  private String title;
  private String description;
  private Instant startTime;
  private Instant endTime;
  private UUID roomId;
  private String roomName;
  private Integer capacity;
  private List<UUID> speakerIds;
  private Instant createdAt;
  private Instant updatedAt;
}
