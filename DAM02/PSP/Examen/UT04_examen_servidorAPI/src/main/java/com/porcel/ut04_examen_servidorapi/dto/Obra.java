package com.porcel.ut04_examen_servidorapi.dto;

import java.io.Serializable;

public class Obra implements Serializable {
    private int idObra;
    private String titol;
    private String any;
    private String modalidad;
    private String autor;


    public Obra() {
        super();
    }
    public Obra(int idObra, String titol, String any, String modalidad, String autor) {
        super();
        this.idObra = idObra;
        this.titol = titol;
        this.any = any;
        this.modalidad = modalidad;
        this.autor = autor;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}

