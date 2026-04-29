package BuildWeek.Group3.controllers;

import BuildWeek.Group3.entities.Cliente;
import BuildWeek.Group3.payloads.ClienteRespDTO;
import BuildWeek.Group3.payloads.NewClienteDTO;
import BuildWeek.Group3.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public Page<Cliente> getClienti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ragioneSociale") String sortBy,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double fatturatoMin,
            @RequestParam(required = false) Double fatturatoMax,
            @RequestParam(required = false) LocalDate dataInserimento,
            @RequestParam(required = false) LocalDate dataUltimoContatto
    ){
        return clienteService.findAllFiltered(page, size, sortBy, nome, fatturatoMin, fatturatoMax, dataInserimento, dataUltimoContatto);
    }

    @GetMapping("/{idCliente}")
    public Cliente findById(@PathVariable UUID idCliente) {
        return clienteService.findById(idCliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteRespDTO saveCliente(@RequestBody @Valid NewClienteDTO body) {
        Cliente savedCliente = clienteService.save(body);
        return new ClienteRespDTO(savedCliente.getIdCliente());
    }

    @PutMapping("/{idCliente}")
    public Cliente findByIdAndUpdate(@PathVariable UUID idCliente, @RequestBody @Valid NewClienteDTO body) {
        return clienteService.findByIdAndUpdate(idCliente, body);
    }

    @DeleteMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID idCliente) {
        clienteService.findByIdAndDelete(idCliente);
    }
}
