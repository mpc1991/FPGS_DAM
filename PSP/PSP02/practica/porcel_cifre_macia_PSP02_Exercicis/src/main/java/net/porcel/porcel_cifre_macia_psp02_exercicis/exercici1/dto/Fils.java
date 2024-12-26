/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici1.dto;

import java.util.Random;

/**
 *
 * @author seek_
 */
public class Fils implements Runnable {
    private int nombreFil;
    private String filName;
    
    public Fils(int nombreFil){
        this.nombreFil = nombreFil;
        this.filName = "Fil: " + nombreFil;
    }
    
    public void run(){
        Random random = new Random();
        int randomNumber = random.nextInt(100) +1;
        System.out.println("Fil " + nombreFil + " iniciat");
        System.out.println(filName + " valor: "+ randomNumber);
        System.out.println("Fil " + nombreFil + " finalitzat");
    }
}
