package org.onlydevs.hibento.mapper;

import org.onlydevs.hibento.endpoint.rest.controller.dto.ExternalLinkDto;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedSpeaker;
import org.onlydevs.hibento.model.Speaker;
import org.springframework.stereotype.Component;

@Component
public class SpeakerMapper {
  public CreatedSpeaker toCreatedSpeaker(Speaker speaker) {
    return new CreatedSpeaker(
        speaker.getId(),
        speaker.getName(),
        speaker.getAvatarUrl(),
        speaker.getBio(),
        speaker.getSpeakerExternalLinks().stream()
            .map(
                link -> new ExternalLinkDto(link.getLinkType().toString().toLowerCase(), link.getUrl()))
            .toList(),
        speaker.getCreatedAt(),
        speaker.getUpdatedAt());
  }

  public UpdatedSpeaker toUpdatedSpeaker(Speaker speaker) {
    return new UpdatedSpeaker(
        speaker.getId(),
        speaker.getName(),
        speaker.getAvatarUrl(),
        speaker.getBio(),
        speaker.getSpeakerExternalLinks().stream()
            .map(
                link -> new ExternalLinkDto(link.getLinkType().toString().toLowerCase(), link.getUrl()))
            .toList(),
        speaker.getCreatedAt(),
        speaker.getUpdatedAt());
  }

}
