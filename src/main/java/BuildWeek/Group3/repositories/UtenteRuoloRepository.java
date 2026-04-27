package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Utente;
import BuildWeek.Group3.entities.UtenteRuolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtenteRuoloRepository extends JpaRepository<UtenteRuolo, Long> {
    List<UtenteRuolo> findByUtente(Utente utente);
}
