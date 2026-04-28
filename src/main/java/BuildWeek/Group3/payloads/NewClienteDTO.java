package BuildWeek.Group3.payloads;

import BuildWeek.Group3.entities.TipoCliente;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewClienteDTO(
        @NotBlank(message = "La ragione sociale è obbligatoria!")
        String ragioneSociale,

        @NotBlank(message = "La partita IVA è obbligatoria")
        @Pattern(regexp = "^[0-9]{11}$", message = "La partita IVA deve essere di 11 cifre!")
        String partitaIva,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Email non valida!")
        String email,

        @PastOrPresent(message = "La data di ultimo contatto non può essere nel futuro!")
        LocalDate dataUltimoContatto,

        @NotNull(message = "Il fatturato annuale è obbligatorio")
        @DecimalMin(value = "0.0", message = "Il fatturato non può essere negativo!")
        Double fatturatoAnnuale,

        @Email(message = "Pec non valida!")
        String pec,

        @Size(max = 30, message = "Il numero di telefono non può superare i 30 caratteri!")
        String telefono,

        @Email(message = "Email contatto non valida!")
        String emailContatto,

        @Size(min = 2, max = 50, message = "Il nome deve essere compreso tra i 2 e i 50 caratteri!")
        String nomeContatto,

        @Size(min = 2, max = 50, message = "Il cognome deve essere compreso tra i 2 e i 50 caratteri!")
        String cognomeContatto,

        @Size(min = 6, max = 15, message = "Il numero di telefono deve essere compreso tra i 6 e i 15 caratteri!")
        String telefonoContatto,

        String logoAziendale,

        @NotNull(message = "Il tipo cliente è obbligatorio!")
        TipoCliente tipoCliente

) {
}
