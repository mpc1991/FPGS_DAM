/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici7.dto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seek_
 */
public class Fil implements Runnable {

    private String nom;
    private static int compteEnrera;
    boolean comptador;
    private final Object lock; // sicronizacion

    public Fil(String nom, int compteEnrera, boolean comptador, Object lock) {
        this.nom = nom;
        this.compteEnrera = compteEnrera;
        this.comptador = comptador;
        this.lock = lock;
    }

    public Fil(String nom, boolean comptador, Object lock) {
        this.nom = nom;
        this.comptador = comptador;
        this.lock = lock;
    }

    @Override
    public void run() {
        if (comptador) {
            while (compteEnrera > 0) {
                synchronized (lock) {
                    System.out.println(nom + " - " + compteEnrera);
                    compteEnrera--;
                    lock.notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Fil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            int aux = compteEnrera;
            while (compteEnrera > 0) {
                synchronized (lock) {
                    try {
                        lock.wait(); 
                        if (compteEnrera == 0) {
                            break;
                        }
                        // Lógica de avisos
                        if (compteEnrera > 0) {
                            if (compteEnrera == 3 * aux / 4) {
                                System.out.println(nom + " - Quedan 3/4 del tiempo.");
                            } else if (compteEnrera == aux / 2) {
                                System.out.println(nom + " - Queda la mitad del tiempo.");
                            } else if (compteEnrera == aux / 3) {
                                System.out.println(nom + " - Queda 1/2 del tiempo.");
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
            System.out.println(nom + " - TIME OUT"); // Finalización del hilo
        }
    }
}
