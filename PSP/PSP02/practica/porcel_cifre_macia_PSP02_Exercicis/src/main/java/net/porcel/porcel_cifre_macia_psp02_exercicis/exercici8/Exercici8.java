/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici8;

import java.util.concurrent.Semaphore;
import net.porcel.porcel_cifre_macia_psp02_exercicis.exercici8.dto.Sumador;

public class Exercici8{
    private int n = 10;
    private Object lock;
    private Semaphore semaphore;
    
    public Exercici8() {
    }
    
    public void inicialize(){
        semaphore = new Semaphore(1);
        
        for(int i=1; i<= n; i++){
            Sumador fil = new Sumador("Fil: " + i, semaphore);
            fil.start();
        }
    }
}
