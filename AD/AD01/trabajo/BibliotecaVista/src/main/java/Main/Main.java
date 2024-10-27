package Main;

import org.example.controlador.ControladorBiblioteca;
import Vista.Vista;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicializa el controlador
            ControladorBiblioteca controlador = new ControladorBiblioteca();
            // Crea la vista
            Vista vista = new Vista(controlador);
            // Ejecuta la vista para mostrar la información
            vista.ejecutar();
        } catch (Exception e) {
            System.out.println("Error inicializando la aplicación: " + e.getMessage());
        }
    }
}
