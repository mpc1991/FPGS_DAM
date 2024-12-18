/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici2;

/**
 *
 * @author seek_
 */
public class Exercici2 implements Runnable {
    private double compte = 100;
    private double registre;
    private String nom;
    private double cuantitat;
    
    public Exercici2(String nom, double cuantitat){
        this.nom = nom;
        this.cuantitat = cuantitat;
    }
    
    public void inicialize(){
        Exercici2 filPrimer = new Exercici2("Fil 1", 10);
        Exercici2 filSegon = new Exercici2("Fil 2", -10);
        Thread tSuma = new Thread(filPrimer);
        System.out.println(nom + " creat");
        Thread tResta = new Thread(filSegon);
        System.out.println(nom + " creat");
        tSuma.start();
        tResta.start();
    }
    
    public void run(){
        System.out.println(nom + " iniciat");
        registre = compte;
        registre = registre + cuantitat;
        compte = registre;
        System.out.println(nom + " => compte: " + compte);
        System.out.println(nom + " finalitzat");
        
    }
}
