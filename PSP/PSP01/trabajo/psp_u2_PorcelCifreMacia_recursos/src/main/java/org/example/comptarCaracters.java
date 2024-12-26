package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class comptarCaracters {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // Leer contenido del caracter recibido
            String caracter = br.readLine();
            String delimitador = br.readLine();

            // Leer contenido del código html recibido
            StringBuilder htmlContent = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals(delimitador)) { // Comprobamos el delimitador
                    break; // Salimos del bucle si encontramos el delimitador
                }
                htmlContent.append(line).append("\n");
            }

            // Contar cuántas veces aparece el caracter en el html
            String html = htmlContent.toString(); // Convertimos a String para contar
            int count = 0;
            char charToCount = caracter.charAt(0);

            for (char c : html.toCharArray()) {
                if (c == charToCount) {
                    count++;
                }
            }

            // Devolver el resultado al proceso padre
            System.out.println("El caracter " + caracter + " ha aparecido " + count + " veces.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
