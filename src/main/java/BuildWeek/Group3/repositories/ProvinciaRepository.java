package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {

    Optional<Provincia> findByNome(String nome);

    Optional<Provincia> findBySigla(String sigla);

    boolean existsByNome(String nome);

    boolean existsBySigla(String sigla);
}