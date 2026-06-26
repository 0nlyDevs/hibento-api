package org.onlydevs.hibento.mapper;

import org.onlydevs.hibento.endpoint.rest.controller.dto.response.SpeakerSummary;
import org.onlydevs.hibento.model.Speaker;
import org.springframework.stereotype.Component;

@Component
public class SpeakerMapper {
  public SpeakerSummary toSummary(Speaker speaker) {
    return SpeakerSummary.builder()
        .id(speaker.getId())
        .name(speaker.getName())
        .bio(speaker.getBio())
        .avatar(speaker.getAvatarUrl())
        .eventSessionCount(speaker.getEventSessionSpeakers().size())
        .build();
  }
}
