package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Comune;
import BuildWeek.Group3.entities.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComuneRepository extends JpaRepository<Comune, UUID> {

    boolean existsByNomeAndProvincia(String nome, Provincia provincia);

    Page<Comune> findByProvincia_IdProvincia(UUID idProvincia, Pageable pageable);
}