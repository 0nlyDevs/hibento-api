package org.onlydevs.hibento.service;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateExternalLink;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedSpeaker;
import org.onlydevs.hibento.endpoint.rest.controller.NotFoundException;
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

  @Transactional
  public UpdatedSpeaker updateSpeaker(UUID id, UpdateSpeaker request) {
    Speaker speaker =
        speakerRepository.findById(id).orElseThrow(() -> new NotFoundException("Speaker not found"));
    speaker.setName(request.getName());
    speaker.setAvatarUrl(request.getAvatarUrl());
    speaker.setBio(request.getBio());
    updateExternalLinks(speaker, request.getExternalLinks());
    Speaker updatedSpeaker = speakerRepository.save(speaker);
    return speakerMapper.toUpdatedSpeaker(updatedSpeaker);
  }

  @Transactional
  public void deleteSpeaker(UUID id) {
    if (!speakerRepository.existsById(id)) {
      throw new NotFoundException("Speaker with id: " + id + " doesn't exist.");
    }
    speakerRepository.deleteById(id);
  }

  private void updateExternalLinks(Speaker speaker, List<CreateExternalLink> externalLinks) {
    speaker.getSpeakerExternalLinks().clear();
    if (externalLinks != null && !externalLinks.isEmpty()) {
      var newLinks =
          externalLinks.stream()
              .map(
                  linkRequest -> {
                    LinkType linkType = parseLinkType(linkRequest.getType());
                    return SpeakerExternalLink.builder()
                        .speaker(speaker)
                        .linkType(linkType)
                        .url(linkRequest.getUrl())
                        .build();
                  })
              .toList();
      speaker.getSpeakerExternalLinks().addAll(newLinks);
    }
  }

  private LinkType parseLinkType(String type) {
    if (type == null) {
      return LinkType.OTHER;
    }
    try {
      return LinkType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException e) {
      return LinkType.OTHER;
    }
  }
}
