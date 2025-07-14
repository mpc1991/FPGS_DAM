package net.porcel.examen_presencial_ad.dto;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "language_id", nullable = false)
//    private Language language;

    @ColumnDefault("'3'")
    @Column(name = "rental_duration", columnDefinition = "tinyint UNSIGNED not null")
    private Short rentalDuration;

    @ColumnDefault("4.99")
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @ColumnDefault("19.99")
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;
//
//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "last_update", nullable = false)
//    private Instant lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Language getLanguage() {
//        return language;
//    }

//    public void setLanguage(Language language) {
//        this.language = language;
//    }

    public Short getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

//    public Instant getLastUpdate() {
//        return lastUpdate;
//    }
//
//    public void setLastUpdate(Instant lastUpdate) {
//        this.lastUpdate = lastUpdate;
//    }

    public Film() {
    }

    public Film(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\''  +
                ", rentalDuration=" + rentalDuration +
                ", rentalRate=" + rentalRate +
                ", replacementCost=" + replacementCost +
                '}';
    }
}