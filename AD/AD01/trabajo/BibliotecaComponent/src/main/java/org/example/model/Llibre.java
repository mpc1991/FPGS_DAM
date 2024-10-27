package org.example.model;

import org.example.ExcepcioBiblioteca;

public class Llibre {
    String tituloLibro;
    int anoLibro;
    String autorLibro;

    public Llibre (String tituloLibro, int anoLibro, String autorLibro) throws ExcepcioBiblioteca {
        if (tituloLibro == null || tituloLibro.trim().isEmpty()) {throw new ExcepcioBiblioteca("El título del libro no puede estar vacío.");}

        this.tituloLibro = tituloLibro;
        this.anoLibro = anoLibro;
        this.autorLibro = autorLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }
    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }
    public int getAnoLibro() {
        return anoLibro;
    }
    public void setAnoLibro(int anoLibro) {
        this.anoLibro = anoLibro;
    }
    public String getAutorLibro() {
        return autorLibro;
    }
    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
    }
}
