package BuildWeek.Group3.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "utenti_ruoli")

public class UtenteRuolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "utente_ruolo_id")
    private long utenteRuoloId;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "ruolo_id", nullable = false)
    private Ruolo ruolo;

    public UtenteRuolo(Utente utente, Ruolo ruolo) {
        this.utente = utente;
        this.ruolo = ruolo;
    }
}
