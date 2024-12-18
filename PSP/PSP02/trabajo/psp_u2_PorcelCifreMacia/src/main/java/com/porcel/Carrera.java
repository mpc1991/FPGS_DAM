package com.porcel;

public class Carrera {
    private static int gonyador = 0;
    private static String missatge;
    private String nom;

    public Carrera() {

    }

    public static void sumaGonyador(String nom){
        switch(gonyador){
            case 1:
                missatge = "\nLa primera posicio és per: " + nom;
                gonyador++;
                break;
            case 2:
                missatge += "\nLa segona posicio és per: " + nom;
                gonyador++;
                break;
            case 3:
                missatge += "\nLa tercera posició és per: " + nom;
                gonyador++;
                
                System.out.println("Resultat de la carrera:");
                System.out.println(missatge);
                break;
            default:
                System.out.println("El gonyador no existe");
        }
    }

    public static int getGonyador() {
        return gonyador;
    }
}
