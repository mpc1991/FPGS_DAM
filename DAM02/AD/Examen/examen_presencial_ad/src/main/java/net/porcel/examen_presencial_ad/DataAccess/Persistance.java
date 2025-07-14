
package net.porcel.examen_presencial_ad.DataAccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.porcel.examen_presencial_ad.dto.Actor;
import net.porcel.examen_presencial_ad.dto.Film;
import net.porcel.examen_presencial_ad.dto.Notificacio;
import net.porcel.examen_presencial_ad_plantilla.Auxiliars.PersException;

public class Persistance {
    private final EntityManagerFactory emf;
    public Persistance(String unitatPersistencia) throws PersException {
        if (unitatPersistencia != null && !unitatPersistencia.trim().isEmpty()) {
            this.emf = Persistence.createEntityManagerFactory(unitatPersistencia);
        } else {
            throw new PersException("La unidad de persistencia no pot ser nulla o buida");
        }
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    // ex2
    public List<Film> getActorByNId(Integer id) throws Exception{
        EntityManager em = getEntityManager();
        List<Film> films = new ArrayList<>();
        List<Actor> actors = new ArrayList<>();
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f, Actor a WHERE a.id = :id", Film.class);
            query.setParameter("id", id);
            films = query.getResultList();
    
        return films;
    }
    
    // ex3
    public static <T> void writeJson(List<Film> films, Path p) throws IOException{
        ObjectMapper om = new ObjectMapper();
        om.writeValue(p.toFile(), films);
    }
    
    // ex4.2
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://daw.paucasesnovescifp.cat:5432/mporceldb?user=mporcel&password=e43125921r";
        Connection con = DriverManager.getConnection(url);
        return con;
    }
    public static void addNotificacio(Notificacio notificacio) throws PersException {
        try (Connection con = getConnection()) {
            String sql = """
                         INSERT INTO \"ut5-practica\".notificacio
                         VALUES (?,?,?,?,?)
                         """;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, notificacio.getId());
            ps.setString(2, notificacio.getTexte());
            ps.setInt(3, notificacio.getIdUsuari());
            ps.setBoolean(4, notificacio.isIsenviat());
            ps.setString(5, notificacio.getMetodoEnvio());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new PersException("Error al insertar nuevo servidor" + e.getMessage());
        }
    }
    public static List<Notificacio> getAllNotifications() throws PersException {
        List<Notificacio> notificacions = new ArrayList<>();

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM \"ut5-practica\".notificacio";
            PreparedStatement ps = con.prepareStatement(sql);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Campos de la BBDD
                    String codi = rs.getString("codi");
                    String texte = rs.getString("texte");
                    int idUsuari = rs.getInt("id_usuari");
                    boolean servidorActiu = rs.getBoolean("is_enviat");
                    String metode = rs.getString("metode");

                    Notificacio notificacio = new Notificacio(codi, texte, idUsuari, servidorActiu, metode);
                    notificacions.add(notificacio);
                }
            }
            return notificacions;
        } catch (Exception e) {
            throw new PersException("Error al recuperar todos los servidores: " + e.getMessage());
        }
    }
}
