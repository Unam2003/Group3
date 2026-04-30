package BuildWeek.Group3.specifications;

import BuildWeek.Group3.entities.Fattura;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class FatturaSpecification {

    public static Specification<Fattura> clienteUguale(UUID idCliente) {
        return (root, query, criteriaBuilder) -> {
            if (idCliente == null) {return null;}
            return criteriaBuilder.equal(root.get("cliente").get("idCliente"), idCliente);
        };
    }

    public static Specification<Fattura> statoUguale(UUID id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {return null;}

            return criteriaBuilder.equal(root.get("statoFattura").get("id"), id);
        };
    }

    public static Specification<Fattura> dataUguale(LocalDate data) {
        return (root, query, criteriaBuilder) -> {
            if (data == null) {return null;}

            return criteriaBuilder.equal(root.get("data"), data);
        };
    }

    public static Specification<Fattura> annoUguale(Integer anno){
        return (root, query, criteriaBuilder) -> {
            if (anno == null) {return null;}

            LocalDate inizioAnno = LocalDate.of(anno, 1,1);
            LocalDate fineAnno = LocalDate.of(anno,12,31);

            return criteriaBuilder.between(root.get("data"), inizioAnno, fineAnno);
        };
    }

    public static Specification<Fattura> importoMaggioreUguale(Double importoMin){
        return (root, query, criteriaBuilder) -> {
            if (importoMin == null) {return null;}

            return criteriaBuilder.greaterThanOrEqualTo(root.get("importo"), importoMin);
        };
    }

    public static Specification<Fattura> importoMinoreUguale(Double importoMax) {
        return (root, query, criteriaBuilder) -> {
            if (importoMax == null) return null;

            return criteriaBuilder.lessThanOrEqualTo(root.get("importo"), importoMax);
        };
    }


}
