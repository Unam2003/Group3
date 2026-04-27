package BuildWeek.Group3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comune {

    @Id
    @GeneratedValue
    @Column(name = "id_comune")
    private UUID idComune;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;
}
