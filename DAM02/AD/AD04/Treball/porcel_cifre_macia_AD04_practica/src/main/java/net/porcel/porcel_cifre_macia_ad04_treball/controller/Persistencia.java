/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_ad04_treball.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import net.porcel.porcel_cifre_macia_ad04_treball.auxiliars.JPAException;
import net.porcel.porcel_cifre_macia_ad04_treball.dto.Peatge;
import net.porcel.porcel_cifre_macia_ad04_treball.dto.Trip;

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

    // Devolver lista de Tolls ordenada
    public List<Peatge> getAllPeatgesOrdered() throws JPAException {
        List<Peatge> peatges;

        try {
            peatges = em.createNamedQuery("Peatge.findAllOrdered", Peatge.class).getResultList();
        } catch (Exception e) {
            throw new JPAException("Error al recuperer els peatges: " + e.getMessage());
        }
        return peatges;
    }

    // Recibir un peage y un booleano -> Devovler los viajes que tengan ese toll al inicio o al final dependiendo del booleano
    public List<Trip> getViatgesPerPeatge(Peatge peatge, boolean isInicio) throws JPAException {
        List<Trip> viatges = new ArrayList<>();

        if (peatge == null) {
            throw new JPAException("El peatge no pot ser null");
        }

        if (isInicio) {
            viatges.addAll(peatge.getTripsAsInput()); // Retorna els viatges on el peatge es a l'inici
        } else {
            viatges.addAll(peatge.getTripsAsOutput()); // Retorna els viatges on el peatge es al final
        }

        return viatges;
    }

    public List<Trip> getViatgesPerPeatge(Peatge peatge) throws JPAException {
        try {
            String jpql = "SELECT t FROM Trip t WHERE t.idTollInput = :peatge AND t.idTollOutput = :peatge";
            TypedQuery<Trip> query = em.createQuery(jpql, Trip.class);
            query.setParameter("peatge", peatge);
            return query.getResultList();
        } catch (Exception e) {
            throw new JPAException("Error al obtener los viajes para el peatge: " + peatge.getIdToll() + e.getMessage());
        }
    }
}
