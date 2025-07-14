/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnovescifp.spaddd.ut4.auxiliars;

/**
 *
 * @author seek_
 */
public class JPAException extends Exception {
    String message;
    
    public JPAException(String message){
        super(message);
    }
    
    public JPAException(){
        super();
    }
}
