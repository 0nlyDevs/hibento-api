package org.onlydevs.hibento.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.NotFoundException;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateVenue;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateVenue;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedVenue;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedVenue;
import org.onlydevs.hibento.mapper.VenueMapper;
import org.onlydevs.hibento.model.Venue;
import org.onlydevs.hibento.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class VenueService {

  private final VenueRepository venueRepository;
  private final VenueMapper venueMapper;

  @Transactional
  public CreatedVenue createVenue(CreateVenue request) {
    Venue venue = new Venue();
    venue.setName(request.getName());
    venue.setCity(request.getCity());
    venue.setNeighborhood(request.getNeighborhood());
    venue.setTotalRooms(request.getTotalRooms());
    Venue savedVenue = venueRepository.save(venue);
    return venueMapper.toCreatedVenue(savedVenue);
  }

  @Transactional
  public UpdatedVenue updateVenue(UUID id, UpdateVenue request) {
    Venue venue =
        venueRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Venue not found"));
    venue.setName(request.getName());
    venue.setCity(request.getCity());
    venue.setNeighborhood(request.getNeighborhood());
    venue.setTotalRooms(request.getTotalRooms());
    Venue updatedVenue = venueRepository.save(venue);
    return venueMapper.toUpdatedVenue(updatedVenue);
  }

  @Transactional
  public void deleteVenue(UUID id) {
    if (!venueRepository.existsById(id)) {
      throw new NotFoundException("Venue not found");
    }
    venueRepository.deleteById(id);
  }
}
