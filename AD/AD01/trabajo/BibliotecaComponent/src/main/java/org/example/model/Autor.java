package org.example.model;

import org.example.ExcepcioBiblioteca;

public class Autor {
    private String nombre;
    private String apellido;


    public Autor(String nombre, String apellido) throws ExcepcioBiblioteca {
        if (nombre.trim().isEmpty()) {throw new ExcepcioBiblioteca("El nombre no puede estar vacío.");}
        if (apellido.trim().isEmpty()) {throw new ExcepcioBiblioteca("El apellido no puede estar vacío.");}


        this.nombre = nombre;
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
