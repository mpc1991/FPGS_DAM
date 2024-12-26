package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class llegirEncrypted {
    public static void main(String[] args) {
        try {
            // Leemos el archivo "encrypted.txt"
            try (BufferedReader fileReader = new BufferedReader(new FileReader("encrypted.txt"))) {
                String line;
                StringBuilder fileContent = new StringBuilder();

                // Leemos cada línea del archivo y lo añadimos al StringBuilder
                while ((line = fileReader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }

                // Enviamos el contenido del archivo al proceso padre
                System.out.println(fileContent.toString());
            } catch (Exception e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
