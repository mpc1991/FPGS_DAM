/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici4;

/**
 *
 * @author seek_
 */
import java.util.Scanner;
import net.porcel.porcel_cifre_macia_psp02_exercicis.exercici4.dto.Exercici4_Factorial;

public class Exercici4 {

    Scanner scn = new Scanner(System.in);
    private Exercici4_Factorial fil = null;

    public Exercici4() {

    }

    public void inicialize() {
        String sortir = "";
        int n;

        while (true) {
            System.out.println("Introduce N: ");
            sortir = scn.nextLine();

            if (sortir.toLowerCase().equals("exit")) {
                if (fil != null) {
                    fil.interrupt();
                }
                break;
            } else {
                try {
                    n = Integer.parseInt(sortir);
                    fil = new Exercici4_Factorial("Fil ", n);
                    fil.start();

                } catch (Exception e) {
                    System.err.println("Valor incorrecto de 'N'");
                    e.getMessage();
                }
            }
        }
    }
}
