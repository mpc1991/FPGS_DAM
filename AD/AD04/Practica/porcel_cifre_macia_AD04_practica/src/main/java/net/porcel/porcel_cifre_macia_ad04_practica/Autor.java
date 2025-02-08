/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_ad04_practica;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Arrays;

@Entity
@Table(name = "AUTORS")
public class Autor {

    @Id
    @Column(name = "ID_AUT", nullable = false)
    private Integer id;
    @Column(name = "NOM_AUT", length = 100)
    private String nomAut;
    @Column(name = "DNAIX_AUT")
    private Instant dnaixAut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomAut() {
        return nomAut;
    }

    public void setNomAut(String nomAut) {
        this.nomAut = nomAut;
    }

    public Instant getDnaixAut() {
        return dnaixAut;
    }

    public void setDnaixAut(Instant dnaixAut) {
        this.dnaixAut = dnaixAut;
    }

    @Override
    public String toString() {
        return "Autor{"
                + "id=" + id
                + ", nomAut='" + nomAut + '\''
                + ", dnaixAut=" + dnaixAut
                //+ ", imgAut=" + Arrays.toString(imgAut)
                + '}';
    }
}
