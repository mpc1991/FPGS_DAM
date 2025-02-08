/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_ad04_treball.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import net.porcel.porcel_cifre_macia_ad04_treball.auxiliars.JPAException;
import net.porcel.porcel_cifre_macia_ad04_treball.dto.Peatge;

/**
 *
 * @author seek_
 */
public class Persistencia {

    String unitatPersistencia;
    private final EntityManagerFactory emf;
    private EntityManager em;

    public Persistencia(String unitatPersistencia) throws JPAException {
        if (unitatPersistencia != null && !unitatPersistencia.trim().isEmpty()) {
            this.unitatPersistencia = unitatPersistencia;
            this.emf = Persistence.createEntityManagerFactory(unitatPersistencia);
            this.em = emf.createEntityManager();
        } else {
            throw new JPAException("La unidad de persistencia no pot ser nulla o buida");
        }
    }

    public String getUnitatPersistencia() {
        return unitatPersistencia;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public void tancarPersistencia() throws JPAException {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }

    // Recibir identificador toll -> devolver peage
    public Peatge getToll(String idToll) throws JPAException {
        Peatge peatge;
        if (idToll == null || idToll.trim().isEmpty()) {
            throw new JPAException("L'identificador del peatge no pot ser null o buit.");
        } else {
            try {
                peatge = em.find(Peatge.class, idToll);
                return peatge;
            } catch (Exception e) {
                throw new JPAException("Error al recuperar peage: " + e.getMessage());
            }
        }
    }
    
    public List<Peatge> getAllPeatgesOrdered() throws JPAException {
        List<Peatge> peatges;
        
        try {
            peatges = em.createNamedQuery("Peatge.findAllOrdered", Peatge.class).getResultList();
        } catch (Exception e) {
            throw new JPAException("Error al recuperer els peatges: " + e.getMessage());
        }
        return peatges;
    }
}
