package net.porcel.examen_presencial_ad;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.porcel.examen_presencial_ad.DataAccess.Persistance;
import net.porcel.examen_presencial_ad.dto.Film;
import net.porcel.examen_presencial_ad.dto.Notificacio;
import net.porcel.examen_presencial_ad_plantilla.Auxiliars.PersException;

public class Examen_presencial_ad {

    public static void main(String[] args) throws PersException, Exception {
        try {
        Persistance persistencia = new Persistance("Biblioteca-PU");
        List<Film>films = persistencia.getActorByNId(1);
        for (Film film : films) {
            System.out.println("Films recuperats: " + film.getTitle());
        }
        
        Path p = Path.of("./films.json");
        persistencia.writeJson(films, p);
        
        Notificacio notificacio = new Notificacio("2asd", "ALERTA TERREMOTO", 1001, true, "http");
        persistencia.addNotificacio(notificacio);
        
        List<Notificacio> notificacions = new ArrayList<>();
        notificacions = persistencia.getAllNotifications();
        for (Notificacio n : notificacions) {
            System.out.println("Notificacio recuperada: " + n );
        }
        
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
    
}
