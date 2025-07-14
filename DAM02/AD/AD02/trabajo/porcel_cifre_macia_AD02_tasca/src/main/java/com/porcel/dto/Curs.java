package com.porcel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import java.util.ArrayList;

public class Curs{
    private String codi;
    private String nom;
    private ArrayList<Modul> moduls;

    public Curs() {
        this(null, null);
    }

    public Curs(String codi, String nom) {
        this.setCodi(codi);
        this.setNom(nom);
        this.setModuls(new ArrayList<>());
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

    public ArrayList<Modul> getModuls() {
        return moduls;
    }

    public void setModuls(ArrayList<Modul> moduls) {
        this.moduls = moduls;
    }

    @Override
    public String toString() {
        return "Curs{" +
                "codi='" + codi + '\'' +
                ", nom='" + nom + '\'' +
                ", moduls=" + moduls +
                '}';
    }

    public void afegirModul(Modul nou) {
        moduls.add(nou);
    }
}
