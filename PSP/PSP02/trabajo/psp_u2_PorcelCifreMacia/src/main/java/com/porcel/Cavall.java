package com.porcel;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cavall extends Thread {
    private String nom;
    private int velocitat;
    private final int velocidadMax = 70;
    private final int velocidadMin = 15;
    private int metrosCarrera;
    private static Semaphore semaphore;

    public Cavall(String nom, int metrosCarrera, Semaphore semaphore) {
        this.nom = nom;
        this.velocitat = 50;
        this.metrosCarrera = metrosCarrera;
        Cavall.semaphore = semaphore;
    }

    public void run() {
        while (metrosCarrera >= 0) {
            try {
                semaphore.acquire(1);

                if (Carrera.getGonyador() ==  3) {this.interrupt();}

                Random random = new Random();
                int velocitatAux = random.nextInt(11) - 5;
                velocitat = velocitat + velocitatAux; // sumam la velocitat random -5 +5
                if (velocitat > velocidadMax) {
                    velocitat = velocidadMax; // si supuremaos la velocidad máxima, igualamos velocidad actual a velocidad máxima
                } else if (velocitat < velocidadMin) {
                    velocitat = velocidadMin; // si supuremaos la velocidad mínima, igualamos velocidad actual a velocidad mínima
                }

                metrosCarrera = metrosCarrera - velocitat; // restamos la velocidad a los metros que quedan de carrera.

                if (metrosCarrera <= 0) { // si llegamos a la meta, anunciamos el ganador
                    if (Carrera.getGonyador() <= 3) {
                        System.out.println(nom + " ha finalitzat!");
                        Carrera.sumaGonyador(nom);

                        this.interrupt();
                    } else {
                        this.interrupt();
                    }
                }

                Carrera.estatCarrera(nom, metrosCarrera, velocitat); // indicamos el estado de la carrara en el que estamos
                semaphore.release();
                Cavall.sleep(2000);

            } catch (InterruptedException e) {

            } finally {

            }
        }
    }
}
