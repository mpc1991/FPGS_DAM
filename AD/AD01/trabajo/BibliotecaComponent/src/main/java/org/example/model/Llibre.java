package org.example.model;

import org.example.ExcepcioBiblioteca;

public class Llibre {
    private String titulo;
    private int ano;
    private String autor;

    // Constructor de la clase Llibre
    public Llibre (String titulo, int ano, String autor) throws ExcepcioBiblioteca {

        // Validar: El título no puede estar vacío o ser null
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ExcepcioBiblioteca("El título del libro no puede estar vacío.");
        }

        this.titulo = titulo;
        this.ano = ano;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public String getAutor() {
        return autor;
    }

}
