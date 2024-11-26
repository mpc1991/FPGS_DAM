/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porcel.exercici1;

import com.porcel.mpcException.MpcException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author seek_
 */
public class Exercici1 {
    
    public static List<String >getDirContent(Path p) throws MpcException{
        
        // Verificar si la ruta es un directorio.
        if (!Files.isDirectory(p)) {
            throw new MpcException ("La ruta debe ser un directorio");
        
        } else {
            // Creamos una lista donde almacenaremos los datos de cada elemento
            List<String> dades = new ArrayList<>();
            
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(p)){
                for (Path entry : ds) {
                    
                    if (Files.isDirectory(entry)){
                        dades.add("\nDirectori");
                        dades.add ("nom" + entry.getFileName().toString());
                    } else {
                        dades.add("\nFitxer");
                        dades.add("Nom: " + entry.getFileName().toString());
                        
                        
                        
                        // verificamos los permisos del archivo.
                        if (Files.isReadable(entry)) {
                            dades.add("És de lectura");
                        } 
                        if (Files.isWritable(entry)) {
                            dades.add("És de escritura");
                        }
                        if (Files.isExecutable(entry)) {
                            dades.add("És executable");
                        }
                        
                        // Es oculto?
                        if (Files.isHidden(entry)) {
                            dades.add("És ocult.");
                        } else {dades.add("No és ocult");}
                        
                        // Propietario
                        UserPrincipal fileOwner = Files.getOwner(entry);
                        dades.add("Owner: " + fileOwner.getName());
                        
                        // Dada darrera modificació
                        dades.add("Darrera modificació: " + Files.getLastModifiedTime(entry).toString());
                    }
                }
            } catch (IOException e) {
                throw new MpcException("Error: error al obtener datos de los elementos del directorio");
            }
            return dades;
        }
    } 
}
