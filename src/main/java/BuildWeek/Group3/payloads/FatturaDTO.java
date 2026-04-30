package BuildWeek.Group3.payloads;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

public record FatturaDTO(
        @NotBlank(message = "Il numero fattura è obbligatorio")
        String numero,

        @NotNull(message = "La data è obbligatoria")
        LocalDate data,

        @NotNull(message = "L'importo è obbligatorio")
        @Positive(message = "L'importo deve essere positivo")
        Double importo,

        @NotNull(message = "Il cliente è obbligatorio")
        UUID clienteId,

        @NotNull(message = "Lo stato fattura è obbligatorio")
        UUID statoId
) {}
