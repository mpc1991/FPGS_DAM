/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnovescifp.spaddd.ut4;

import cat.paucasesnovescifp.spadd.ut4.controller.Persistencia;
import cat.paucasesnovescifp.spaddd.ut4.model.Aspirant;
import cat.paucasesnovescifp.spaddd.ut4.model.Illa;
import cat.paucasesnovescifp.spaddd.ut4.model.Localitat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author seek_
 */
public class Main {

    public static void main(String[] args) throws Exception {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Biblioteca-PU");
//        EntityManager em = emf.createEntityManager();
//
//        Illa illa = em.find(Illa.class, "072");
//        System.out.println("Illa: " + illa);

        // Recuperar localitats
//        List<Localitat> localitats = illa.getLocalitats();
//        System.out.println("Localitats de l'illa:");
//        for (Localitat localitat : localitats) {
//            System.out.println(" - " + localitat.getNomLocalitat());
//        }

        //Obtener TODOS los aspirantes
//        Persistencia persistencia = new Persistencia("Biblioteca-PU");
//        List<Aspirant> aspirants = persistencia.getAspirants();
//
//        for (Aspirant aspirant : aspirants) {
//            System.out.println(aspirant);
//        }

        // Obtener Aspirante por nif
        String nif = "37389863F";
        Persistencia persistencia = new Persistencia("Biblioteca-PU");
        Aspirant aspirant = persistencia.getAspirant(nif);
        System.out.println("Aspirant: " + aspirant);
    }
}
