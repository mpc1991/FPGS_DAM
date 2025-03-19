/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_ad05_practica.exception;

/**
 *
 * @author seek_
 */
public class PersException extends Exception {
    String missatge;
    
    public PersException(String message){
        super(message);
    }
    
    public PersException(){
        super();
    }
    
    
    
}
