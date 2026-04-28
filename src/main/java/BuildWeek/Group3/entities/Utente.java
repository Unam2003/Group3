package BuildWeek.Group3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "utente_id")
    private UUID utenteId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, name = "avatar_url")
    private String avatarURL;


    public Utente(String username, String email, String password, String nome, String cognome) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatarURL = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }


}
