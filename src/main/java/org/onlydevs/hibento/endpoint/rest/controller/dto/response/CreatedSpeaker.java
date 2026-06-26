package org.onlydevs.hibento.endpoint.rest.controller.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.onlydevs.hibento.endpoint.rest.controller.dto.ExternalLinkDto;

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
public class CreatedSpeaker {
  private UUID id;
  private String name;
  private String avatarUrl;
  private String bio;
  private List<ExternalLinkDto> externalLinks;
  private Instant createdAt;
  private Instant updatedAt;
}
