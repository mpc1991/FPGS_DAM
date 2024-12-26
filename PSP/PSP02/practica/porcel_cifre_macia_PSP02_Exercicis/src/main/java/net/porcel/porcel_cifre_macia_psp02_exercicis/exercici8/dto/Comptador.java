package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici8.dto;

public class Comptador {
    private static int comptador = 0;

    public static void incrementarComptador() {
        comptador++;
    }

    public static int getComptador() {
        return comptador;
    }
}
