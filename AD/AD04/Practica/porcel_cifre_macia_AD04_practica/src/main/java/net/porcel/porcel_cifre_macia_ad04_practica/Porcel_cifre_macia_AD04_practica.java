/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package net.porcel.porcel_cifre_macia_ad04_practica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author seek_
 */
public class Porcel_cifre_macia_AD04_practica {

    public static void main(String[] args) {
        try (
                EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("Biblioteca-PU"); EntityManager em = emf.createEntityManager();) {
            Autor ramon = em.find(Autor.class, 1);
            System.out.println("ramon = " + ramon);
        }
    }
}
