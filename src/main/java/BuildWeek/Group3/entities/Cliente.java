package BuildWeek.Group3.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {

    @Id
    @GeneratedValue
    @Column(name = "id_cliente")
    private UUID idCliente;

    @Column(name = "ragione_sociale", nullable = false)
    private String ragioneSociale;

    @Column(name = "partita_iva", nullable = false, unique = true)
    private String partitaIva;

    @Column(nullable = false)
    private String email;

    @Column(name = "data_inserimento", nullable = false)
    private LocalDate dataInserimento;

    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;

    @Column (name = "fatturato_annuale")
    private double fatturatoAnnuale;

    private String pec;

    private String telefono;

    @Column(name = "email_contatto")
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "telefono_contatto")
    private String telefonoContatto;

    @Column(name = "logo_aziendale")
    private String logoAziendale;

    @Enumerated(EnumType.STRING)
    @Column (name = "tipo_cliente", nullable = false)
    private TipoCliente tipoCliente;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Indirizzo> indirizzi;
}
