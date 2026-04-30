package BuildWeek.Group3.controllers;


import BuildWeek.Group3.entities.StatoFattura;

import BuildWeek.Group3.payloads.StatoFatturaDTO;
import BuildWeek.Group3.services.StatoFatturaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stati-fattura")
public class StatoFatturaController {

    private final StatoFatturaService statoFatturaService;

    public StatoFatturaController(StatoFatturaService statoFatturaService) {
        this.statoFatturaService = statoFatturaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatoFattura create(@RequestBody @Valid StatoFatturaDTO body) {
        return statoFatturaService.create(body);
    }

    @GetMapping
    public List<StatoFattura> findAll() {
        return statoFatturaService.findAll();
    }

    @GetMapping("/{id}")
    public StatoFattura findById(@PathVariable UUID id) {
        return statoFatturaService.findById(id);
    }

    @PutMapping("/{id}")
    public StatoFattura update(@PathVariable UUID id, @RequestBody @Valid StatoFatturaDTO body) {
        return statoFatturaService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        statoFatturaService.delete(id);
    }
}