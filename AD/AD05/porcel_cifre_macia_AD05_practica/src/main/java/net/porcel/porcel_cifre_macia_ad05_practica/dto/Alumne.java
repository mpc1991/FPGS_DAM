/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_ad05_practica.dto;

/**
 *
 * @author seek_
 */
public class Alumne {
    private String nom;
    private String NIF;
    private String llinatges;
    private Contacte contacte;
    private boolean baixa;

    public Alumne(String nom, String NIF, String llinatges, Contacte contacte, boolean baixa) {
        this.nom = nom;
        this.NIF = NIF;
        this.llinatges = llinatges;
        this.contacte = contacte;
        this.baixa = baixa;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getLlinatges() {
        return llinatges;
    }

    public void setLlinatges(String llinatges) {
        this.llinatges = llinatges;
    }

    public Contacte getContacte() {
        return contacte;
    }

    public void setContacte(Contacte contacte) {
        this.contacte = contacte;
    }

    public boolean isBaixa() {
        return baixa;
    }

    public void setBaixa(boolean baixa) {
        this.baixa = baixa;
    }

    
    
}
