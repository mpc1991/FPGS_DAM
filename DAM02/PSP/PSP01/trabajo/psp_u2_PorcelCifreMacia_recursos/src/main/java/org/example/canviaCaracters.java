package org.example;

import java.io.*;

public class canviaCaracters {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            // Leemos los caracteres a quitar y poner y el delimitador
            String caracterQuitar = br.readLine();
            String caracterPoner = br.readLine();
            String delimitador = br.readLine();

            StringBuilder htmlContent = new StringBuilder();
            String line;

            // Leemos el contenido HTML hasta encontrar el delimitador
            while ((line = br.readLine()) != null) {
                if (line.equals(delimitador)) {
                    break; // Salimos del bucle si encontramos el delimitador
                }
                htmlContent.append(line).append("\n");
            }

            // Sustituimos los caracteres en el HTML
            for (int i = 0; i < htmlContent.length(); i++) {
                if (htmlContent.charAt(i) == caracterQuitar.charAt(0)) {
                    htmlContent.setCharAt(i, caracterPoner.charAt(0)); // Sustitución
                }
            }

            // Guardamos el contenido modificado en un archivo "encrypted.txt"
            File file = new File("encrypted.txt");
            try (FileWriter writer = new FileWriter(file)) {
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
