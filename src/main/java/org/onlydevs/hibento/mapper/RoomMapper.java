package org.onlydevs.hibento.mapper;

import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedRoom;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedRoom;
import org.onlydevs.hibento.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

  public CreatedRoom toCreatedRoom(Room room) {
    return new CreatedRoom(
        room.getId(),
        room.getName(),
        room.getCapacity(),
        room.getVenue().getId(),
        room.getCreatedAt(),
        room.getUpdatedAt());
  }

  public UpdatedRoom toUpdatedRoom(Room room) {
    return new UpdatedRoom(
        room.getId(),
        room.getName(),
        room.getCapacity(),
        room.getVenue().getId(),
        room.getCreatedAt(),
        room.getUpdatedAt());
  }
}
