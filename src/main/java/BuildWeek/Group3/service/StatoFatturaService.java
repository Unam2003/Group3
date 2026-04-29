package BuildWeek.Group3.service;

import BuildWeek.Group3.entities.StatoFattura;
import BuildWeek.Group3.payloads.StatoFatturaDTO;
import BuildWeek.Group3.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StatoFatturaService {

    @Autowired
    private StatoFatturaRepository repository;

    public StatoFattura create(StatoFatturaDTO dto) {

        if (repository.existsByStato(dto.stato())) {
            throw new RuntimeException("Stato invariato");
        }

        StatoFattura stato = new StatoFattura(dto.stato());
        return repository.save(stato);
    }
    
    public List<StatoFattura> findAll() {
        return repository.findAll();
    }

    public StatoFattura findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Stato inesistente"));
    }

    public StatoFattura update(UUID id, StatoFatturaDTO dto) {
        StatoFattura stato = findById(id);
        stato.setStato(dto.stato());
        return repository.save(stato);
    }

    public void delete(UUID id) {
        StatoFattura stato = findById(id);
        repository.delete(stato);
    }
}