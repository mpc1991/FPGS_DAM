package com.porcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    private static int cuantitatCavalls;
    private static int metrosCarrera;
    private static Scanner scn = new Scanner(System.in);
    private static Semaphore semaphore = new Semaphore(1);
    private static List<Cavall> cavalls = new ArrayList<>();
    private static String tornarJugar;

    public static void main(String[] args) {
        do {
            tornarJugar = "";
            calcularCuantitatCavalls();
            calcularCuantitatMetrosCarrera();
            prepararCavalls();

            clerance();
            do {
                System.out.println("Vols tornar a correr? (S/N)");
                tornarJugar = scn.nextLine();
            } while (!tornarJugar.equalsIgnoreCase("S") && !tornarJugar.equalsIgnoreCase("N"));

        } while (tornarJugar.equalsIgnoreCase("s"));
        System.out.println("Gracies per participar.");
    }

    private static void calcularCuantitatCavalls() {
        while (cuantitatCavalls < 10) {
            try {
                System.out.println("Indica la cantidad de cavalls que participaran");
                cuantitatCavalls = scn.nextInt();
                scn.nextLine();
                if (cuantitatCavalls < 10) {
                    System.out.println("No poden correr menys de 10 cavalls");
                } else {
                    MonitorCavall.setTotalCavalls(cuantitatCavalls);
                    Carrera.setTotalCavalls(cuantitatCavalls);
                    Cavall.setTOTAL_CAVALLS(cuantitatCavalls);
                }
            } catch (Exception e) {
                System.out.println("La cuantitat de cavalls es té que indicar amb un nombre sencer.");
                scn.nextLine();
            }
        }
    }

    private static void calcularCuantitatMetrosCarrera() {
        while (metrosCarrera < 100) {
            try {
                System.out.println("Indica els metros que té la pista");
                metrosCarrera = scn.nextInt();
                scn.nextLine();
                if (metrosCarrera < 100) {
                    System.out.println("La pista té que fer al manco 100 metros.");
                } else {
                    Carrera.setMetrosTotals(metrosCarrera);
                }
            } catch (Exception e) {
                System.out.println("Els metros de pista es tenen que indicar am un nombre sencer.");
                scn.nextLine();
            }
        }
    }

    private static void prepararCavalls() {
        // Creación de hilos para cada cavallo.
        Cavall.setCorrer(true);
        try {
            for (int i = 1; i <= cuantitatCavalls; i++) {
                Cavall cavall = new Cavall("Cavall " + i, metrosCarrera, semaphore, i);
                cavalls.add(cavall);
                cavall.start();
            }
            for (Cavall cavall : cavalls) {
                cavall.join();
            }
            System.out.println("Carrera Finalitzada!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void clerance() {
        cavalls.clear();
        cuantitatCavalls = 0;
        metrosCarrera = 0;
        Carrera.clearContador();
    }

    public static void acabar() {
        for (Cavall cavall : cavalls) {
            while (!cavall.isInterrupted()) {
                cavall.setCorrer(false);
                cavall.interrupt();
            }
        }
    }
}