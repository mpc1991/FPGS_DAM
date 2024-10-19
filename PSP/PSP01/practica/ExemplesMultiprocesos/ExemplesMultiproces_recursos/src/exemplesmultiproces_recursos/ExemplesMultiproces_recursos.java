/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exemplesmultiproces_recursos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Yolanda
 */
public class ExemplesMultiproces_recursos {

    /** Realitza l'arrel quadrada i mostra el resultat.
     * Hi ha dues opcions, rebre el valor com a argument o rebre el valor mitjançant un stream
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // Cream un canal de comunicació (stream) per rebre informació.
        // Podeu veure que el procés pare sap qui és el procés fill,
        // però el fill no sap qui és el procés pare. Utilitzarem System.in com a InputStream
        // i System.out com a OutputStream
        // BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        
        // Es queda a l'espera de l'entrada.
        //String num = entrada.readLine();
        
        //entrada.close();
        
        // Es mostra el resultat.
        //System.out.println(Math.sqrt(Integer.parseInt(num)));
        
        // Comentau aquesta línia (i descomentau les altres) si voleu provar ExecutarProgramaJavaEntradaSortida
        System.out.println(Math.sqrt(Integer.parseInt(args[0])));
        
        // Segons com executem aquest programa, els System.in o el System.out poden variar.
        // Per exemple, podem executar aquest programa (Run Project) i veurem que el System.in
        // és el teclat per consola. El System.out és la consola.
        // Si executam aquest programa des de un procés, el System.in i el System.out són el procés pare d'aquest.
    }
    
}
