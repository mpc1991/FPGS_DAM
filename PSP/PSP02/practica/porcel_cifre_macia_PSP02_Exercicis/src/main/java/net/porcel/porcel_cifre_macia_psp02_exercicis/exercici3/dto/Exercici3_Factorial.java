/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici3.dto;

/**
 *
 * @author seek_
 */
public class Exercici3_Factorial extends Thread {

    private String nom;
    private int nombre;
    private String prioritatString;

    public Exercici3_Factorial(String nom, int nombre, int prioritat) {
        this.nom = nom;
        this.nombre = nombre;
        setPriority(prioritat);
    }

    public void run() {
        //System.out.println(nom + " iniciat");
        long resultat = fibonacci(nombre);
        System.out.println(nom + " => Prioritat: " + getPriority() + " -Resultat: " + resultat);
        //System.out.println(nom + " finalitzat");
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
