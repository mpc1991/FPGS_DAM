package org.example.model;

import org.example.ExcepcioBiblioteca;
import java.util.List;

public class Llibre {
    private String titulo;
    private int ano;
    private List<Autor> autores;

    // Constructor de la clase Llibre
    public Llibre (String titulo, int ano, List<Autor> autores) throws ExcepcioBiblioteca {

        // Validar: El título no puede estar vacío o ser null
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ExcepcioBiblioteca("El título del libro no puede estar vacío.");
        }

        this.titulo = titulo;
        this.ano = ano;
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public String getAutores() {
        if (autores == null || autores.isEmpty()) {
            return "Sin autor.";
        } else {
            return autores.toString();

        }
    }
}
