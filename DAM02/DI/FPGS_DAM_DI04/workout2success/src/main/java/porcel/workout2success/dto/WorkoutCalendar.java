package porcel.workout2success.dto;

/**
 * Clase usada para los Workouts que aparecen en el calendario
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class WorkoutCalendar {

    private int id;
    private String userName;
    private String comment;
    private int numberOfExercises;

    /**
     * Getter del ID del workout
     * 
     * @return el ID del workout 
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del ID del workout
     * 
     * @param id del workout 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del usuario con el workout asignado
     * 
     * @return usuario con el workout asignado
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter del usuario con el workout asignado
     * 
     * @param userName del usuario
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter del comentario del workout
     * 
     * @return comentario 
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter del comentario del workout
     * 
     * @param comment del workout
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter de la cantidad de ejercicios en el workout
     * 
     * @return cantidad de ejercicios 
     */
    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    /**
     * Setter de la cantidad de ejercicios en el workout
     * 
     * @param numberOfExercises cantidad de ejercicios a implamanetar 
     */
    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    @Override
    public String toString() {
        return String.format("Workout [ID: %d, User: %s, Comment: %s, Exercises: %d]",
                id, userName, comment, numberOfExercises);
    }

}
