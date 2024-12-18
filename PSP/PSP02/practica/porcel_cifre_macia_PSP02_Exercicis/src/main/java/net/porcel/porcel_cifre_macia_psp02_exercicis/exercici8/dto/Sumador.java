/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici8.dto;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seek_
 */
public class Sumador extends Thread {

    private String nom;
    Object lock;
    Semaphore semaphore;

    public Sumador(String nom, Semaphore semaphore) {
        this.nom = nom;
        this.semaphore = semaphore;
    }

    public void run() {

        try {
            semaphore.acquire();
            sleep(100);

            Comptador.incrementarComptador();
            System.out.println(nom + " Comptador:" + Comptador.getComptador());
            semaphore.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sumador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
