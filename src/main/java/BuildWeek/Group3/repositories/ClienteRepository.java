package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByPartitaIva(String partitaIva);

    boolean existsByPartitaIva(String partitaIva);

    boolean existsByEmail(String email);
}
