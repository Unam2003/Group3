package BuildWeek.Group3.security;

import BuildWeek.Group3.entities.Utente;
import BuildWeek.Group3.exceptions.UnauthorizedException;
import BuildWeek.Group3.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private final TokenTools tokenTools;
    private final UtenteService utenteService;

    public TokenFilter(TokenTools tokenTools, UtenteService utenteService) {
        this.tokenTools = tokenTools;
        this.utenteService = utenteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Verifichiamo se la richiesta contiene l'header Authorization e questo deve contenere il token nel formato "Bearer eyJhbteirtkbla
        String authHeder = request.getHeader("Authorization");
        if (authHeder == null || !authHeder.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'authorization header nel formato corretto");

        // 2. Estraiamo il token dall'header
        // authHeader = "Bearer eyJhbGcblabla
        String accessToken = authHeder.replace("Bearer ", "");

        // 3. Verifichiamo che il token sia OK (verifichiamo la firma e che non sia scaduto), se c'è qualche problema -> Errore
        tokenTools.verifyToken(accessToken);


        // -----------> AUTORIZZAZIONE <-------
        // 1. Cerchiamo l'utente nel db
        // 1.1 Estraiamo l'id dal token
        UUID utenteId = this.tokenTools.extractIdFromToken(accessToken);

        // 1.2 FindById
        Utente authenticatedUtente = this.utenteService.findById(utenteId);

        Collection<? extends GrantedAuthority> authorities = utenteService.getAuthoritiesForUtente(authenticatedUtente);


        // 2. Associamo l'utente al Security Context
        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedUtente, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // 3. Se tutto è OK -> Andiamo avanti con la catena (o un prossimo filtro o direttamente il controller)
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

//        return request.getServletPath().equals("/auth/login") || request.getServletPath().equals("/auth/register");
        // versione più potente e professionale -->
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}
