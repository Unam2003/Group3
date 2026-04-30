package BuildWeek.Group3.controllers;
import BuildWeek.Group3.entities.Indirizzo;

import BuildWeek.Group3.payloads.IndirizzoDTO;
import BuildWeek.Group3.services.IndirizzoService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    private final IndirizzoService indirizzoService;

    public IndirizzoController(IndirizzoService indirizzoService) {
        this.indirizzoService = indirizzoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Indirizzo create(@RequestBody @Valid IndirizzoDTO body) {
        return indirizzoService.create(body);
    }
    @GetMapping
    public List<Indirizzo> findAll() {
        return indirizzoService.findAll();
    }

    @GetMapping("/{id}")
    public Indirizzo findById(@PathVariable UUID id) {
        return indirizzoService.findById(id);
    }

    @PutMapping("/{id}")
    public Indirizzo update(@PathVariable UUID id, @RequestBody @Valid IndirizzoDTO body) {
        return indirizzoService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        indirizzoService.delete(id);
    }
    @GetMapping("/cliente/{clienteId}")
    public List<Indirizzo> findByCliente(@PathVariable UUID clienteId) {
        return indirizzoService.findByCliente(clienteId);
    }

    @GetMapping("/comune/{comuneId}")
    public List<Indirizzo> findByComune(@PathVariable UUID comuneId) {
        return indirizzoService.findByComune(comuneId);
    }
}