package BuildWeek.Group3.services;

import BuildWeek.Group3.entities.Provincia;
import BuildWeek.Group3.exceptions.NotFoundException;
import BuildWeek.Group3.repositories.ProvinciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProvinciaService {

    private final ProvinciaRepository provinciaRepository;

    public ProvinciaService(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    public Page<Provincia> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return provinciaRepository.findAll(pageable);
    }

    public Provincia findById(UUID idProvincia) {
        return provinciaRepository.findById(idProvincia).orElseThrow(() -> new NotFoundException("Provincia con Id " + idProvincia + " non trovata!"));
    }

    public Provincia findBySigla(String sigla) {
        return provinciaRepository.findBySigla(sigla).orElseThrow(() -> new NotFoundException("Provincia con sigla " + sigla + " non trovata!"));
    }

    public Provincia findByNomeProvincia(String nomeProvincia) {
        return provinciaRepository.findByNomeProvincia(nomeProvincia).orElseThrow(() -> new NotFoundException("Provincia " + nomeProvincia + " non trovata!"));
    }
}
