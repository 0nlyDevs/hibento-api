package org.onlydevs.hibento.service;

import org.onlydevs.hibento.endpoint.rest.controller.dto.PaginatedResponse;
import org.onlydevs.hibento.endpoint.rest.controller.dto.Pagination;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.SpeakerSummary;
import org.onlydevs.hibento.mapper.SpeakerMapper;
import org.onlydevs.hibento.repository.SpeakerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SpeakerService {
  private final SpeakerRepository speakerRepository;
  private final SpeakerMapper speakerMapper;

  public PaginatedResponse<SpeakerSummary> getAllSpeaker(Pageable pageable) {
    var paginated = speakerRepository.findAll(pageable);
    var speakerSummaryList = paginated.getContent().stream().map(speakerMapper::toSummary).toList();
    return new PaginatedResponse<SpeakerSummary>(
        speakerSummaryList,
        new Pagination(
            paginated.getNumber(),
            paginated.getSize(),
            paginated.getTotalElements()));
  }
}
