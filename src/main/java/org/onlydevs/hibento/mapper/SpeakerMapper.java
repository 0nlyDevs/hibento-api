package org.onlydevs.hibento.mapper;

import java.util.List;

import org.onlydevs.hibento.endpoint.rest.controller.dto.EventSessionDto;
import org.onlydevs.hibento.endpoint.rest.controller.dto.ExternalLinkDto;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.SpeakerDetail;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.SpeakerSummary;
import org.onlydevs.hibento.model.EventSessionSpeaker;
import org.onlydevs.hibento.model.Speaker;
import org.onlydevs.hibento.model.SpeakerExternalLink;
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

  public SpeakerDetail toSpeakerDetail(Speaker speaker) {
    return SpeakerDetail.builder()
        .id(speaker.getId())
        .name(speaker.getName())
        .bio(speaker.getBio())
        .avatar(speaker.getAvatarUrl())
        .externalLinks(extractExternalLink(speaker.getSpeakerExternalLinks()))
        .eventSessions(extractEventSession(speaker.getEventSessionSpeakers()))
        .build();
  }

  private List<ExternalLinkDto> extractExternalLink(List<SpeakerExternalLink> links) {
    return links.stream().map(l -> new ExternalLinkDto(l.getLinkType().toString(), l.getUrl())).toList();
  }

  private List<EventSessionDto> extractEventSession(List<EventSessionSpeaker> sessionSpeakers) {
    return sessionSpeakers.stream()
        .map(s -> new EventSessionDto(s.getId(), s.getEventSession().getTitle(),
            s.getEventSession().getEvent().getTitle(), s.getEventSession().getStartTime(),
            s.getEventSession().getRoom().getName()))
        .toList();
  }
}
