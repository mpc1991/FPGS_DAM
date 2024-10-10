package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// Código adaptado de:
// - https://docs.google.com/document/d/1qJNECm7GkZNDVdD657lpuR4w0JuV2eWdMFah1vo_BNw/edit?tab=t.0
// - https://docs.oracle.com/javase/6/docs/api/java/lang/ProcessBuilder.html
// - https://docs.oracle.com/javase/7/docs/api/java/lang/ProcessBuilder.html
// - https://chatgpt.com/

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {

            try {
                String input;
                System.out.println("Introduce un número ('exit' para terminar):");
                input = scan.nextLine();

                if(input.equalsIgnoreCase("exit")) {
                    scan.close(); // cerramos el scanner
                    break;
                }

                // Creamos el proceso hijo y lo ejecutamos.
                ProcessBuilder pb = new ProcessBuilder("java", "../ExercicisMultiproces1_ParellSenar/src/main/java/org/example/ExercicisMultiproces1_ParellSenar.java", input);
                Process p = pb.start();
                p.waitFor(); // Esperar a que el proceso hijo termine

                // Leer la salida del proceso hijo usando try-with-resources.
                try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                    String line;
                    StringBuilder output = new StringBuilder();


                    while ((line = br.readLine()) != null) { // Leer la salida del proceso hijo y almacenarlas en 'line'.
                        output.append(line).append("\n"); // Almacenamos el contenido de 'line' y añadimos un salto de línea.
                    }

                    // Imprimimos lo que nos ha devuelto el proceso hijo y tenemos almacenado en 'output' convirtiendo el StringBuilder a String.
                    System.out.println(output.toString());

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}