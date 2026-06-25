package org.onlydevs.hibento.repository;

import java.util.List;
import org.onlydevs.hibento.PojaGenerated;
import org.onlydevs.hibento.repository.model.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@PojaGenerated
@Repository
public interface DummyRepository extends JpaRepository<Dummy, String> {

  @Override
  List<Dummy> findAll();
}
