package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, UUID> {

    Optional<StatoFattura> findByStato(String stato);

    boolean existsByStato(String stato);
}
