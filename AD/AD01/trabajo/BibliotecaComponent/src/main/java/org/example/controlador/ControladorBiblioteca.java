package org.example.controlador;

// Código adaptado casi en su totalidad de ChatGPT.
//  Razón 1: El enunciado me ha parecido indescifrable con el conocimiento que tenemos.
//  Razón 2: No he conseguido entender el proceso de crear una base de datos ficticia.

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

        // Creamos Libros
        Llibre llibre1 = new Llibre("Haikyuu - to the top", 1967, autor1.getNombre() + " " + autor1.getApellido());
        Llibre llibre2 = new Llibre("Dragon Ball", 1985, autor1.getNombre() + " " + autor1.getApellido());
        Llibre llibre3 = new Llibre("El señor de los anillos", 1997, autor2.getNombre() + " " + autor2.getApellido());
        Llibre llibre4 = new Llibre("Harry Potter y la cámara secreta", 1998, autor2.getNombre() + " " + autor2.getApellido());
        Llibre llibre5 = new Llibre("libro de un autor sin rostro", 2020, null);

        //Agregamos Libros a la base de datos
        listaLibros.add(llibre1);
        listaLibros.add(llibre2);
        listaLibros.add(llibre3);
        listaLibros.add(llibre4);
        listaLibros.add(llibre5);
    }

    /*public List<Autor> getListaAutores() { // devolver todos los autores, no es necesario?
        return new ArrayList<>(listaAutores);
    }*/
    public List<Llibre> getListaLlibres() { // para devolver TODOS los libros
        return new ArrayList<>(listaLibros);
    }

    public Autor getAutor(String nombre, String apellido) {
        for (Autor autor : listaAutores) {
            if (autor.getNombre().equals(nombre) && autor.getApellido().equals(apellido)) {
                return autor;
            }
        }
        return null;
    }

    // Obtener libros de un Autor
    public List<Llibre> getListaLibros(Autor autor) {
        List<Llibre> ListaLibros = new ArrayList<>(); // Lista para almacenar libros encontrados del Autor
        for (Llibre llibre : this.listaLibros) {
            // Si el libro no tiene Autor, no lo añadimos
            if (ListaLibros.isEmpty() && llibre.getAutorLibro() == null) {
                // ListaLibros.add(llibre); // descomentar en caso de que sea necesario añadir libros sin autor
            } else if (getListaLlibres().isEmpty() && llibre.getAutorLibro() != null && llibre.getAutorLibro().equals(autor.getNombre() + "" + autor.getApellido())){
                ListaLibros.add(llibre);
            }
        }
        return ListaLibros; // Devolvemos la lista de libros
    }
}
