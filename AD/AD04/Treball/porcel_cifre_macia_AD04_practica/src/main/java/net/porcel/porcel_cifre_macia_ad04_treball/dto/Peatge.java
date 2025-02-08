/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_ad04_treball.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import net.porcel.porcel_cifre_macia_ad04_treball.auxiliars.JPAException;

/**
 *
 * @author seek_
 */
@Entity
@Table(name = "toll")
@NamedQuery(name = "Peatge.findAllOrdered", query = "SELECT p FROM Peatge p ORDER BY p.name ASC")
public class Peatge {

    @Id //PK
    @Column(name = "idToll", length = 7, nullable = false)
    String idToll;

    @Column(name = "name", length = 35, nullable = false)
    String name;
    
    // Relaciones OneToMany con Trip
    @OneToMany(mappedBy = "idTollInput", fetch = FetchType.LAZY)
    private List<Trip> tripsAsInput;

    @OneToMany(mappedBy = "idTollOutput", fetch = FetchType.LAZY)
    private List<Trip> tripsAsOutput;

    public Peatge() {
    }

    public Peatge(String idToll, String name) throws JPAException {
        setIdToll(idToll);
        setName(name);
    }

    public String getIdToll() {
        return idToll;
    }

    public void setIdToll(String idToll) throws JPAException {
        if (idToll == null || idToll.trim().isEmpty() || idToll.length() > 7) {
            throw new JPAException("idToll no pot ser null, buid o tenir més de 7 carácters");
        } else {
            this.idToll = idToll;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws JPAException {
        if (name == null || name.trim().isEmpty() || name.length() > 35) {
            throw new JPAException("name no pot ser null, buid o tenir més de 35 carácters");
        } else {
            this.name = name;
        }
    }

    public List<Trip> getTripsAsInput() {
        return tripsAsInput;
    }

    public void setTripsAsInput(List<Trip> tripsAsInput) {
        this.tripsAsInput = tripsAsInput;
    }

    public List<Trip> getTripsAsOutput() {
        return tripsAsOutput;
    }

    public void setTripsAsOutput(List<Trip> tripsAsOutput) {
        this.tripsAsOutput = tripsAsOutput;
    }
    
    
}
