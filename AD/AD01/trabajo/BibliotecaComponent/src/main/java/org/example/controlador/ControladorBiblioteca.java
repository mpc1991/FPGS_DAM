package org.example.controlador;

// Código adaptado de Nerea Montoya

import org.example.model.Autor;
import org.example.model.Llibre;
import org.example.ExcepcioBiblioteca;
import java.util.ArrayList;
import java.util.List;

public class ControladorBiblioteca {
    private List <Autor> listaAutores = new ArrayList<>();
    private List <Llibre> listaLibros = new ArrayList<>();

    // Constructor para inicializar la base de datos
    public ControladorBiblioteca() throws ExcepcioBiblioteca{
        inicializarDatos();
    }

    private void inicializarDatos() throws ExcepcioBiblioteca{
        // Creamos Autores
        Autor autor1 = new Autor("Nerea", "Montoya");
        Autor autor2 = new Autor("José", "Verdejo");
        Autor autor3 = new Autor("Aitor", "Velasco");

        // Agregamos autores a la base de datos
        listaAutores.add(autor1);
        listaAutores.add(autor2);
        listaAutores.add(autor3);

        // Creamos y agregamos Libros
        listaLibros.add(new Llibre("Solo Leveling", 2023, List.of(autor1)));
        listaLibros.add(new Llibre("Fairy Tail", 2006, List.of(autor2)));
        listaLibros.add(new Llibre("Bleach", 2024, List.of(autor2)));
        listaLibros.add(new Llibre("El señor de los anillos", 2000, null)); // Libro sin autor
        listaLibros.add(new Llibre("Libro conjunto", 2020, List.of(autor1, autor2))); // Libro con múltiples autores
    }

    // Métodos para obtener listas
    public List<Autor> getListaAutores() { // devolver todos los autores, no es necesario?
        return new ArrayList<>(listaAutores);
    }
    public List<Llibre> getListaLlibres() { // para devolver TODOS los libros
        return new ArrayList<>(listaLibros);
    }

    // Obtener un autor por nombre y apellido
    public Autor getAutor(String nombre, String apellido) {
        for (Autor autor : listaAutores) {
            if (autor.getNombre().equals(nombre) && autor.getApellido().equals(apellido)) {
                return autor;
            }
        }
        return null;
    }

    // Obtener libros de un Autor específico
    public List<Llibre> getListaLibros(Autor autor) {
        List<Llibre> librosDeAutor = new ArrayList<>();
        String nombreCompleto = autor.getNombre() + " " + autor.getApellido();
        for (Llibre llibre : listaLibros) {
            if (nombreCompleto.equals(llibre.getAutores())) {
                librosDeAutor.add(llibre);
            }
        }
        return librosDeAutor; // Devolvemos la lista de libros
    }
}
