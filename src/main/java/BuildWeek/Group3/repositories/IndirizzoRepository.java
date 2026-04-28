package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, UUID> {

    List<Indirizzo> findByCliente_IdCliente(UUID idCliente);
    List<Indirizzo> findByComune_IdComune(UUID idComune);
    List<Indirizzo> findByTipoIndirizzo(String tipoIndirizzo);
    List<Indirizzo> findByClienteIdClienteAndTipoIndirizzo(UUID idCliente, String tipoIndirizzo);
    List<Indirizzo> findByClienteIdClienteAndComune_IdComune(UUID idCliente, UUID idComune);
}