/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici1;

import net.porcel.porcel_cifre_macia_psp02_exercicis.exercici1.dto.Fils;

/**
 *
 * @author seek_
 */
public class Exercici1 {

    private static int nombreFils = 20;

    public void inicialice() {
        for (int i = 1; i < nombreFils; i++) {
            System.out.println("Fil " + i + " creat");
            Fils fil = new Fils(i);
            Thread a = new Thread(fil);
            a.start();
        }
    }
}
