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

    private static void prepararCavalls(){
        semaphore = new Semaphore(1);

        while (cuantitatCavalls < 10) {
            try {
                System.out.println("Indica la cantidad de cavalls que participaran");
                cuantitatCavalls = scn.nextInt();
                scn.nextLine();
                if (cuantitatCavalls < 10) {System.out.println("Los caballos que van a participar no pueden ser menos de 10");}
            } catch (Exception e) {
                System.out.println("La cantidad de caballos se debe indicar con un número entero.");
                e.getMessage();
                throw new RuntimeException(e);
            }

        }
        while (metrosCarrera < 100){
                try {
                    System.out.println("Indica els KM que té la pista");
                    metrosCarrera = scn.nextInt();
                    scn.nextLine();
                    if (metrosCarrera < 100) {System.out.println("LA pista debe tener almenos 100 metros.");}
                } catch (Exception e) {
                    System.out.println("Se debe introducir un número entero.");
                    e.getMessage();
                    throw new RuntimeException(e);
                }
        }

        while (true) {
            try {
                for (int i = 1; i < cuantitatCavalls; i++) {
                    Cavall cavall = new Cavall("Cavall " + i, metrosCarrera, semaphore);
                    cavall.start();

                    cavall.join();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}