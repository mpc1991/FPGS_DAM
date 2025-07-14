/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package porcel.workout2success.dto;

/**
 *
 * @author seek_
 */
public class Exercici {

    int id;
    String nomExercici;
    String descripcio;
    String demoFoto;

    public Exercici(int id, String nomExercici, String descripcio, String demoFoto) {
        this.id = id;
        this.nomExercici = nomExercici;
        this.descripcio = descripcio;
        this.demoFoto = demoFoto;
    }

    public Exercici(String nomExercici, String descripcio, String demoFoto) {
        this.nomExercici = nomExercici;
        this.descripcio = descripcio;
        this.demoFoto = demoFoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomExercici() {
        return nomExercici;
    }

    public void setNomExercici(String nomExercici) {
        this.nomExercici = nomExercici;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getDemoFoto() {
        return demoFoto;
    }

    public void setDemoFoto(String demoFoto) {
        this.demoFoto = demoFoto;
    }

    @Override
    public String toString() {
        return descripcio;
    }
}
