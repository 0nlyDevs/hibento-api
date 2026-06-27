package org.onlydevs.hibento.mapper;

import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedVenue;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedVenue;
import org.onlydevs.hibento.model.Venue;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {

  public CreatedVenue toCreatedVenue(Venue venue) {
    return new CreatedVenue(
        venue.getId(),
        venue.getName(),
        venue.getCity(),
        venue.getNeighborhood(),
        venue.getTotalRooms(),
        venue.getCreatedAt(),
        venue.getUpdatedAt());
  }

  public UpdatedVenue toUpdatedVenue(Venue venue) {
    return new UpdatedVenue(
        venue.getId(),
        venue.getName(),
        venue.getCity(),
        venue.getNeighborhood(),
        venue.getTotalRooms(),
        venue.getCreatedAt(),
        venue.getUpdatedAt());
  }
}
