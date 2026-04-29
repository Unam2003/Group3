package BuildWeek.Group3.services;

import BuildWeek.Group3.entities.Cliente;
import BuildWeek.Group3.exceptions.BadRequestException;
import BuildWeek.Group3.exceptions.NotFoundException;
import BuildWeek.Group3.payloads.NewClienteDTO;
import BuildWeek.Group3.repositories.ClienteRepository;
import BuildWeek.Group3.specifications.ClienteSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(NewClienteDTO body) {

        if (clienteRepository.existsByPartitaIva(body.partitaIva())) {
            throw new BadRequestException("La partita IVA " + body.partitaIva() + " è già in uso!");
        }

        if (clienteRepository.existsByEmail(body.email())) {
            throw new BadRequestException("Email " + body.email() + " è già in uso!");
        }

        Cliente cliente = new Cliente();

        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setEmail(body.email());
        cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(body.dataUltimoContatto());
        cliente.setFatturatoAnnuale(body.fatturatoAnnuale());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setLogoAziendale(body.logoAziendale());
        cliente.setTipoCliente(body.tipoCliente());

        return clienteRepository.save(cliente);

    }

    public Cliente findById(UUID idCliente) {
        return clienteRepository.findById(idCliente).orElseThrow(() -> new NotFoundException("Il cliente con id " + idCliente + " non è stato trovato!"));
    }

    public void findByIdAndDelete(UUID idCliente) {
        Cliente found = this.findById(idCliente);
        clienteRepository.delete(found);
    }

    public Cliente findByIdAndUpdate(UUID idCliente, NewClienteDTO body) {
        Cliente found = this.findById(idCliente);

        if (!found.getPartitaIva().equals(body.partitaIva()) && clienteRepository.existsByPartitaIva(body.partitaIva())) {
            throw new BadRequestException("La partita IVA " + body.partitaIva() + " è già in uso!");
        }

        if (!found.getEmail().equals(body.email()) && clienteRepository.existsByEmail(body.email())) {
            throw new BadRequestException("Email " + body.email() + " è già in uso!");
        }

        found.setRagioneSociale(body.ragioneSociale());
        found.setPartitaIva(body.partitaIva());
        found.setEmail(body.email());
        found.setDataUltimoContatto(body.dataUltimoContatto());
        found.setFatturatoAnnuale(body.fatturatoAnnuale());
        found.setPec(body.pec());
        found.setTelefono(body.telefono());
        found.setEmailContatto(body.emailContatto());
        found.setNomeContatto(body.nomeContatto());
        found.setCognomeContatto(body.cognomeContatto());
        found.setTelefonoContatto(body.telefonoContatto());
        found.setLogoAziendale(body.logoAziendale());
        found.setTipoCliente(body.tipoCliente());

        return clienteRepository.save(found);
    }

    public Page<Cliente> findAll(int page, int size, String sortyBy){
        if (size > 100) size = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortyBy));
        return clienteRepository.findAll(pageable);
    }

    public Page<Cliente> findAllFiltered(int page, int size, String sortBy, String nome, Double fatturatoMin, Double fatturatoMax, LocalDate dataInserimento, LocalDate dataUltimoContatto) {
        if (size > 100) size = 100;

        Specification<Cliente> specification = Specification.where(ClienteSpecification.ragioneSocialeContiene(nome))
                .and(ClienteSpecification.fatturatoMaggioreUguale(fatturatoMin))
                .and(ClienteSpecification.fatturatoMinoreUguale(fatturatoMax))
                .and(ClienteSpecification.dataInserimentoUguale(dataInserimento))
                .and(ClienteSpecification.dataUltimoContattoUguale(dataUltimoContatto));

        if (sortBy.equalsIgnoreCase("provinciaSedeLegale")) {
            specification = specification.and(ClienteSpecification.ordinaPerProvinciaSedeLegale());
            Pageable pageable = PageRequest.of(page, size);
            return clienteRepository.findAll(specification, pageable);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return  clienteRepository.findAll(specification, pageable);
    }

}
