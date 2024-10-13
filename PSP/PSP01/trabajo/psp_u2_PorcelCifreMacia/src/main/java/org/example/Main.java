package org.example;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Recursos utilitzats
// https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html

public class Main {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder htmlContent = new StringBuilder();

    public static void main(String[] args) {

        while (true) {
            System.out.println("");
            System.out.println("Indica la pàgina web a modificar (exit para salir): ");
            System.out.println("Exemple: https://paucasesnovescifp.cat");
            String pagina = sc.nextLine().toLowerCase();

            // Validamos (Que empieze por http:// o https://) (que contenga letras, puntos y guiones) (que siga de "." y letras tantas veces como sea necesario)
            String validacio = "https?://[\\w.-]+(\\.[a-z]{2,})+";
            Pattern p = Pattern.compile(validacio);
            Matcher m = p.matcher(pagina);
            boolean b = m.matches();

            if (pagina.equals("exit")){
                System.out.println("Saliendo del programa...");
                break;
            } else if (!b) {
                System.out.println("Debes introducir una URL correcta");
            } else {
                int seleccio = 0;
                while (seleccio != 8) {
                    System.out.println("Selecciona el que vols fer;");
                    System.out.println("");
                    System.out.println("1. Carregar pàgina Web");
                    System.out.println("2. Analitzar el nombre de caràcters");
                    System.out.println("3. Substituir lletra");
                    System.out.println("4. Llegir encrypted.txt");
                    System.out.println("5. Cercar paraules clau");
                    System.out.println("6. Crear arxiu index.html");
                    System.out.println("7. Ejecutar arxiu index.html");
                    System.out.println("8. Sortir");
                    seleccio = sc.nextInt();
                    sc.nextLine();

                    switch (seleccio) {
                        case 1:
                            carregarPaginaWeb(pagina); // Cargar el HTML y guardarlo en la variable global
                            break;
                        case 2:
                            comptarCaracters(); // Función para contar carácteres
                            break;
                        case 3:
                            canviaCaracters();
                            break;
                        case 4:
                            llegirEncrypted();
                            break;
                        case 5:
                            buscarParaula();
                            break;
                        case 6:
                            crearIndex();
                            break;
                        case 7:
                            obrirIndex();
                            break;
                        case 8:
                            System.out.println("Saliendo del menú");
                            break;
                        default:
                            System.out.println("El número debe ser entre el 1 y el 8.");
                    }
                }
            }
        }
    }

    public static void carregarPaginaWeb(String pagina) {
        try {

            // Creamos el proceso hijo
            ProcessBuilder pb = new ProcessBuilder("java", "../psp_u2_PorcelCifreMacia_recursos/src/main/java/org/example/carregarPaginaWeb.java");
            Process p = pb.start();


            // Enviamos los datos al proceso hijo
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                writer.write(pagina);
                writer.newLine();
                writer.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Leemos la salida del proceso hijo
            try(BufferedReader br =new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                StringBuilder content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }

                // Almacenamos el contenido HTML en la variable global
                htmlContent = content;
                System.out.println("Salida proceso hijo:");
                System.out.println(htmlContent);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {

        }
    }

    public static void comptarCaracters () {

        if (htmlContent.isEmpty()) {
            System.out.println("Primero debes cargar una página web (opción 1).");
            return;
        }

        System.out.println("Introduce el caracter a contar");
        String caracter = sc.nextLine();

        if (caracter.length() == 0){
            System.out.println("Error: Debes introducir al menos 1 caracter");
            return;
        }        if (caracter.length() != 1) {
            System.out.println("Error: Debes introducir sólo 1 caracter");
            return;
        }

        try {

            // Creamos proceso hijo para contar caracteres
            ProcessBuilder pb = new ProcessBuilder("java", "../psp_u2_PorcelCifreMacia_recursos/src/main/java/org/example/comptarCaracters.java");
            Process p = pb.start();

            // Enviamos los datos al proceso hijo
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                writer.write(caracter);
                writer.newLine();
                writer.write("-"); // Delimitador
                writer.newLine();
                writer.write(htmlContent.toString());
                writer.newLine();
                writer.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Esperamos a que el proceso hijo termine
            p.waitFor();

            // Leemos la salida del proceso hijo
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }

            p.waitFor();

            System.out.println("Salida proceso hijo:");
            System.out.println(content);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void canviaCaracters () {

        System.out.println("Que letra quieres sustituir?");
        String caracterQuitar = "";
        while (caracterQuitar.length() != 1) {
            caracterQuitar = sc.nextLine();

            if (caracterQuitar.length() != 1) {
                System.out.println("Debes introducir una letra a sustituir");
                caracterQuitar = sc.nextLine();
                return;
            }
        }

        System.out.println("por qué letra quieres sustituirla?");
        String caracterPoner = "";
        while (caracterPoner.length() != 1) {
            caracterPoner = sc.nextLine();

            if (caracterPoner.length() != 1) {
                System.out.println("Debes indicar un caracter al que cambiar");
                caracterPoner = sc.nextLine();
                return;
            }
        }

        try {
            // Creamos el proceso hijo
            ProcessBuilder pb = new ProcessBuilder("java", "../psp_u2_PorcelCifreMacia_recursos/src/main/java/org/example/canviaCaracters.java");
            Process p = pb.start();

            // Enviamos los datos al proceso hijo
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                writer.write(caracterQuitar);
                writer.newLine();
                writer.write(caracterPoner);
                writer.newLine();
                writer.write("-");
                writer.newLine();
                writer.write(htmlContent.toString());
                writer.newLine();
                writer.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            p.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }

            p.waitFor();

            // Asignamos nuevo resultado de la página web e imprimimos resultado
            //htmlContent = content;
            System.out.println("Salida proceso hijo:");
            System.out.println(content);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void llegirEncrypted () {
        try {
            // Creamos el proceso hijo
            ProcessBuilder pb = new ProcessBuilder("java", "../psp_u2_PorcelCifreMacia_recursos/src/main/java/org/example/llegirEncrypted.java");
            Process p = pb.start();

            // Leemos la salida del proceso hijo (el contenido del archivo encrypted.txt)
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

            // Esperamos a que el proceso hijo termine
            p.waitFor();

            // Mostramos el contenido del archivo por consola
            System.out.println("Contingut de l'arxiu encrypted.txt:");
            System.out.println(fileContent.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void buscarParaula() {
        System.out.println("Introduce una palabra para buscar:");
        String paraula = sc.nextLine();

        if (!paraula.matches("\\w+")) {
            System.out.println("Error: Debes introducir una única palabra válida.");
            return;
        }

        try {
            // Creamos el proceso hijo
            ProcessBuilder pb = new ProcessBuilder("java", "../psp_u2_PorcelCifreMacia_recursos/src/main/java/org/example/buscarParaula.java");
            Process p = pb.start();

            // Enviamos los datos al proceso hijo
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                writer.write(paraula);
                writer.newLine();
                writer.write("-"); // Delimitador
                writer.newLine();
                writer.write(htmlContent.toString());
                writer.newLine();
                writer.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Esperamos a que el proceso hijo termine
            p.waitFor();

            // Leemos la salida del proceso hijo
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }

            // Mostramos el resultado de la búsqueda
            System.out.println("Resultado del proceso hijo:");
            System.out.println(content.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearIndex() {
        // Comprobar si el archivo encrypted.txt existe
        File encryptedFile = new File("encrypted.txt");

        if (!encryptedFile.exists()) {
            System.out.println("El archivo encrypted.txt no existe.");
            System.out.println("¿Deseas realizar la opción 3 (sustituir caracteres) para crear encrypted.txt? (sí/no)");
            String respuesta = sc.nextLine().toLowerCase();

            if (respuesta.equals("sí")) {
                canviaCaracters(); // Ejecutar la opción 3 para crear encrypted.txt
            } else {
                System.out.println("Operación cancelada.");
                return;
            }
        }

        try {
            // Crear proceso hijo para generar index.html
            ProcessBuilder pb = new ProcessBuilder("java", "../psp_u2_PorcelCifreMacia_recursos/src/main/java/org/example/crearIndex.java");
            Process p = pb.start();

            // Leer la salida del proceso hijo
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }

            // Mostrar el mensaje del proceso hijo
            System.out.println("Resultado del proceso hijo:");
            System.out.println(content.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void obrirIndex() {
        // Comprobar si el archivo index.html existe
        File indexFile = new File("index.html");

        if (!indexFile.exists()) {
            System.out.println("El archivo index.html no existe.");
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