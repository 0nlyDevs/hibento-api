package org.onlydevs.hibento.endpoint.rest.controller.dto.response;

import java.util.List;
import java.util.UUID;

import org.onlydevs.hibento.endpoint.rest.controller.dto.EventSessionDto;
import org.onlydevs.hibento.endpoint.rest.controller.dto.ExternalLinkDto;

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
public class SpeakerDetail {
  private UUID id;
  private String name;
  private String bio;
  private String avatar;
  private List<ExternalLinkDto> externalLinks;
  private List<EventSessionDto> eventSessions;

}
