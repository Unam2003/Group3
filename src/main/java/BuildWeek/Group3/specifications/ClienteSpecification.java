package BuildWeek.Group3.specifications;

import BuildWeek.Group3.entities.Cliente;
import BuildWeek.Group3.entities.Comune;
import BuildWeek.Group3.entities.Indirizzo;
import BuildWeek.Group3.entities.Provincia;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import java.time.LocalDate;

public class ClienteSpecification {

    public static Specification<Cliente> ragioneSocialeContiene(String nome){
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) return null;

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("ragioneSociale")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> fatturatoMaggioreUguale(Double fatturatoMin) {
        return (root, query, criteriaBuilder) -> {
            if (fatturatoMin == null) return null;

            return criteriaBuilder.greaterThanOrEqualTo(root.get("fatturatoAnnuale"), fatturatoMin);
        };
    }

    public static Specification<Cliente> fatturatoMinoreUguale(Double fatturatoMax) {
        return (root, query, criteriaBuilder) -> {
            if (fatturatoMax == null) return null;

            return criteriaBuilder.lessThanOrEqualTo(root.get("fatturatoAnnuale"), fatturatoMax);
        };
    }

    public static Specification<Cliente> dataInserimentoUguale(LocalDate dataInserimento) {
        return (root, query, criteriaBuilder) -> {
            if (dataInserimento == null) return null;

            return criteriaBuilder.equal(root.get("dataInserimento"), dataInserimento);
        };
    }

    public static Specification<Cliente> dataUltimoContattoUguale(LocalDate dataUltimoContatto) {
        return (root, query, criteriaBuilder) -> {
            if (dataUltimoContatto == null) return null;

            return criteriaBuilder.equal(root.get("dataUltimoContatto"), dataUltimoContatto);
        };
    }

    public static Specification<Cliente> ordinaPerProvinciaSedeLegale() {
        return (cliente, query, criteriaBuilder) -> {

            Join<Cliente, Indirizzo> indirizzo = cliente.join("indirizzi", JoinType.LEFT);
            Join<Indirizzo, Comune> comune = indirizzo.join("comune", JoinType.LEFT);
            Join<Comune, Provincia> provincia = comune.join("provincia", JoinType.LEFT);

            query.orderBy(criteriaBuilder.asc(provincia.get("nomeProvincia")), criteriaBuilder.asc(cliente.get("ragioneSociale")));

            return criteriaBuilder.equal(indirizzo.get("tipoIndirizzo"), "SEDE_LEGALE");
        };
    }

}
