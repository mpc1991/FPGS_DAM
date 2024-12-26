/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package _1ExecutarComanda;

import java.io.IOException;

/** Executam una comanda. També executam la comanda des de la línia de comandes.
 *
 * @author Yolanda
 */
public class ExecutarComanda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        /* Podem executar diferents programes
           Es crearà un procés fill (instància del programa executat)
        */
        
        /* Podem fer diferents proves. Si posam CMD veurem que no s'obrirà.
        Per executar comandes del CMD, hem d'utilitzar la tercera opció. */
        
        //ProcessBuilder pb = new ProcessBuilder("CALC");
        //ProcessBuilder pb = new ProcessBuilder("NOTEPAD"); 
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "CALC");
        
        Process p = pb.start(); // Executa el programa
        
        // PID del procés fill
        System.out.println(p);
        
    }
    
}
