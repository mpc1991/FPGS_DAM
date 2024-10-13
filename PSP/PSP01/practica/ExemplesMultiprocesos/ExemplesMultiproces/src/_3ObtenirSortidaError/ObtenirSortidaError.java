package _3ObtenirSortidaError;

import java.io.IOException;
import java.io.InputStream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/** Executam una comanda i obtenim la sortida d'error.
 *
 * @author Yolanda
 */
public class ObtenirSortidaError {
    public static void main(String[] args) throws IOException  {

        // Ara posarem una comanda que no existeix
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "whdiuhwi");
        Process p = pb.start();
        
        // Vincularem l'input d'entrada d'error
        // Recordau que cada procés té una sortida estàndar i una sortida d'error
        InputStream sortidaETime = p.getErrorStream();
        
        // Si feis la mateixa prova des de el CMD veureu que el missatge d'error
        // és igual que el que veim executant aquest programa.
        int c;
        while ((c = sortidaETime.read()) != -1) {
            System.out.print((char) c);
        }
        sortidaETime.close();
    }
}
