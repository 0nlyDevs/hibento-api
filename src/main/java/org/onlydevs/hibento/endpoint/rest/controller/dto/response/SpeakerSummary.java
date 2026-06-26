package org.onlydevs.hibento.endpoint.rest.controller.dto.response;

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
public class SpeakerSummary {
  private UUID id;
  private String name;
  private String avatar;
  private String bio;
  private Integer eventSessionCount;
}
