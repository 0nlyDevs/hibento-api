package org.onlydevs.hibento.repository;

import java.util.UUID;
import org.onlydevs.hibento.model.Speaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, UUID> {
  @EntityGraph(attributePaths = {"eventSessionSpeakers"})
  Page<Speaker> findAll(Pageable pageable);
}
