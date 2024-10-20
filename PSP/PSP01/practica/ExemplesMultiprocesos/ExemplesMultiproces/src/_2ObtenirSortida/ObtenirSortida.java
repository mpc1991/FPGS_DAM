/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _2ObtenirSortida;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Yolanda
 */
public class ObtenirSortida {
    
    /** Executam una comanda i obtenim una sortida d'aquest programa.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        /* Volem executar un programa i obtenir la sortida.
        Farem l'exemple amb TIME /T. Aquest mostrarà l'hora actual. */
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "TIME /T");
        Process p = pb.start();
        
        // Per obtenir la sortida del fill com a entrada
        InputStream sortidaTime = p.getInputStream();
        
        // Rebem les dades per Bytes, però podem utilitzar un BufferedReader i llegir per línies.
        int c;
        while ((c = sortidaTime.read()) != -1) {
            System.out.print((char) c);
        }
        
        // És molt important tancar tots els streams una vegada que ja no els necessitam.
        sortidaTime.close();
    }
}
