package com.porcel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Modul implements Serializable {
    private String codi;
    private String nom;
    private Integer hores;

    public Modul() {
        this(null, null, 0);
    }

    public Modul(String codi, String nom, int hores) {
        this.setCodi(codi);
        this.setNom(nom);
        this.setHores(hores);
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getHores() {
        return hores;
    }

    public void setHores(Integer hores) {
        this.hores = hores;
    }

    @Override
    public String toString() {
        return "Modul{" +
                "codi='" + codi + '\'' +
                ", nom='" + nom + '\'' +
                ", hores=" + hores +
                '}';
    }
}