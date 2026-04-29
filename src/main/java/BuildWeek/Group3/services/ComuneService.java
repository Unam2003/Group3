package BuildWeek.Group3.services;

import BuildWeek.Group3.entities.Comune;
import BuildWeek.Group3.exceptions.NotFoundException;
import BuildWeek.Group3.repositories.ComuneRepository;
import BuildWeek.Group3.repositories.ProvinciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ComuneService {

    private final ComuneRepository comuneRepository;
    private final ProvinciaService provinciaService;

    public ComuneService(ComuneRepository comuneRepository, ProvinciaService provinciaService) {
        this.comuneRepository = comuneRepository;
        this.provinciaService = provinciaService;
    }

    public Page<Comune> findAll(int page, int size, String sortBy) {
        if(size > 100) size = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return  comuneRepository.findAll(pageable);
    }

    public Comune findById(UUID idComune) {
        return comuneRepository.findById(idComune).orElseThrow(() -> new NotFoundException("Comune con id " + idComune + " non trovato!" ));
    }

    public Page<Comune> findByProvincia(UUID idProvincia, int page, int size, String sortBy) {
        provinciaService.findById(idProvincia);

        if(size > 100) size = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return comuneRepository.findByProvincia_IdProvincia(idProvincia, pageable);
    }

}
