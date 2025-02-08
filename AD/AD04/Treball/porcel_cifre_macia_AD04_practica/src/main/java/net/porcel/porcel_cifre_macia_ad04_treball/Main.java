/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package net.porcel.porcel_cifre_macia_ad04_treball;

import java.util.List;
import net.porcel.porcel_cifre_macia_ad04_treball.auxiliars.JPAException;
import net.porcel.porcel_cifre_macia_ad04_treball.controller.Persistencia;
import net.porcel.porcel_cifre_macia_ad04_treball.dto.Peatge;

/**
 *
 * @author seek_
 */
public class Main {

    private static Persistencia persistencia = null;

    public static void main(String[] args) throws JPAException {
        try {
            persistencia = new Persistencia("Peatges-PU");

            // Bloque ejercicio 2.2
            //getToll("AP7-30");
            //getAllPeatgesOrdered();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (persistencia != null) {
                persistencia.tancarPersistencia();
            }
        }
    }

    public static void getToll(String idToll) throws JPAException {
        Peatge peatge = persistencia.getToll(idToll);
        if (peatge != null) {
            System.out.println("Peatge: " + peatge.getName());
        } else {
            System.out.println("l'ID no correspon a cap peatge");
        }
    }

    private static void getAllPeatgesOrdered() throws JPAException {
        List<Peatge> peatges = persistencia.getAllPeatgesOrdered();
        if (peatges != null && !peatges.isEmpty()) {
            for (Peatge peatge : peatges) {
                System.out.println("peatge: " + peatge.getName() + " - ID: " + peatge.getIdToll());
            }
        } else {
            System.out.println("No hi ha peatges");
        }
    }
}
