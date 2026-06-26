package org.onlydevs.hibento.service;

import lombok.AllArgsConstructor;

import java.util.UUID;

import org.onlydevs.hibento.endpoint.rest.controller.dto.PaginatedResponse;
import org.onlydevs.hibento.endpoint.rest.controller.dto.Pagination;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.SpeakerDetail;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.SpeakerSummary;
import org.onlydevs.hibento.mapper.SpeakerMapper;
import org.onlydevs.hibento.repository.SpeakerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SpeakerService {
  private final SpeakerRepository speakerRepository;
  private final SpeakerMapper speakerMapper;

  @Transactional(readOnly = true)
  public PaginatedResponse<SpeakerSummary> getAllSpeaker(Pageable pageable) {
    var paginated = speakerRepository.findAll(pageable);
    var speakerSummaryList = paginated.getContent().stream().map(speakerMapper::toSummary).toList();
    return new PaginatedResponse<SpeakerSummary>(
        speakerSummaryList,
        new Pagination(paginated.getNumber(), paginated.getSize(), paginated.getTotalElements()));
  }

  @Transactional(readOnly = true)
  public SpeakerDetail getSpeakerById(UUID id) {
    var speaker = speakerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found " + id));
    return speakerMapper.toSpeakerDetail(speaker);
  }

}
