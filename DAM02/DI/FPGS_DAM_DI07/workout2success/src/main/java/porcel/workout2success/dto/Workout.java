package porcel.workout2success.dto;

/**
 * Clase dto para los Workouts de un usuario
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class Workout {

    private int id;
    private String forDate;
    private int userId;
    private String comments;

    /**
     * Constructor con todos los parametros
     * 
     * @param id del workout
     * @param forDate fecha del workout
     * @param userId id del usuario asignado al workout
     * @param comments del workout
     */
    public Workout(int id, String forDate, int userId, String comments) {
        this.id = id;
        this.forDate = forDate;
        this.userId = userId;
        this.comments = comments;
    }

    /**
     * Constructor sin el ID
     * @param forDate fecha del workout
     * @param userId id del usuario asignado al workout
     * @param comments del workout
     */
    public Workout(String forDate, int userId, String comments) {
        this.forDate = forDate;
        this.userId = userId;
        this.comments = comments;
    }

    /**
     * getter del ID del workout
     * 
     * @return id del workout 
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del ID del workout
     * 
     * @param id ID a implementar 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de la fecha del workout
     * 
     * @return fecha del workout 
     */
    public String getForDate() {
        return forDate;
    }

    /**
     * setter de la fecha del workout
     * 
     * @param forDate fecha a implementar 
     */
    public void setForDate(String forDate) {
        this.forDate = forDate;
    }

    /**
     * Getter del usuario al que se ha asignado el workout
     * 
     * @return ID del usuario con el Workout asignado 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter del usuario asignado al workout
     * 
     * @param userId id del usuario
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter de los comentarios del workout
     * 
     * @return comentario del workout 
     */
    public String getComments() {
        return comments;
    }

    /**
     * Setter del comentario del workout
     * 
     * @param comments comentario a implantar 
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return forDate + " - " + comments;
    }
}
