package org.onlydevs.hibento.repository;

import java.time.Instant;
import java.util.UUID;
import org.onlydevs.hibento.model.EventSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<EventSession, UUID> {
  boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
      UUID roomId, Instant endTime, Instant startTime);

  boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndIdNot(
      UUID roomId, Instant endTime, Instant startTime, UUID id);
}
