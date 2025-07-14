package net.porcel.workout2calendar.dto;

public class WorkoutCalendar {

    private int id;
    private String userName;
    private String comment;
    private int numberOfExercises;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    @Override
    public String toString() {
        return String.format("Workout [ID: %d, User: %s, Comment: %s, Exercises: %d]",
                id, userName, comment, numberOfExercises);
    }

}
