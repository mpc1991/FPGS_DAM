package com.porcel;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cavall extends Thread{
    private String nom;
    private int velocitat;
    private final int velocidadMax = 70;
    private final int velocidadMin = 15;
    private int metrosCarrera;
    private static Semaphore semaphore;

    public Cavall(String nom, int metrosCarrera, Semaphore semaphore){
        this.nom = nom;
        this.velocitat = 50;
        this.metrosCarrera = metrosCarrera;
        this.semaphore = semaphore;
    }

    public void run(){
        while(metrosCarrera > 0){
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Random random = new Random();
            int velocitatAux = random.nextInt(5);
            velocitat = velocitatAux;

            if(velocitat > velocidadMax){velocitat = velocidadMax;
            } else if (velocitat < velocidadMin){velocitat = velocidadMin;}

            metrosCarrera = metrosCarrera - velocitat;
            if (metrosCarrera < 0){
                if (Carrera.getGonyador() < 3) {
                    Carrera.sumaGonyador(nom);
                    System.out.println(nom + " ha finalitzat!");
                } else {
                    try {
                        semaphore.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            try {
                semaphore.release();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
