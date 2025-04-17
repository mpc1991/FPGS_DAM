/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.ut6.practica.component;

/**
 *
 * @author seek_
 */
public class MPCEx extends Exception{
        String message;
    
    public MPCEx(String message) {
        super(message);
    }
    
    public MPCEx(){
        super();
    }
}

