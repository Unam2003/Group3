package BuildWeek.Group3.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "fatture")
public class Fattura {

    @Id
    @GeneratedValue
    private UUID idFattura;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Double importo;

    @Column(nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_stato_fattura", nullable = false)
    private StatoFattura statoFattura;

    public Fattura() {}

    public Fattura(LocalDate data, Double importo, String numero, Cliente cliente, StatoFattura statoFattura) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.cliente = cliente;
        this.statoFattura = statoFattura;
    }
}