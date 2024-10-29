package org.example.model;

import org.example.ExcepcioBiblioteca;

public class Autor {
    private String nombre;
    private String apellido;

    // Constructor
    public Autor(String nombre, String apellido) throws ExcepcioBiblioteca {
        // Validar: el nombre y apellidos no pueden estar vacíos o ser null
        if (nombre.trim().isEmpty()) {
            throw new ExcepcioBiblioteca("El nombre no puede estar vacío.");
        }
        if (apellido.trim().isEmpty()) {
            throw new ExcepcioBiblioteca("El apellido no puede estar vacío.");
        }

        this.nombre = nombre;
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
