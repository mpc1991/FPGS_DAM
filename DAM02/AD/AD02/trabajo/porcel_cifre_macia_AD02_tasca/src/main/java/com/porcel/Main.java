package com.porcel;

import com.porcel.dto.Curs;
import com.porcel.dto.Modul;
import com.porcel.exercici1.Exercici1;
import com.porcel.exercici2.Exercici2;
import com.porcel.exercici3.Exercici3;
import com.porcel.mpcException.MpcException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    
    public static void main(String[] args) throws MpcException {

        // EXERCICI1
        System.out.println("\nExercici1:");
        String ruta = "";
        Path p = Path.of(ruta);

        try {
            // Llamamos al metodo getDirContent para obtener el contenido del directorio
            List<String> exercici = Exercici1.getDirContent(p);
            System.out.println("Contingut del directory ");

            // Imprimimos cada línea de la lista.
            for (String linea : exercici) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            throw new MpcException("Error: " + e.getMessage());
        }

        // EXERCICI2
        System.out.println("\nExercici2:");
        // creamos unos cuantos modulos
        String codi = "001";
        String nom = "Macia";
        Integer hores = 120;

        String codi2 = "002";
        String nom2 = "Jose";
        Integer hores2 = 180;

        String codi3 = "003";
        String nom3 = "Bernat";
        Integer hores3 = 200;

        Modul modul = new Modul(codi, nom, hores);
        Modul modul2 = new Modul(codi2, nom2, hores2);
        Modul modul3 = new Modul(codi3, nom3, hores3);
        
        // Añadimos los modulos a la lista para poder pasarlos luego al metodo que los va a tratar
        List<Modul> moduls = new ArrayList<>();
        moduls.add(modul);
        moduls.add(modul2);
        moduls.add(modul3);
        
        exercici2DataStreams(moduls);
        exercici2ObjectStreams(moduls);
        
        // EXERCICI3
        String rutaMostrar = "src\\main\\resources\\curs.json";
        String rutaCopiar = "copyCurs.json";
        exercici3JsonMostrar(rutaMostrar, Curs.class);
        exercici3JsonCopiar(rutaMostrar, rutaCopiar, Curs.class);
        
        rutaMostrar = "src\\main\\resources\\Modul.json";
        rutaCopiar = "copyModul.json";
        exercici3JsonMostrar(rutaMostrar, Modul.class);
        exercici3JsonCopiar(rutaMostrar, rutaCopiar, Modul.class);
        
    }
    
    private static void exercici2DataStreams(List<Modul> moduls){
        System.out.println("Exercici2DataStreams:");
        String ruta = "modulosDataStreams.txt";
        Path p = Path.of(ruta);
        
        try {
            Exercici2.setLlistaModulsDataStreams(moduls, p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            List<Modul> modulsRetornats = new ArrayList<>();
            modulsRetornats = Exercici2.getLlistaModulsDataStreams(p);
            
            for (Modul modulRetornat : modulsRetornats) {
                System.out.println(modulRetornat);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void exercici2ObjectStreams(List<Modul> moduls) throws MpcException{
        System.out.println("\nExercici2ObjectStreams:");
        String ruta = "modulosObjectStreams.txt";
        Path p = Path.of(ruta);
        
        try {
            Exercici2.setLlistaModulsObjectStreams(moduls, p);
        } catch (Exception e) {
            throw new MpcException(e.getMessage());
        }
        
        try {
            List<Modul> exerciciObjectStreamList = Exercici2.getLlistaModulsObjectStreams(p);
            for (Modul mod2 : exerciciObjectStreamList) {
                System.out.print(mod2 + "\n");
            }
        } catch (Exception e) {
            throw new MpcException(e.getMessage());
        }
    }
    
    private static <T> void exercici3JsonMostrar(String ruta, Class<T> classe) throws MpcException{
        System.out.println("\nExercici3Json");
        Path p = Path.of(ruta);
        
        System.out.println("Mostra JSON:");
        try {
            T object = Exercici3.getExercici3Json(p, classe);
            System.out.println(object);
        } catch (Exception e) {
            throw new MpcException (e.getMessage());
        }
    }
    
    private static <T> void exercici3JsonCopiar(String origen, String desti, Class<T> classe) throws MpcException{
        System.out.println("\nExercici3Json");
        Path orign = Path.of(origen);
        Path dest = Path.of(desti);
        
        System.out.println("Copiar JSON:");
        try {
            Exercici3.copyExercici3Json(orign, dest, classe);
            System.out.println("Copiado correctamente el json desde " + origen + " a " + desti);
        } catch (Exception e) {
            throw new MpcException (e.getMessage());
        }
    }
}
