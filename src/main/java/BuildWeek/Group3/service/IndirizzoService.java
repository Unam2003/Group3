package BuildWeek.Group3.service;

import BuildWeek.Group3.entities.*;
import BuildWeek.Group3.payloads.IndirizzoDTO;
import BuildWeek.Group3.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class IndirizzoService {
//TODO STESSA COSA DELLA FATTURA
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ComuneRepository comuneRepository;

    public Indirizzo create(IndirizzoDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new RuntimeException("Cliente non trovato"));

        Comune comune = comuneRepository.findById(dto.comuneId()).orElseThrow(() -> new RuntimeException("Comune non trovato"));Indirizzo indirizzo = new Indirizzo();indirizzo.setVia(dto.via());indirizzo.setCivico(dto.civico());indirizzo.setLocalità(dto.localita());indirizzo.setCap(dto.cap());indirizzo.setTipoIndirizzo(dto.tipoIndirizzo());indirizzo.setCliente(cliente);indirizzo.setComune(comune);

        return indirizzoRepository.save(indirizzo);
    }
    public List<Indirizzo> findAll() {
        return indirizzoRepository.findAll();
    }
    public Indirizzo findById(UUID id) {
        return indirizzoRepository.findById(id).orElseThrow(() -> new RuntimeException("Indirizzo non trovato"));
    }

    // UPDATE
    public Indirizzo update(UUID id, IndirizzoDTO dto) {
        Indirizzo indirizzo = findById(id);
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new RuntimeException("Cliente non trovato"));

        Comune comune = comuneRepository.findById(dto.comuneId()).orElseThrow(() -> new RuntimeException("Comune non trovato"));

        indirizzo.setVia(dto.via());
        indirizzo.setCivico(dto.civico());
        indirizzo.setLocalità(dto.localita());
        indirizzo.setCap(dto.cap());
        indirizzo.setTipoIndirizzo(dto.tipoIndirizzo());
        indirizzo.setCliente(cliente);
        indirizzo.setComune(comune);

        return indirizzoRepository.save(indirizzo);
    }

    public void delete(UUID id) {
        Indirizzo indirizzo = findById(id);
        indirizzoRepository.delete(indirizzo);
    }

    public List<Indirizzo> findByCliente(UUID clienteId) {
        return indirizzoRepository.findByCliente_IdCliente(clienteId);
    }

    public List<Indirizzo> findByComune(UUID comuneId) {
        return indirizzoRepository.findByComune_IdComune(comuneId);
    }
}