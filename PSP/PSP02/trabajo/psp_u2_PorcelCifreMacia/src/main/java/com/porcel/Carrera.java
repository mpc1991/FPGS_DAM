package com.porcel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Carrera {
    private static int gonyador = 1;
    private static String missatge;
    private static int METROS_TOTALS;
    private static String estado = "";
    private static int cuantitatCavalls = 0;
    private static int iteracioCavall = 0;

    public Carrera() {

    }

    public static void sumaGonyador(String nom) {
        switch (gonyador) {
            case 1:
                missatge = "-----------------------------------------";
                missatge += "\n| La primera posicio és per: " + nom + "  |";
                gonyador++;
                break;
            case 2:
                missatge += "\n|--------------------------------------|";
                missatge += "\n| La segona posicio és per: " + nom + "   |";
                gonyador++;
                break;
            case 3:
                missatge += "\n|--------------------------------------|";
                missatge += "\n| La tercera posició és per: " + nom + "  |";
                missatge += "\n-----------------------------------------";

                gonyador++;

                System.out.println("\nResultat de la carrera:");
                System.out.println(missatge);
                System.out.println("\n");

                break;
            default:
                System.out.println(nom + " no entra a podio");
        }
    }

    public static void estatCarrera(String nom, int metrosFaltants, int velocitat) {
        int totalGuiones = 100;
        int porcentajeCompletado = 100 - ((metrosFaltants * 100) / METROS_TOTALS);
        int guionesProgreso = porcentajeCompletado;

        estado = nom + ": ";
        if (cuantitatCavalls < 10 ) { estado += "";}
        //if (cuantitatCavalls > 10 && cuantitatCavalls < 100) {estado += "";}
        for (int i = 0; i < guionesProgreso; i++) {
            if (guionesProgreso > totalGuiones) {
                guionesProgreso = totalGuiones;
            }

            estado += "-";
        }
        estado += ">";

        for (int i = guionesProgreso; i < totalGuiones; i++) {
            estado += " ";
        }
        estado += "META! " + velocitat + "Km/h";
        System.out.println(estado);
        iteracioCavall++;
        if (iteracioCavall == cuantitatCavalls) {
            showDate();
            iteracioCavall = 0;
        }
    }

    public static int getGonyador() {
        return gonyador;
    }

    public static void setMetrosTotals(int metrosTotals) {
        METROS_TOTALS = metrosTotals;
    }

    public static void setCuantitatCavalls(int n) {
        cuantitatCavalls = n;
    }

    public static void showDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" + "\n\n");
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
