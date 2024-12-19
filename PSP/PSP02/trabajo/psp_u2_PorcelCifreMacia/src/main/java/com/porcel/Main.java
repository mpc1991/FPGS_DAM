package com.porcel;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    private static int cuantitatCavalls;
    private static int metrosCarrera;
    private static Scanner scn = new Scanner(System.in);
    private static Semaphore semaphore;

    public static void main(String[] args) {
        prepararCavalls();
    }

    private static void prepararCavalls() {
        while (cuantitatCavalls < 10) {
            try {
                System.out.println("Indica la cantidad de cavalls que participaran");
                cuantitatCavalls = scn.nextInt();
                scn.nextLine();
                if (cuantitatCavalls < 10) {
                    System.out.println("Los caballos que van a participar no pueden ser menos de 10");
                }
            } catch (Exception e) {
                System.out.println("La cantidad de caballos se debe indicar con un número entero.");
                e.getMessage();
                throw new RuntimeException(e);
            }
        }
        semaphore = new Semaphore(1, true);
        Carrera.setCuantitatCavalls(cuantitatCavalls);

        while (metrosCarrera < 100) {
            try {
                System.out.println("Indica els KM que té la pista");
                metrosCarrera = scn.nextInt();
                scn.nextLine();
                if (metrosCarrera < 100) {
                    System.out.println("La pista debe tener almenos 100 metros.");
                } else {
                    Carrera.setMetrosTotals(metrosCarrera);
                }
            } catch (Exception e) {
                System.out.println("Se debe introducir un número entero.");
                e.getMessage();
                throw new RuntimeException(e);
            }
        }

        while (true) {
            try {
                for (int i = 1; i <= cuantitatCavalls; i++) {
                    Cavall cavall = new Cavall("Cavall " + i, metrosCarrera, semaphore);
                    cavall.start();
                    //cavall.wait();
                }
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean parar(){
        String continuar = "";
        boolean continuado = false;

        while (!continuar.equals("s") || !continuar.equals("n")) {
            System.out.println("Continuar? s/n");
            continuar = scn.nextLine();

            if (continuar.equalsIgnoreCase("s")) {
                continuado = false;
                break;
            } else if (continuar.equalsIgnoreCase("n")) {
                continuado = true;
                break;
            } else {
                System.out.println("Debes responder con 's' o 'n'");
            }
        }
        return continuado;
    }

}