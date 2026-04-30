package BuildWeek.Group3.payloads;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record IndirizzoDTO(
        @NotBlank(message = "La via è obbligatoria")
        String via,

        @NotBlank(message = "Il civico è obbligatorio")
        String civico,

        @NotBlank(message = "La località è obbligatoria")
        String localita,

        @NotBlank(message = "Il CAP è obbligatorio")
        @Size( min = 5, max = 5, message = "Il CAP deve essere di 5 cifre")
        String cap,

        @NotBlank(message = "Il tipo indirizzo è obbligatorio")
        String tipoIndirizzo,

        @NotNull(message = "Il cliente è obbligatorio")
        UUID clienteId,

        @NotNull(message = "Il comune è obbligatorio")
        UUID comuneId
) {}
