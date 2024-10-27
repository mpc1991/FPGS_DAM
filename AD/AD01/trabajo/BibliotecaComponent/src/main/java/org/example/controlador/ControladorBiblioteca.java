package org.example.controlador;

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
        Autor autor1 = new Autor("Gabriel", "García Márquez");
        Autor autor2 = new Autor("José", "Verdejo");
        Autor autor3 = new Autor("Aitor", "Velasco");

        // Agregamos autores a la base de datos
        listaAutores.add(autor1);
        listaAutores.add(autor2);
        listaAutores.add(autor3);

        // Creamos y agregamos Libros
        listaLibros.add(new Llibre("Cien años de soledad", 1967, autor1.getNombre() + " " + autor1.getApellido()));
        listaLibros.add(new Llibre("Crónica de una muerte anunciada", 1981, autor1.getNombre() + " " + autor1.getApellido()));
        listaLibros.add(new Llibre("La sombra del viento", 2001, autor2.getNombre() + " " + autor2.getApellido()));
        listaLibros.add(new Llibre("El señor de los anillos", 1954, null)); // Libro sin autor
        listaLibros.add(new Llibre("Libro conjunto", 2020, autor1.getNombre() + " " + autor1.getApellido() + " & " + autor2.getNombre() + " " + autor2.getApellido())); // Libro con múltiples autores
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
            if (nombreCompleto.equals(llibre.getAutor())) {
                librosDeAutor.add(llibre);
            }
        }
        return librosDeAutor; // Devolvemos la lista de libros
    }
}
