package BuildWeek.Group3.controllers;

import BuildWeek.Group3.entities.Utente;
import BuildWeek.Group3.exceptions.ValidationException;
import BuildWeek.Group3.payloads.LoginDTO;
import BuildWeek.Group3.payloads.LoginRespDTO;
import BuildWeek.Group3.payloads.NewUtenteResp;
import BuildWeek.Group3.payloads.UtenteDTO;
import BuildWeek.Group3.services.AuthService;
import BuildWeek.Group3.services.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UtenteService utenteService;

    public AuthController(AuthService authService, UtenteService utenteService) {
        this.authService = authService;
        this.utenteService = utenteService;
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginDTO body) {
        return new LoginRespDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteResp createUtente(@RequestBody @Validated UtenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            validationResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));

            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }
        Utente newUtente = this.utenteService.saveUtente(body);
        return new NewUtenteResp(newUtente.getUtenteId());
    }
}
