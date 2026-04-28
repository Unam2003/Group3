package BuildWeek.Group3.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "stato_fattura")
public class StatoFattura {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String stato;

    public StatoFattura() {}

    public StatoFattura(String stato) {
        this.stato = stato;
    }
}