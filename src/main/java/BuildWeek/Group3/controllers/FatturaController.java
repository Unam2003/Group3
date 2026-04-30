package BuildWeek.Group3.controllers;

import BuildWeek.Group3.entities.Fattura;
import BuildWeek.Group3.payloads.FatturaDTO;
import BuildWeek.Group3.services.FatturaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    private final FatturaService fatturaService;

    public FatturaController(FatturaService fatturaService) {
        this.fatturaService = fatturaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura create(@RequestBody @Valid FatturaDTO body) {
        return fatturaService.create(body);
    }

    @GetMapping
    public List<Fattura> findAll() {
        return fatturaService.findAll();
    }
    @GetMapping("/{id}")
    public Fattura findById(@PathVariable UUID id) {
        return fatturaService.findById(id);
    }

    @PutMapping("/{id}")
    public Fattura update(@PathVariable UUID id, @RequestBody @Valid FatturaDTO body) {
        return fatturaService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        fatturaService.delete(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Fattura> findByCliente(@PathVariable UUID clienteId) {
        return fatturaService.findByCliente(clienteId);
    }

    @GetMapping("/stato/{statoId}")
    public List<Fattura> findByStato(@PathVariable UUID statoId) {
        return fatturaService.findByStato(statoId);
    }

    @GetMapping("/data")
    public List<Fattura> findByData(@RequestParam LocalDate data) {
        return fatturaService.findByData(data);
    }

    @GetMapping("/anno/{anno}")
    public List<Fattura> findByAnno(@PathVariable int anno) {
        return fatturaService.findByAnno(anno);
    }

    @GetMapping("/importo")
    public List<Fattura> findByRangeImporto(@RequestParam Double min, @RequestParam Double max) {
        return fatturaService.findByRangeImporto(min, max);
    }
}