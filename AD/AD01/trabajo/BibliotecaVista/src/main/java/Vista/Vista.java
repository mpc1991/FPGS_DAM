package Vista;

import org.example.controlador.ControladorBiblioteca;
import org.example.model.Llibre;
import org.example.model.Autor;

import java.util.List;

public class Vista {
    private ControladorBiblioteca controlador;

    // Constructor que recibe el controlador
    public Vista(ControladorBiblioteca controlador) {
        this.controlador = controlador;
    }

    // Metodo para mostrar todos los autores
    public void mostrarAutores() {
        List<Autor> autores = controlador.getListaAutores();
        System.out.println("Lista de Autores:");
        for (Autor autor : autores) {
            System.out.println("- " + autor.getNombre() + " " + autor.getApellido());
        }
    }

    // Metodo para mostrar todos los libros
    public void mostrarLibros() {
        List<Llibre> libros = controlador.getListaLlibres();
        System.out.println("Lista de Libros:");
        for (Llibre llibre : libros) {
            System.out.println("- Título: " + llibre.getTitulo() + ", Año: " + llibre.getAno() + ", Autor/es: " + llibre.getAutores());
        }
    }

    // Metodo principal para ejecutar la vista
    public void ejecutar() {
        try {
            mostrarAutores();
            mostrarLibros();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}