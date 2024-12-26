/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici7;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.porcel.porcel_cifre_macia_psp02_exercicis.exercici7.dto.Fil;

/**
 *
 * @author seek_
 */
public class Exercici7 {
    private int n = 25;
    Object lock; 
    
    public Exercici7(){}
    
    public void inicialize(){
        lock = new Object();
        
        Fil fil = new Fil("fil1", n, true, lock);
        Fil fil2 = new Fil("Fil 2", false, lock);
        
        Thread filThread = new Thread(fil);
        Thread fil2Thread = new Thread(fil2);
        
        filThread.start();
        fil2Thread.start();
        
        try {
            filThread.join();
            fil2Thread.join();
            
            System.out.println("Programa finalitzat");
        } catch (InterruptedException ex) {
            Logger.getLogger(Exercici7.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
