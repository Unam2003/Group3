package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>, JpaSpecificationExecutor<Cliente> {

    Optional<Cliente> findByPartitaIva(String partitaIva);

    boolean existsByPartitaIva(String partitaIva);

    boolean existsByEmail(String email);
}
