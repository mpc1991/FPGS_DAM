package org.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class canviaCaracters {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            // Leemos los caracters a quitar y poner y el delomotador.
            String caracterQuitar = br.readLine();
            String caracterPoner = br.readLine();
            String delimitador = br.readLine();

            StringBuilder htmlContent = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals(delimitador)) { // Comprobamos el delimitador
                    break; // Salimos del bucle si encontramos el delimitador
                }
                htmlContent.append(line).append("\n");
            }

            for (int i = 0; i < htmlContent.length(); i++) {
                if (htmlContent.charAt(i) == caracterQuitar.charAt(0)) {
                    htmlContent.setCharAt(i, caracterPoner.charAt(0)); // Sustitución
                }
            }

            try (FileWriter writer = new FileWriter("encrypted.txt")){
                writer.write(htmlContent.toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            System.out.println("Arxiu creat amb èxit");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
