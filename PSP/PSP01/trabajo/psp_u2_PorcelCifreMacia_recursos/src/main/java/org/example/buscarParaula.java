package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class buscarParaula {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            // Leemos la palabra a buscar
            String paraula = br.readLine();

            // Validamos que sea una sola palabra
            if (!paraula.matches("\\w+")) {
                System.out.println("Error: Debes introducir una única palabra válida.");
                return;
            }

            // Leemos el delimitador
            String delimitador = br.readLine();

            // Leemos el contenido HTML hasta el delimitador
            StringBuilder htmlContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(delimitador)) {
                    break;
                }
                htmlContent.append(line).append("\n");
            }

            // Buscamos la palabra en el contenido HTML
            String html = htmlContent.toString();
            if (html.contains(paraula)) {
                System.out.println("La palabra '" + paraula + "' existe al menos una vez en el código HTML.");
            } else {
                System.out.println("La palabra '" + paraula + "' no se ha encontrado en el código HTML.");
            }

        } catch (Exception e) {
            System.out.println("Error durante la ejecución del proceso hijo: " + e.getMessage());
        }
    }
}
