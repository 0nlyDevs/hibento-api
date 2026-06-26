package org.onlydevs.hibento.endpoint.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

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
