package cat.paucasesnovescifp.spadd.ut4.controller;

import cat.paucasesnovescifp.spaddd.ut4.auxiliars.JPAException;
import cat.paucasesnovescifp.spaddd.ut4.model.Aspirant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class Persistencia {
    String unitatPersistencia;
    private final EntityManagerFactory emf;

    // Main
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("Biblioteca-PU");
    //EntityManager em = emf.createEntityManager();
    public Persistencia(String unitatPersistencia) throws JPAException {
        if (!unitatPersistencia.trim().isEmpty() && unitatPersistencia != null) {
            this.unitatPersistencia = unitatPersistencia;
            this.emf = Persistence.createEntityManagerFactory(unitatPersistencia);
        } else {
        throw new JPAException("La unidad de persistencia no pot ser nulla o buida");
        }
    }
    public String getUnitatPersistencia() {
        return unitatPersistencia;
    }
    
    public List<Aspirant> getAspirants() throws Exception{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Aspirant> query;

        try {
            query = em.createQuery("SELECT a FROM Aspirant a", Aspirant.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            //em.close();
        }
        return query.getResultList();
    }
    public Aspirant getAspirant(String nif) throws Exception{
        EntityManager em = emf.createEntityManager();
        Aspirant aspirant;
        try {
            aspirant = em.find(Aspirant.class, nif);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            emf.close();
        }
        return aspirant;
    }
}
