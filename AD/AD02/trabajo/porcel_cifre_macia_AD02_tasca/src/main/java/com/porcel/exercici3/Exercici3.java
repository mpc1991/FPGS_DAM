/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porcel.exercici3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porcel.dto.Curs;
import com.porcel.mpcException.MpcException;
import java.io.IOException;
import java.nio.file.Path;

/**
 *
 * @author seek_
 */
public class Exercici3 {
    public static <T> T getExercici3Json(Path p, Class<T> classe) throws IOException, MpcException{
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;
        
        try {
            t = objectMapper.readValue(p.toFile(), classe);
        } catch (IOException e) {
            throw new MpcException("Error: al leer el json");
        }
        
        return t;
    } 
    
    public static <T> void copyExercici3Json(Path origen, Path desti, Class<T> classe)throws MpcException{
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            
            Object jsonOrigen = objectMapper.readValue(origen.toFile(),classe);
            
            objectMapper.writeValue(desti.toFile(), jsonOrigen);
        } catch (Exception e) {
            throw new MpcException("Error al copiar el JSON.");
        }
    }
}
