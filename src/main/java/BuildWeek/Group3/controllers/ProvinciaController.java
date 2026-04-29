package BuildWeek.Group3.controllers;

import BuildWeek.Group3.entities.Provincia;
import BuildWeek.Group3.services.ProvinciaService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/province")
public class ProvinciaController {

    private final ProvinciaService provinciaService;

    public ProvinciaController(ProvinciaService provinciaService) {
        this.provinciaService = provinciaService;
    }

    @GetMapping
    public Page<Provincia> findProvince(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "nomeProvincia") String sortBy
    ) {
      return  provinciaService.findAll(page, size, sortBy);
    }

    @GetMapping("/{idProvincia}")
    public Provincia findById(@PathVariable UUID idProvincia) {
        return provinciaService.findById(idProvincia);
    }

    @GetMapping("/sigla/{sigla}")
    public Provincia findBySigla(@PathVariable String sigla) {
        return provinciaService.findBySigla(sigla);
    }
}
