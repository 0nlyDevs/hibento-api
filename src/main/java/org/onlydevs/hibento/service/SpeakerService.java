package org.onlydevs.hibento.service;

import lombok.AllArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedSpeaker;
import org.onlydevs.hibento.mapper.SpeakerMapper;
import org.onlydevs.hibento.model.Speaker;
import org.onlydevs.hibento.model.SpeakerExternalLink;
import org.onlydevs.hibento.model.enums.LinkType;
import org.onlydevs.hibento.repository.SpeakerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SpeakerService {
  private final SpeakerRepository speakerRepository;
  private final SpeakerMapper speakerMapper;

  @Transactional
  public CreatedSpeaker createSpeaker(CreateSpeaker request) {
    Speaker speaker = new Speaker();
    speaker.setName(request.getName());
    speaker.setAvatarUrl(request.getAvatarUrl());
    speaker.setBio(request.getBio());
    if (request.getExternalLinks() != null && !request.getExternalLinks().isEmpty()) {
      var links =
          request.getExternalLinks().stream()
              .map(
                  linkRequest -> {
                    LinkType linkType;
                    try {
                      linkType = LinkType.valueOf(linkRequest.getType().toUpperCase());
                    } catch (IllegalArgumentException e) {
                      linkType = LinkType.OTHER;
                    }

                    return SpeakerExternalLink.builder()
                        .speaker(speaker)
                        .linkType(linkType)
                        .url(linkRequest.getUrl())
                        .build();
                  })
              .toList();

      speaker.setSpeakerExternalLinks(links);
    }
    Speaker savedSpeaker = speakerRepository.save(speaker);
    return speakerMapper.toCreatedSpeaker(savedSpeaker);
  }
}
