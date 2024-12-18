/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici4.dto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seek_
 */
public class Exercici4_Factorial extends Thread {

    private String nom;
    private int nombre;
    private volatile boolean running = true;

    public Exercici4_Factorial(String nom, int nombre) {
        this.nom = nom;
        this.nombre = nombre;

    }

    public void run() {
        try {
            System.out.println("Fil - iniciat");
            for (int i = 0; i <= nombre; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Fil - " + nom + " Interrumpit");
                    return;
                }
                long resultat = fibonacci(i);
                System.out.println(resultat);
                Thread.sleep(5000);
            }
            System.out.println("Fil - " + nom + " Finalitzat");
        } catch (InterruptedException ex) {
            System.out.println("Fil - " + nom + " Interrumpit durant el sleep.");
        }
    }

    /**
     * Sucesión fibonacci iterativo en Java
     *
     * @author parzibyte URL:
     * https://parzibyte.me/blog/2019/02/28/sucesion-fibonacci-java-metodo-iterativo-recursivo/
     */
    public long fibonacci(long posicion) {
        long siguiente = 1, actual = 0, temporal = 0;
        for (long x = 1; x <= posicion; x++) {
            // Si no quieres imprimirla, comenta la siguiente línea:
            //System.out.print(actual + ", ");
            temporal = actual;
            actual = siguiente;
            siguiente = siguiente + temporal;
        }
        // Si no quieres imprimirla, comenta la siguiente línea:
        //System.out.println(actual);
        return actual;
    }

    public String getNom() {
        return nom;
    }
}
