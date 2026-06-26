package org.onlydevs.hibento.endpoint.rest.controller.dto;

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
public class EventSessionDto {
  private UUID id;
  private String title;
  private String eventName;
  private Instant startTime;
  private String room;
}
