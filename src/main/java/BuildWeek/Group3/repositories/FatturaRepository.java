package BuildWeek.Group3.repositories;

import BuildWeek.Group3.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FatturaRepository extends JpaRepository<Fattura, UUID>, JpaSpecificationExecutor<Fattura> {

    List<Fattura> findByCliente_IdCliente(UUID idCliente);
    List<Fattura> findByStatoFattura_Id(UUID idStato);
    List<Fattura> findByData(LocalDate data);
    List<Fattura> findByDataBetween(LocalDate start, LocalDate end);
    List<Fattura> findByImportoBetween(Double min, Double max);
    List<Fattura> findByClienteIdClienteAndStatoFattura_Id(UUID idCliente, UUID idStato);
    List<Fattura> findByClienteIdClienteAndImportoBetween(UUID idCliente, Double min, Double max);
    List<Fattura> findByStatoFatturaIdAndImportoBetween(UUID idStato, Double min, Double max);
}