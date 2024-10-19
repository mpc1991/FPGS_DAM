/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _4ExecutarProgramaJavaSortida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Executam un programa Java i obtenim una sortida.
 *
 * @author Yolanda
 */
public class ExecutarProgramaJavaSortida {

    public static void main(String[] args) throws IOException {
        
        // Ara executarem un programa que hem creat de JAVA
        Runtime r = Runtime.getRuntime();
        
        // Per executar el programa, necessitam primer el seu executable d'aquest programa.
        // Per obtenir un executable, hem d'anar al projecte i donar a l'opcio (Clean and Build).
        // D'aquesta manera es generarà un fitxer .JAR (executable de Java).
        // Si canviam el codi, haurem de generar un nou executable.
        // Quan li doneu a l'opcio Clean and Build, veureu que per consola surt la comanda per executar el programa. java - jar ruta_del_executable.jar
        // Podeu provar la comanda des de el CMD i així executareu el programa Java que hagueu creat.
        String [] comanda = {
            "java",
            "-jar",
            "C:\\Users\\Yolanda\\Documents\\NetBeansProjects\\ExemplesMultiproces_recursos\\dist\\ExemplesMultiproces_recursos.jar",
            "60" // Aquesta darrera línia és un argument del programa
        };
        
        // Executam el programa i obtenir el procés generat pel sistema operatiu.
        // Aquest serà un procés fill del procés actual (procés pare).
        Process p = r.exec(comanda);
        
        // Declaram un stream per obtenir la sortida del fill.
        BufferedReader sortidaProces = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        // Quan feim un read(), quedam a l'espera de que el fill ens enviï dades.
        System.out.println(sortidaProces.readLine());
        
        sortidaProces.close();
    }

}
