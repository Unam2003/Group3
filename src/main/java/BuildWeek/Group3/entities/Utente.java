package BuildWeek.Group3.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue
    @Column(name = "id_utente")
    private UUID id;

    private String username;
    private String email;
    private String password;

    private String nome;
    private String cognome;
    private String avatar;

    @ManyToMany
    @JoinTable(
            name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "id_utente"),
            inverseJoinColumns = @JoinColumn(name = "id_ruolo")
    )
    private Set<Ruolo> ruoli = new HashSet<>();

}