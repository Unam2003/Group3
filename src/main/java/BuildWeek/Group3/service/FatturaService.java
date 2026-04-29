package BuildWeek.Group3.service;

import BuildWeek.Group3.entities.*;
import BuildWeek.Group3.payloads.FatturaDTO;
import BuildWeek.Group3.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;
//TODO CLIENTE
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    //TODO CI SONO ANCHE CREATE ECC PER CLIENTE, CHE MANCA
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

    public List<Fattura> findByRangeImporto(Double min, Double max) {
        return fatturaRepository.findByImportoBetween(min, max);
    }
}