package BuildWeek.Group3.controllers;

import BuildWeek.Group3.entities.Utente;
import BuildWeek.Group3.payloads.UtenteDTO;
import BuildWeek.Group3.services.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping
public class UtenteController {
    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }


    @GetMapping("/me")
    public Utente getOwnProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public Utente updateOwnProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser, @RequestBody UtenteDTO body) {
        return this.utenteService.findByIdAndUpdate(currentAuthenticatedUser.getUtenteId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwnProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        this.utenteService.findByIdAndDelete(currentAuthenticatedUser.getUtenteId());
    }

    @PatchMapping("/me/avatar")
    public void uploadAvatar(@AuthenticationPrincipal Utente currentAuthenticatedUser, @RequestParam("profile_picture") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());

        this.utenteService.avatarUpload(file, currentAuthenticatedUser.getUtenteId());
    }


    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void getByIdAndDelete(@PathVariable UUID utenteId) {
        this.utenteService.findByIdAndDelete(utenteId);
    }


}
