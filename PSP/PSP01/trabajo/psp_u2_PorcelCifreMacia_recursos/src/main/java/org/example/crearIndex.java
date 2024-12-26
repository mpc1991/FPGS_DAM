package org.example;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class crearIndex {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            // Leer el contenido de encrypted.txt
            String fileName = br.readLine();
            File encryptedFile = new File(fileName);
            if (!encryptedFile.exists()) {
                System.out.println("Error: El archivo encrypted.txt no existe.");
                return;
            }

            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(encryptedFile))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo encrypted.txt: " + e.getMessage());
                return;
            }

            // Extraer el contenido dentro del <body></body>, permitiendo atributos en la etiqueta <body>
            String content = fileContent.toString();
            Pattern pattern = Pattern.compile("<body[^>]*>(.*?)</body>", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(content);

            String bodyContent = "";
            if (matcher.find()) {
                bodyContent = matcher.group(1).trim(); // Extraemos solo el contenido dentro de <body>
            } else {
                System.out.println("Error: No se encontró contenido dentro de las etiquetas <body></body>.");
                System.out.println("Asegurate de cargar la página web primero (Opción 1)");
                return;
            }

            // Crear y escribir el archivo index.html con el contenido extraído
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("index.html"))) {
                writer.write("<html>\n<head>\n<title>Contenido Body</title>\n</head>\n");
                writer.write("<body>\n");
                writer.write(bodyContent); // Escribimos el contenido del body
                writer.write("\n</body>\n</html>");
            }

            System.out.println("El archivo index.html ha sido creado con éxito.");

        } catch (Exception e) {
            System.out.println("Error durante la ejecución del proceso hijo: " + e.getMessage());
        }
    }
}
