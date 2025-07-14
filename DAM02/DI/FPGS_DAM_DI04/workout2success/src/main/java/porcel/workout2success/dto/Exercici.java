/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package porcel.workout2success.dto;

/**
 * Clase dto para los Exercicis de un workout
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class Exercici {

    int id;
    String nomExercici;
    String descripcio;
    String demoFoto;

    /**
     * Constructor
     * 
     * @param id id del ejercicio
     * @param nomExercici nombre del ejercicio
     * @param descripcio descripción del ejercicio
     * @param demoFoto foto del ejercicio
     */
    public Exercici(int id, String nomExercici, String descripcio, String demoFoto) {
        this.id = id;
        this.nomExercici = nomExercici;
        this.descripcio = descripcio;
        this.demoFoto = demoFoto;
    }

    /**
     * Constructor sin identificador
     * 
     * @param nomExercici nombre del ejercicio
     * @param descripcio descripcion del ejercicio
     * @param demoFoto  foto del ejercicio
     */
    public Exercici(String nomExercici, String descripcio, String demoFoto) {
        this.nomExercici = nomExercici;
        this.descripcio = descripcio;
        this.demoFoto = demoFoto;
    }

    /**
     * Getter del ID del ejercicio
     * 
     * @return ID del ejercicio 
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del ID del ejercicio
     * 
     * @param id ID del ejercicio a implantar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del nombre del ejercicio
     * 
     * @return devuelve el nombre dele ejercicio
     */
    public String getNomExercici() {
        return nomExercici;
    }

    /**
     * Setter del nombre del ejercicio
     * 
     * @param nomExercici nombre del ejercicio a implantar
     */
    public void setNomExercici(String nomExercici) {
        this.nomExercici = nomExercici;
    }

    /**
     * Getter de la descripción del ejercicio
     * 
     * @return devuelve la descripción del ejercicio 
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Setter de la descripción del ejercicio
     * 
     * @param descripcio descripción a implantar
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Getter de la foto del ejercicio
     * 
     * @return devuelve la foto del ejercicio 
     */
    public String getDemoFoto() {
        return demoFoto;
    }

    /**
     * Setter de la foto del ejercicio
     * 
     * @param demoFoto foto s implantar
     */
    public void setDemoFoto(String demoFoto) {
        this.demoFoto = demoFoto;
    }

    @Override
    public String toString() {
        return descripcio;
    }
}
