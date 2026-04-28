package BuildWeek.Group3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "ruoli")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruolo_id")
    private long ruoloId;
    @Column(nullable = false)
    private String tipo;

    public Ruolo(String tipo) {
        this.tipo = tipo;
    }
}
