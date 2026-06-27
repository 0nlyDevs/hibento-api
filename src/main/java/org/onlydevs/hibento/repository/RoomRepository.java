package org.onlydevs.hibento.repository;

import java.util.UUID;
import org.onlydevs.hibento.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
  boolean existsByVenueIdAndName(UUID venueId, String name);
}
