package org.onlydevs.hibento.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.NotFoundException;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateRoom;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateRoom;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedRoom;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedRoom;
import org.onlydevs.hibento.mapper.RoomMapper;
import org.onlydevs.hibento.model.Room;
import org.onlydevs.hibento.model.Venue;
import org.onlydevs.hibento.model.exception.ConflictException;
import org.onlydevs.hibento.repository.RoomRepository;
import org.onlydevs.hibento.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  private final VenueRepository venueRepository;
  private final RoomMapper roomMapper;

  @Transactional
  public CreatedRoom createRoom(CreateRoom request) {
    Venue venue = venueRepository
        .findById(request.getVenueId())
        .orElseThrow(() -> new NotFoundException("Venue not found"));

    if (roomRepository.existsByVenueIdAndName(request.getVenueId(), request.getName())) {
      throw new ConflictException("Room name already exists in this venue");
    }

    Room room = new Room();
    room.setName(request.getName());
    room.setCapacity(request.getCapacity());
    room.setVenue(venue);
    Room savedRoom = roomRepository.save(room);
    return roomMapper.toCreatedRoom(savedRoom);
  }

  @Transactional
  public UpdatedRoom updateRoom(UUID id, UpdateRoom request) {
    Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));

    if (!room.getVenue().getId().equals(request.getVenueId())) {
      venueRepository
          .findById(request.getVenueId())
          .orElseThrow(() -> new NotFoundException("Venue not found"));
    }

    if (!room.getName().equals(request.getName())
        && roomRepository.existsByVenueIdAndName(request.getVenueId(), request.getName())) {
      throw new ConflictException("Room name already exists in this venue");
    }

    Venue venue = venueRepository
        .findById(request.getVenueId())
        .orElseThrow(() -> new NotFoundException("Venue not found"));

    room.setName(request.getName());
    room.setCapacity(request.getCapacity());
    room.setVenue(venue);
    Room updatedRoom = roomRepository.save(room);
    return roomMapper.toUpdatedRoom(updatedRoom);
  }

  @Transactional
  public void deleteRoom(UUID id) {
    if (!roomRepository.existsById(id)) {
      throw new NotFoundException("Room not found");
    }
    roomRepository.deleteById(id);
  }
}
