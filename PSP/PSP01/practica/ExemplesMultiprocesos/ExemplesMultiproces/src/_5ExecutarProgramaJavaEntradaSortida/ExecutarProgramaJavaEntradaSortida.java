/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _5ExecutarProgramaJavaEntradaSortida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/** Executam un programa Java, enviam una entrada i obtenim una sortida.
 *
 * @author Yolanda
 */
public class ExecutarProgramaJavaEntradaSortida {
    public static void main(String[] args) throws IOException {
        
        // El que farem serà apart de rebre la sortida del fill, enviar també una entrada.
        
        Runtime r = Runtime.getRuntime();
        
        // Veureu que ja no donam el valor d'entrada per argument. Ara ho donarem mitjançant un Stream (canal de comunicació)
        String [] comanda = {
            "java",
            "-jar",
            "C:\\Users\\Yolanda\\Documents\\NetBeansProjects\\ExemplesMultiproces_recursos\\dist\\ExemplesMultiproces_recursos.jar"
        };
        
        Process p = r.exec(comanda);
        
        // Ara utilitzarem un nou stream per poder escriure dades en el canal de comunicació que tenim des de el pare fins el fill
        // Veiem que ara la comunicació era bidireccional, fins ara només era unidireccional (del fill al pare)
        BufferedWriter entradaProces = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        BufferedReader sortidaProces = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        Scanner scanner = new Scanner(System.in);
        
        // L'usuari introdueix un valor
        System.out.println("Introduir un nombre enter: ");
        String sortida = scanner.nextLine();
        
        // Enviam el valor al fill (fins ara el fill està a l'espera de rebre aquest valor amb un read())
        entradaProces.write(sortida);
        
        scanner.close();
        entradaProces.close();
        
        // Obtenim les dades enviades pel fill.
        System.out.println(sortidaProces.readLine());
        sortidaProces.close();
    }
}
