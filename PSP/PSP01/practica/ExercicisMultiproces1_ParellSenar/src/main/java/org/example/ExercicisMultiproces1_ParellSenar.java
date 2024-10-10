package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class ExercicisMultiproces1_ParellSenar {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            //double numero = in.nextDouble();
            int numero = Integer.parseInt(args[0]);

            //int numero = 2;
            if (numero > 0) {

                double resultado = numero % 2;

                if (resultado == 0){
                    System.out.println("El número " + numero + " es par");
                } else {
                    System.out.println("El número " + numero + "  es impar");
                }
            } else {
                System.out.println("El número debe ser positivo");
            }
        } catch (Exception e) {
            System.out.print("El número introducido debe ser entero y positivo.");
        }
    }
}