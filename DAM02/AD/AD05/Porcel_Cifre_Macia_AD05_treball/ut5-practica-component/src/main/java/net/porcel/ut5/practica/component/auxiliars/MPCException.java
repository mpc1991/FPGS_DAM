/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.ut5.practica.component.auxiliars;

/**
 *
 * @author seek_
 */
public class MPCException extends Exception{
    String message;
    
    public MPCException(String message) {
        super(message);
    }
    
    public MPCException(){
        super();
    }
}
