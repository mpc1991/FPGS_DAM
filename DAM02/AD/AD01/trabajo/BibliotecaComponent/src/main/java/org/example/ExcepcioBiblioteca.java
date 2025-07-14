package org.example;

public class ExcepcioBiblioteca extends Exception {
    public ExcepcioBiblioteca(String mensaje) {
        super(mensaje);
    }

    public ExcepcioBiblioteca(String message, Throwable cause){
        super(message, cause);
    }
}
