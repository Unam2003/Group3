package BuildWeek.Group3.payloads;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record StatoFatturaDTO(
        @NotBlank(message = "Lo stato è obbligatorio")
        String stato
) {}