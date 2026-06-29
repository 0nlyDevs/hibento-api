package org.onlydevs.hibento.repository;

import java.time.Instant;
import java.util.UUID;
import org.onlydevs.hibento.model.EventSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<EventSession, UUID> {
  boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
      UUID roomId, Instant endTime, Instant startTime);

  boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndIdNot(
      UUID roomId, Instant endTime, Instant startTime, UUID id);

  @Query(
      "SELECT COUNT(ess) > 0 FROM EventSessionSpeaker ess "
          + "WHERE ess.speaker.id = :speakerId "
          + "AND ess.eventSession.startTime < :endTime "
          + "AND ess.eventSession.endTime > :startTime")
  boolean existsBySpeakerIdAndOverlappingTime(
      @Param("speakerId") UUID speakerId,
      @Param("startTime") Instant startTime,
      @Param("endTime") Instant endTime);

  @Query(
      "SELECT COUNT(ess) > 0 FROM EventSessionSpeaker ess "
          + "WHERE ess.speaker.id = :speakerId "
          + "AND ess.eventSession.id != :sessionId "
          + "AND ess.eventSession.startTime < :endTime "
          + "AND ess.eventSession.endTime > :startTime")
  boolean existsBySpeakerIdAndOverlappingTimeAndIdNot(
      @Param("speakerId") UUID speakerId,
      @Param("startTime") Instant startTime,
      @Param("endTime") Instant endTime,
      @Param("sessionId") UUID sessionId);
}
