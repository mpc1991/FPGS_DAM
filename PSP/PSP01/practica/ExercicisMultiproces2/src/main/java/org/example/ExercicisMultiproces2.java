package org.example;
// Código adaptado de:
// https://www.w3schools.com/java/ref_string_replaceall.asp


import java.io.*;
import java.util.Scanner;
public class ExercicisMultiproces2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Indique el texto a convertir");
            String input = sc.nextLine();

            // Creamos proceso hijo
            ProcessBuilder pb = new ProcessBuilder("java", "../ExercicisMultiproces2_ModificarString/src/main/java/org/example/ExercicisMultiproces2_ModificarString.java");
            Process p = pb.start();

            // flujo de salida hacia el proceso hijo
            OutputStream os = p.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            pw.println(input); // enviar entrada al hijo
            pw.flush(); // Enviar los datos inmediatamente

            String respuesta;
            respuesta = br.readLine(); //leer la respuesta del hijo

            System.out.println("El pare diu: " + respuesta.toString()); // imprimir la respuesta

            p.waitFor(); // Esperamos que el proceso hijo termine
            p.destroy(); // Destruimos el proceso hijo

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}