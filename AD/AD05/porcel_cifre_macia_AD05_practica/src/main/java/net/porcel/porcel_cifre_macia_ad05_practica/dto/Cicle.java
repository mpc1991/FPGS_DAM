/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_ad05_practica.dto;

/**
 *
 * @author seek_
 */
public class Cicle {
    int codi;
    String nom;

    public Cicle(int codi, String nom) {
        this.codi = codi;
        this.nom = nom;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Cicle{" + "codi=" + codi + ", nom=" + nom + '}';
    }
}
