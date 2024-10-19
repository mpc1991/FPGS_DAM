package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExercicisMultiproces2_ModificarString {
    public static void main(String[] args) {

        // Leemos el texto enviado por el proceso padre a través de la entrada estándar (System.in)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String texto = br.readLine(); // Leemos el texto enviado por el padre

            // Proceso de transformación de la frase
            texto = texto.toUpperCase();
            texto = texto.replaceAll("[AEIOU]", "_");


            System.out.println("El fill diu: " + texto); //Enviamos la respuesta al padre
            System.out.flush(); // Enviar los datos inmediatamente
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}