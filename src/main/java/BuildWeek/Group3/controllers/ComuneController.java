package BuildWeek.Group3.controllers;

import BuildWeek.Group3.entities.Comune;
import BuildWeek.Group3.services.ComuneService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comuni")
public class ComuneController {

    private final ComuneService comuneService;

    public ComuneController(ComuneService comuneService) {
        this.comuneService = comuneService;
    }

    @GetMapping
    public Page<Comune> getComuni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "nome") String sortBy
    ) {
        return comuneService.findAll(page, size, sortBy);
    }

    @GetMapping("/{idComune}")
    public Comune findById(@PathVariable UUID idComune) {
        return comuneService.findById(idComune);
    }

    @GetMapping("/provincia/{idProvincia}")
    public Page<Comune> findByProvincia(
            @PathVariable UUID idProvincia,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20")int size,
            @RequestParam(defaultValue = "nome") String sortBy
    ) {
        return comuneService.findByProvincia(idProvincia, page, size, sortBy);
    }
}
