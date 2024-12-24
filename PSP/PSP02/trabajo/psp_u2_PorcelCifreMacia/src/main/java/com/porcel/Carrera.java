package com.porcel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Carrera {
    private static int gonyador = 1;
    private static String missatge;
    private static int METROS_TOTALS;
    private static String estado;
    private static int totalCavalls = 0;
    private static List<Cavall> cavalls = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void sumaGonyador(Cavall cavall) throws IOException {
        switch (gonyador) {
            case 1:
                missatge = "-----------------------------------------";
                missatge += "\n| La primera posicio és per: " + cavall.getNom();
                gonyador++;
                break;
            case 2:
                missatge += "\n|----------------------------------------";
                missatge += "\n| La segona posicio és per: " + cavall.getNom();
                gonyador++;
                break;
            case 3:
                missatge += "\n|----------------------------------------";
                missatge += "\n| La tercera posició és per: " + cavall.getNom();
                missatge += "\n-----------------------------------------";

                gonyador++;
                System.out.println("\nResultat de la carrera:");
                System.out.println(missatge);
                System.out.println("\n");
                String continuar= "";

                while (!continuar.equalsIgnoreCase("s") && !continuar.equalsIgnoreCase("n")) {
                    System.out.println("\nQuieres continuar la carrera (S/N)");
                    continuar = sc.nextLine();
                    if (!continuar.equalsIgnoreCase("s") && !continuar.equalsIgnoreCase("n")) {
                        System.out.println("\nDebes escribir 's' o 'n'");
                    }
                    if (continuar.equalsIgnoreCase("N")) {
                        Main.acabar();
                    }
                }
                System.out.println("\nsaliendo del bucle while podium");
                break;
            default:
                System.out.println("dafault en notificarpodio" + cavall.getNom());
        }
    }

    public static void setMetrosTotals(int metrosTotals) {
        METROS_TOTALS = metrosTotals;
    }

    public static void showDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.print(simpleDateFormat.format(new Date()) + "\n");
    }

    public static void notificarPasada(Cavall cavall) throws IOException {
        cavalls.add(cavall);
        if (cavalls.size() == totalCavalls) {
            estatCarrera2(cavalls);
            cavalls.clear();
        }
    }

    public static void estatCarrera2(List<Cavall> cavalls) {
        cavalls.sort(Comparator.comparing(Thread::threadId)); //https://stackoverflow.com/questions/16252269/how-to-sort-a-list-arraylist
        estado = "";

        for (Cavall cavall : cavalls) {
            // Calculo de guiones adaptado con ayuda de ChatGPT
            int totalGuiones = 100;
            int guionesProgreso = Math.min(100, Math.max(0, 100 - ((cavall.getMetrosFaltants() * 100) / METROS_TOTALS)));

            estado += cavall.getNom() + ": ";
            if (cavall.getIdCavall() < 10) {
                estado += " ";
            }

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
            int metrosrestantesCarrera = METROS_TOTALS - cavall.getMetrosFaltants();
            if (metrosrestantesCarrera > METROS_TOTALS) {
                metrosrestantesCarrera = METROS_TOTALS;
            }
            estado += "META! " + cavall.getVelocitat() + "Km/h - Metros recorreguts " + metrosrestantesCarrera + "/" + METROS_TOTALS;

            if (cavall.getMetrosFaltants() <= 0) {
                estado += " - FINALIZADO!";
            }
            estado += "\n";
        }
        System.out.print(estado);
        showDate();
    }

    public static void setTotalCavalls(int n){
        Carrera.totalCavalls = n;
    }
    public static void clearContador(){
        gonyador = 1;

    }
}
