package cat.paucasesnovescifp.spaddd.ut4.model;

import cat.paucasesnovescifp.spaddd.ut4.auxiliars.JPAException;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Localitats")
public class Localitat {
    @Id
    @Column(name = "idLocalitat", nullable = false, length = 9)
    private String idLocalitat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idIlla", nullable = false)
    private Illa idIlla;

    @Column(name = "nomLocalitat", nullable = false, length = 100)
    private String nomLocalitat;

    public Localitat() {
    }

    public Localitat(String idLocalitat, Illa idIlla, String nomLocalitat) throws JPAException {
        this.setIdLocalitat(idLocalitat);
        this.setIdIlla(idIlla);
        this.setNomLocalitat(nomLocalitat);
    }

    public String getIdLocalitat() {
        return idLocalitat;
    }

    public void setIdLocalitat(String idLocalitat) throws JPAException {
        if (idLocalitat == null || idLocalitat.isBlank()) {
            throw new JPAException("L'identificador no pot ser una cadena nul·la, buida o formada només per espais.");
        }
        this.idLocalitat = idLocalitat;
    }

    public Illa getIdIlla() {
        return idIlla;
    }

    public void setIdIlla(Illa idIlla) throws JPAException {
        if (idIlla == null) {
            throw new JPAException("La illa no pot ser nul·la.");
        }
        this.idIlla = idIlla;
    }

    public String getNomLocalitat() {
        return nomLocalitat;
    }

    public void setNomLocalitat(String nomLocalitat) throws JPAException {
        if (nomLocalitat == null || nomLocalitat.isBlank()) {
            throw new JPAException("El nom no pot ser una cadena nul·la, buida o formada només per espais.");
        }
        this.nomLocalitat = nomLocalitat;
    }

    @Override
    public String toString() {
        return "Localitat{" +
                "idLocalitat='" + getIdLocalitat() + '\'' +
                ", idIlla=" + getIdIlla() +
                ", nomLocalitat='" + getNomLocalitat() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localitat localitat = (Localitat) o;
        return Objects.equals(getIdLocalitat(), localitat.getIdLocalitat()) && Objects.equals(getIdIlla(), localitat.getIdIlla()) && Objects.equals(getNomLocalitat(), localitat.getNomLocalitat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdLocalitat(), getIdIlla(), getNomLocalitat());
    }
}