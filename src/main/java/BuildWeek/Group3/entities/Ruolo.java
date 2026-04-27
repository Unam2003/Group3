package BuildWeek.Group3.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "ruoli")
public class Ruolo {

    @Id
    @GeneratedValue
    @Column(name = "id_ruolo")
    private UUID id;

    private String tipo;

    @ManyToMany(mappedBy = "ruoli")
    private Set<Utente> utenti = new HashSet<>();
}
