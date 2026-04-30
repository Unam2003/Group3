package BuildWeek.Group3.services;

import BuildWeek.Group3.entities.Ruolo;
import BuildWeek.Group3.entities.Utente;
import BuildWeek.Group3.entities.UtenteRuolo;
import BuildWeek.Group3.exceptions.BadRequestException;
import BuildWeek.Group3.exceptions.NotFoundException;
import BuildWeek.Group3.payloads.UtenteDTO;
import BuildWeek.Group3.repositories.RuoloRepository;
import BuildWeek.Group3.repositories.UtenteRepository;
import BuildWeek.Group3.repositories.UtenteRuoloRepository;
import BuildWeek.Group3.tools.EmailSender;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UtenteService {
    public final UtenteRepository utenteRepository;
    public final UtenteRuoloRepository utenteRuoloRepository;
    public final RuoloRepository ruoloRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder bcrypt;
    private final Cloudinary cloudinaryUploader;

    public UtenteService(UtenteRepository utenteRepository, UtenteRuoloRepository utenteRuoloRepository, RuoloRepository ruoloRepository, EmailSender emailSender, PasswordEncoder bcrypt, Cloudinary cloudinaryUploader) {
        this.utenteRepository = utenteRepository;
        this.utenteRuoloRepository = utenteRuoloRepository;
        this.ruoloRepository = ruoloRepository;
        this.emailSender = emailSender;
        this.bcrypt = bcrypt;
        this.cloudinaryUploader = cloudinaryUploader;
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesForUtente(Utente utente) {
        List<UtenteRuolo> associazioni = utenteRuoloRepository.findByUtente(utente);
        return associazioni.stream()
                .map(assoc -> new SimpleGrantedAuthority(assoc.getRuolo().getTipo()))
                .toList();
    }

    public Utente saveUtente(UtenteDTO body) {
        if (this.utenteRepository.existsByEmail(body.email()))
            throw new BadRequestException("L'indirizzo email " + body.email() + " è già in uso!");

        Utente newUtente = new Utente(body.username(), body.email(), this.bcrypt.encode(body.password()), body.nome(), body.cognome());
        Utente saveUtente = this.utenteRepository.save(newUtente);

        Ruolo defaultRole = ruoloRepository.findByTipo("USER").orElseThrow(() -> new NotFoundException("Ruolo USER non trovato!"));

        UtenteRuolo utenteRuolo = new UtenteRuolo(saveUtente, defaultRole);
        this.utenteRuoloRepository.save(utenteRuolo);

        this.emailSender.sendRegistrationEmail(saveUtente);

        log.info("L'utente con id " + saveUtente.getUtenteId() + " è stato creato correttamente");

        return saveUtente;
    }

    public Utente findById(UUID utenteId) {
        return this.utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public Utente findByIdAndUpdate(UUID utenteId, UtenteDTO body) {
        Utente found = this.findById(utenteId);

        if (!found.getEmail().equals(body.email())) {
            if (this.utenteRepository.existsByEmail(body.email()))
                throw new BadRequestException("L'indirizzo email " + body.email() + " è già in uso!");
        }
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setUsername(body.username());
        found.setEmail(body.email());
        found.setPassword(body.password());

        Utente updateUtente = this.utenteRepository.save(found);
        log.info("L'utente con id " + updateUtente.getUtenteId() + " è stato modificato correttamente");
        return updateUtente;
    }

    public void findByIdAndDelete(UUID utenteId) {
        Utente found = this.findById(utenteId);
        this.utenteRepository.delete(found);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato!"));


    }

    public void avatarUpload(MultipartFile file, UUID utenteId) {
        try {
            Utente found = this.findById(utenteId);

            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");

            found.setAvatarURL(url);
            this.utenteRepository.save(found);
            log.info("Avatar aggiornato per l'utente con id " + utenteId);

        } catch (IOException e) {
            throw new BadRequestException("Errore nel caricamento del file!");
        }
    }

}
