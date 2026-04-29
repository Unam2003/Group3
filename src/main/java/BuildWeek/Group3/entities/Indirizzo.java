package BuildWeek.Group3.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "indirizzi")
public class Indirizzo {

    @Id
    @GeneratedValue
    @Column(name = "id_indirizzo")
    private UUID id;
    private String via;
    private String civico;
    private String localita;
    private String cap;

    @Column(name = "tipo_indirizzo")
    private String tipoIndirizzo;

    @ManyToOne
    @JoinColumn(name = "id_comune")
    private Comune comune;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


}
