package BuildWeek.Group3.services;

import BuildWeek.Group3.entities.*;
import BuildWeek.Group3.payloads.FatturaDTO;
import BuildWeek.Group3.repositories.*;
import BuildWeek.Group3.specifications.FatturaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private StatoFatturaRepository statoFatturaRepository;


    public Fattura create(FatturaDTO body) {

        Cliente cliente = clienteRepository.findById(body.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente non trovato"));

        StatoFattura stato = statoFatturaRepository.findById(body.statoId())
                .orElseThrow(() -> new RuntimeException("Stato fattura non trovato"));

        Fattura fattura = new Fattura(body.data(), body.importo(), body.numero(), cliente, stato
        );

        return fatturaRepository.save(fattura);
    }

    public List<Fattura> findAll() {
        return fatturaRepository.findAll();
    }

    public Fattura findById(UUID id) {
        return fatturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fattura non trovata"));
    }
    public List<Fattura> findByData(LocalDate data) {
        return fatturaRepository.findByData(data);
    }
    public Fattura update(UUID id, FatturaDTO body) {

        Fattura fattura = findById(id);

        Cliente cliente = clienteRepository.findById(body.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente non trovato"));

        StatoFattura stato = statoFatturaRepository.findById(body.statoId())
                .orElseThrow(() -> new RuntimeException("Stato fattura non trovato"));fattura.setData(body.data());fattura.setImporto(body.importo());fattura.setNumero(body.numero());fattura.setCliente(cliente);fattura.setStatoFattura(stato);

        return fatturaRepository.save(fattura);
    }

    public void delete(UUID id) {
        Fattura fattura = findById(id);
        fatturaRepository.delete(fattura);
    }
    public List<Fattura> findByCliente(UUID clienteId) {
        return fatturaRepository.findByCliente_IdCliente(clienteId);
    }

    public List<Fattura> findByStato(UUID statoId) {
        return fatturaRepository.findByStatoFattura_Id(statoId);
    }

    public List<Fattura> findByAnno(int anno) {

        LocalDate start = LocalDate.of(anno, 1, 1);
        LocalDate end = LocalDate.of(anno, 12, 31);

        return fatturaRepository.findByDataBetween(start, end);
    }

    public List<Fattura> findByRangeImporto(Double min, Double max) {
        return fatturaRepository.findByImportoBetween(min, max);
    }

    public Page<Fattura> findAllFiltered(int page, int size, String sortBy, UUID idCliente, UUID idStato, LocalDate data, Integer anno, Double importoMin, Double importoMax) {
        if (size > 100) size = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Specification<Fattura> specification = Specification.where(FatturaSpecification.clienteUguale(idCliente))
                .and(FatturaSpecification.statoUguale(idStato))
                .and(FatturaSpecification.dataUguale(data))
                .and(FatturaSpecification.annoUguale(anno))
                .and(FatturaSpecification.importoMaggioreUguale(importoMin))
                .and(FatturaSpecification.importoMinoreUguale(importoMax));

        return fatturaRepository.findAll(specification, pageable);
    }
}