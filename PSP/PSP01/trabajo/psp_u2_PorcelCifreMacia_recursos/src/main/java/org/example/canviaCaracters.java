package org.example;

<<<<<<< HEAD
import java.io.*;
=======
<<<<<<< HEAD
import java.io.*;
=======
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
>>>>>>> cbde9fb97379d0d5f8bc01b3ba528fe55b09f656
>>>>>>> 9aedccb47fd820b3583660e1602416d973b5d6aa

public class canviaCaracters {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

<<<<<<< HEAD
            // Leemos los caracteres a quitar y poner y el delimitador
=======
<<<<<<< HEAD
            // Leemos los caracteres a quitar y poner y el delimitador
=======
            // Leemos los caracters a quitar y poner y el delomotador.
>>>>>>> cbde9fb97379d0d5f8bc01b3ba528fe55b09f656
>>>>>>> 9aedccb47fd820b3583660e1602416d973b5d6aa
            String caracterQuitar = br.readLine();
            String caracterPoner = br.readLine();
            String delimitador = br.readLine();

            StringBuilder htmlContent = new StringBuilder();
            String line;

<<<<<<< HEAD
            // Leemos el contenido HTML hasta encontrar el delimitador
            while ((line = br.readLine()) != null) {
                if (line.equals(delimitador)) {
=======
<<<<<<< HEAD
            // Leemos el contenido HTML hasta encontrar el delimitador
            while ((line = br.readLine()) != null) {
                if (line.equals(delimitador)) {
=======
            while ((line = br.readLine()) != null) {
                if (line.equals(delimitador)) { // Comprobamos el delimitador
>>>>>>> cbde9fb97379d0d5f8bc01b3ba528fe55b09f656
>>>>>>> 9aedccb47fd820b3583660e1602416d973b5d6aa
                    break; // Salimos del bucle si encontramos el delimitador
                }
                htmlContent.append(line).append("\n");
            }

<<<<<<< HEAD
            // Sustituimos los caracteres en el HTML
=======
<<<<<<< HEAD
            // Sustituimos los caracteres en el HTML
=======
>>>>>>> cbde9fb97379d0d5f8bc01b3ba528fe55b09f656
>>>>>>> 9aedccb47fd820b3583660e1602416d973b5d6aa
            for (int i = 0; i < htmlContent.length(); i++) {
                if (htmlContent.charAt(i) == caracterQuitar.charAt(0)) {
                    htmlContent.setCharAt(i, caracterPoner.charAt(0)); // Sustitución
                }
            }

<<<<<<< HEAD
            // Guardamos el contenido modificado en un archivo "encrypted.txt"
            File file = new File("encrypted.txt");
            try (FileWriter writer = new FileWriter(file)) {
=======
<<<<<<< HEAD
            // Guardamos el contenido modificado en un archivo "encrypted.txt"
            File file = new File("encrypted.txt");
            try (FileWriter writer = new FileWriter(file)) {
=======
            try (FileWriter writer = new FileWriter("encrypted.txt")){
>>>>>>> cbde9fb97379d0d5f8bc01b3ba528fe55b09f656
>>>>>>> 9aedccb47fd820b3583660e1602416d973b5d6aa
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
