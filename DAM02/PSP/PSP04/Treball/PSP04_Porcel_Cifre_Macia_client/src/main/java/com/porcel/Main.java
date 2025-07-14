package com.porcel;

import com.porcel.data.dataAccess;

import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    private static int idi;
    private static String ids;
    private static String titol;
    private static String any;
    private static String modalidad;
    private static String autor;

    public static void main(String[] args) throws IOException {
        int select = 0;
        while (select != 7) {

            System.out.println("Tria una opció");
            System.out.println("1. Llistar totes les obres");
            System.out.println("2. Llistar una obre per ID");
            System.out.println("3. Llistar obres per autor");
            System.out.println("4. Crear una nova obra");
            System.out.println("5. Actualitzar una nova obra");
            System.out.println("6. Eliminar una obra per ID");
            System.out.println("7. Sortir");
            select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 1:
                    dataAccess.getObras();
                    break;
                case 2:
                    System.out.println("Ingrese un ID");
                    ids = sc.nextLine();
                    dataAccess.getObrasById(ids);
                    break;
                case 3:
                    System.out.println("Ingrese un autor");
                    autor = sc.nextLine();
                    dataAccess.getObrasByAutor(autor);
                    break;
                case 4:
                    System.out.println("Ingrese el ID");
                    idi = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese el titulo");
                    titol = sc.nextLine();
                    System.out.println("Ingrese el año");
                    any = sc.nextLine();
                    System.out.println("Ingrese la modalidad");
                    modalidad = sc.nextLine();
                    System.out.println("Ingrese el autor");
                    autor = sc.nextLine();

                    dataAccess.createObra(idi,titol,any,modalidad,autor);
                    break;
                case 5:
                    System.out.println("Ingrese el ID");
                    idi = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese el titulo");
                    titol = sc.nextLine();
                    System.out.println("Ingrese el año");
                    any = sc.nextLine();
                    System.out.println("Ingrese la modalidad");
                    modalidad = sc.nextLine();
                    System.out.println("Ingrese el autor");
                    autor = sc.nextLine();

                    dataAccess.updateObra(idi,titol,any,modalidad,autor);
                    break;
                case 6:
                    System.out.println("Ingrese un ID");
                    ids = sc.nextLine();
                    dataAccess.deleteObra(ids);
                    break;
                case 7:
                    System.out.println("Bye Bye mi picoliiiiissima daaaaaama");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}