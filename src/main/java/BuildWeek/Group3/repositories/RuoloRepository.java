package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByTipo(String tipo);
}
