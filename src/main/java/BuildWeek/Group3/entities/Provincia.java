package BuildWeek.Group3.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Provincia {

    @Id
    @GeneratedValue
    @Column(name = "id_provincia")
    private UUID idProvincia;

    @Column(name = "nome_provincia", nullable = false)
    private String nomeProvincia;

    @Column(nullable = false, unique = true)
    private String sigla;
}
