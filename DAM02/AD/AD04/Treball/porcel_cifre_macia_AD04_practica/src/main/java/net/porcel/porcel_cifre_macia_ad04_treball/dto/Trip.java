package net.porcel.porcel_cifre_macia_ad04_treball.dto;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "trip", indexes = {
        @Index(name = "trip_tollinput", columnList = "idTollInput"),
        @Index(name = "trip_tolloutput", columnList = "idTollOutput")
})
public class Trip {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "inputTime", nullable = false)
    private Instant inputTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTollInput", nullable = false)
    private Peatge idTollInput;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTollOutput")
    private Peatge idTollOutput;

    @Column(name = "outputTime")
    private Instant outputTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Peatge getIdTollInput() {
        return idTollInput;
    }

    public void setIdTollInput(Peatge idTollInput) {
        this.idTollInput = idTollInput;
    }

    public Instant getInputTime() {
        return inputTime;
    }

    public void setInputTime(Instant inputTime) {
        this.inputTime = inputTime;
    }

    public Peatge getIdTollOutput() {
        return idTollOutput;
    }

    public void setIdTollOutput(Peatge idTollOutput) {
        this.idTollOutput = idTollOutput;
    }

    public Instant getOutputTime() {
        return outputTime;
    }

    public void setOutputTime(Instant outputTime) {
        this.outputTime = outputTime;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", inputTime=" + inputTime +
                ", idTollInput=" + idTollInput +
                ", idTollOutput=" + idTollOutput +
                ", outputTime=" + outputTime +
                '}';
    }
}