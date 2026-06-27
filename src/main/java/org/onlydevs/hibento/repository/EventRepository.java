package org.onlydevs.hibento.repository;

import java.util.UUID;
import org.onlydevs.hibento.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {}
