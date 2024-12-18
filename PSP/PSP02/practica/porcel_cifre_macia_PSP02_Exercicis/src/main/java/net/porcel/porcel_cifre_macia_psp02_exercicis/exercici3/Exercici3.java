/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici3;

import net.porcel.porcel_cifre_macia_psp02_exercicis.exercici3.dto.Exercici3_Factorial;

/**
 *
 * @author seek_
 */
public class Exercici3{
    
    
    public Exercici3() {
    
    }
    
    public void prova1(){
        Exercici3_Factorial filPrimer = new Exercici3_Factorial("Fil 1", 20, Thread.MIN_PRIORITY);
        System.out.println(filPrimer.getNom() + " creat");
        Exercici3_Factorial filSegon = new Exercici3_Factorial("Fil 2", 20, Thread.MAX_PRIORITY);
        System.out.println(filSegon.getNom() + " creat");
        //Thread tSuma = new Thread(filPrimer);
        //Thread tResta = new Thread(filSegon);
        filPrimer.start();
        filSegon.start();
    }
    
    public void prova2(){
        int filsPocaPrioritat = 5;
        int filsMoltaPrioritat = filsPocaPrioritat + 5;
        String nom;
        Exercici3_Factorial fil;
        
        for (int i = 0; i < filsPocaPrioritat; i++) {
            nom = "Fil: " + i;
            fil = new Exercici3_Factorial(nom, 10, 1);
            fil.start();
        }
        
        for (int i = filsPocaPrioritat; i < filsMoltaPrioritat; i++) {
            nom = "Fil: " + i;
            fil = new Exercici3_Factorial(nom, 10, 10);
            fil.start();
        }
    }
}
