package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Recursos utilitzats
// https://docs.oracle.com/

public class Programa {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder htmlContent = new StringBuilder(); // Variable global que gordarà la pàgina html
    static List<Process> procesosHijos = new ArrayList<>();  // Lista para guardar los procesos hijos

    public static void main(String[] args) {

        while (true) {
            System.out.println("");
            System.out.println("Indica la pàgina web a modificar (exit per sortir): ");
            System.out.println("Exemple: https://paucasesnovescifp.cat");
            String pagina = sc.nextLine().toLowerCase().trim();

            // Validamos (Que empieze por http:// o https://) (que contenga letras, puntos y guiones) (que siga de "." y letras tantas veces como sea necesario)
            String validacio = "https?://[\\w.-]+(\\.[a-z]{2,})+";
            Pattern p = Pattern.compile(validacio);
            Matcher m = p.matcher(pagina);
            boolean b = m.matches();

            if (pagina.equals("exit")){
                System.out.println("Sortint del programa...");
                break;
            } else if (!b) {
                System.out.println("Tens que introducir una URL correcta");
            } else {
                int seleccio = 0;

                while (seleccio != 8) {
                    try {
                        System.out.println("");
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
                        seleccio = Integer.parseInt(sc.nextLine());

                        switch (seleccio) {
                            case 1:
                                carregarPaginaWeb(pagina); // Carregar el HTML y guardar-lo en la variable global
                                break;
                            case 2:
                                comptarCaracters(); // Funció per a comptar caràcters
                                break;
                            case 3:
                                canviaCaracters(); // Función per a cambiar caràcteres
                                break;
                            case 4:
                                llegirEncrypted(); // Funció per a llegir l'arxiu "Encrypted"
                                break;
                            case 5:
                                buscarParaula(); // Funció per validar si una paràula existéix
                                break;
                            case 6:
                                crearIndex(); // Funció per a crear l'Index
                                break;
                            case 7:
                                obrirIndex(); // Funció per a obrir l'Index emprant el navegador
                                break;
                            case 8:
                                verificarProcesosActivos();
                                System.out.println("Sortint del menú");
                                break;
                            default:
                                System.out.println("El número té que ser entre el 1 i el 8.");
                        }
                    }catch (Exception e) {
                        System.out.println("Debes introducir un número para elegir la opción del menú");
                    }
                }
            }
        }
    }

    public static void carregarPaginaWeb(String pagina) {
        try {

            // Cream el proces fill
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "../psp_u2_PorcelCifreMacia_recursos/out/artifacts/psp_u2_PorcelCifreMacia_recursos_jar/psp_u2_PorcelCifreMacia_recursos.jar", "org.example.carregarPaginaWeb");
            Process p = pb.start();
            procesosHijos.add(p); // Añadimos el proceso hijo a la lista

            // Enviam les dades al proces fill
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                writer.write(pagina);
                writer.newLine();
                writer.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Llegim la sortida del fill
            try(BufferedReader br =new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                StringBuilder content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }

                // Almacenam el contingut dins la variable global.
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
            System.out.println("Primer tens que carregar una página web (opción 1).");
            return;
        }

        System.out.println("Introdueix el caràcter a comptar");
        String caracter = sc.nextLine();

        if (caracter.length() == 0){
            System.out.println("Error: Tens que introduir almenys 1 caràcter");
            return;
        } else if (caracter.length() != 1) {
            System.out.println("Error: Tens que introduir només 1 carácter");
            return;
        }

        try {

            // Creamos proceso hijo para contar caracteres
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "../psp_u2_PorcelCifreMacia_recursos/out/artifacts/psp_u2_PorcelCifreMacia_recursos_jar/psp_u2_PorcelCifreMacia_recursos.jar", "org.example.comptarCaracters");
            Process p = pb.start();
            procesosHijos.add(p); // Añadimos el proceso hijo a la lista

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

            System.out.println("Sortida del proces fill:");
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
                return;
            }
        }

        System.out.println("por qué letra quieres sustituirla?");
        String caracterPoner = "";
        while (caracterPoner.length() != 1) {
            caracterPoner = sc.nextLine();

            if (caracterPoner.length() != 1) {
                System.out.println("Debes indicar un caracter al que cambiar");
                return;
            }
        }

        try {
            // Cream el proces fill
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "../psp_u2_PorcelCifreMacia_recursos/out/artifacts/psp_u2_PorcelCifreMacia_recursos_jar/psp_u2_PorcelCifreMacia_recursos.jar", "org.example.canviaCaracters");
            Process p = pb.start();
            procesosHijos.add(p); // Añadimos el proceso hijo a la lista

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

            System.out.println("Salida proceso hijo:");
            System.out.println(content);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void llegirEncrypted () {
        try {
            // Creamos el proceso hijo
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "../psp_u2_PorcelCifreMacia_recursos/out/artifacts/psp_u2_PorcelCifreMacia_recursos_jar/psp_u2_PorcelCifreMacia_recursos.jar", "org.example.llegirEncrypted");
            Process p = pb.start();
            procesosHijos.add(p); // Añadimos el proceso hijo a la lista

            // Leemos la salida del proceso hijo (el contenido del archivo encrypted.txt)
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

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
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "../psp_u2_PorcelCifreMacia_recursos/out/artifacts/psp_u2_PorcelCifreMacia_recursos_jar/psp_u2_PorcelCifreMacia_recursos.jar", "org.example.buscarParaula");
            Process p = pb.start();
            procesosHijos.add(p); // Añadimos el proceso hijo a la lista

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
        String respuesta = "";

        if (!encryptedFile.exists()) {
            System.out.println("El archivo encrypted.txt no existe.");
            System.out.println("¿Deseas realizar la opción 3 (sustituir caracteres) para crear encrypted.txt? (sí/no)");
            System.out.println("1. Si");
            System.out.println("2. No");
            respuesta = sc.nextLine();

            if (respuesta.equals("1")) {
                canviaCaracters(); // Ejecutar la opción 3 para crear encrypted.txt
            } else {
                System.out.println("Operación cancelada.");
                return;
            }
        }

        try {
            // Crear proceso hijo para generar index.html
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "../psp_u2_PorcelCifreMacia_recursos/out/artifacts/psp_u2_PorcelCifreMacia_recursos_jar/psp_u2_PorcelCifreMacia_recursos.jar", "org.example.crearIndex");
            Process p = pb.start();
            procesosHijos.add(p); // Añadimos el proceso hijo a la lista

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                writer.write(encryptedFile.getAbsolutePath());
                writer.newLine();
                writer.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

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
            Process p = pb.start();
            procesosHijos.add(p); // Añadimos el proceso hijo a la lista
            System.out.println("El archivo index.html se ha abierto en el navegador.");

        } catch (Exception e) {
            System.out.println("Error al abrir el archivo index.html: " + e.getMessage());
        }
    }

    public static void verificarProcesosActivos() {
        boolean hayProcesosActivos = false;

        for (Process proceso : procesosHijos) {
            if (proceso.isAlive()) {
                hayProcesosActivos = true;
                System.out.println("Esperant que el procés fill acabi...");
                proceso.destroy();  // Espera a que el proceso hijo termine
            }
        }

        if (!hayProcesosActivos) {
            System.out.println("Tots els processos han acabat. Pots sortir del programa.");
        }
    }

}