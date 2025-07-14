package org.example;

import java.io.File;

public class obrirIndex {
    public static void main(String[] args) {
        // Comprobar si el archivo index.html existe
        File indexFile = new File("index.html");

        if (!indexFile.exists()) {
            System.out.println("Error: El archivo index.html no existe.");
            return;
        }

        try {
            // Crear un proceso para abrir el archivo en el navegador predeterminado
            ProcessBuilder pb;
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Windows
                pb = new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", indexFile.getAbsolutePath());
            } else if (os.contains("mac")) {
                // macOS
                pb = new ProcessBuilder("open", indexFile.getAbsolutePath());
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux o Unix
                pb = new ProcessBuilder("xdg-open", indexFile.getAbsolutePath());
            } else {
                System.out.println("Sistema operativo no soportado.");
                return;
            }

            // Iniciar el proceso
            pb.start();
            System.out.println("El archivo index.html se ha abierto en el navegador.");

        } catch (Exception e) {
            System.out.println("Error al abrir el archivo index.html: " + e.getMessage());
        }
    }
}
