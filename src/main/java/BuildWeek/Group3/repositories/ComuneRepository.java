package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Comune;
import BuildWeek.Group3.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComuneRepository extends JpaRepository<Comune, UUID> {

    boolean existsByNomeAndProvincia(String nome, Provincia provincia);
}