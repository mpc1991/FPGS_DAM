package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici6.dto;

import java.util.Random;

public class MostrarNombresThread extends Thread {

    private String nom;
    private int cuantitat;
    private boolean controlCantidad;

    public MostrarNombresThread(String nom, int cuantitat) {
        this.nom = nom;
        this.cuantitat = cuantitat;
        this.controlCantidad = true;
    }

    public MostrarNombresThread(String nom) {
        this.nom = nom;
        this.controlCantidad = false;
    }

    public void run() {
        int contador = 0;

        while (true) {
            try {
                Random random = new Random();
                int randomNumber = random.nextInt(100) + 1;
                System.out.println(nom + " - " + randomNumber);
                if (controlCantidad) {
                    contador++;
                    if (contador == cuantitat) {
                        contador = 0;
                        System.err.println(nom + " finalitzat complet");
                        break;
                    }
                }
                
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("interrupció del " + nom);
                break;
            }

        }
    }
}
