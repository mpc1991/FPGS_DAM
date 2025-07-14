package covas.model;

/**
 *
 * @author Toni Covas
 */
public class Exercici {

    private int id;
    private String nomExercici;
    private String descripcio;
    private String demoFoto;

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

}
