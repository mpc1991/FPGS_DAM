package porcel.workout2success.dto;

public class Workout {

    private int id;
    private String forDate;
    private int userId;
    private String comments;

    public Workout(int id, String forDate, int userId, String comments) {
        this.id = id;
        this.forDate = forDate;
        this.userId = userId;
        this.comments = comments;
    }

    public Workout(String forDate, int userId, String comments) {
        this.forDate = forDate;
        this.userId = userId;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForDate() {
        return forDate;
    }

    public void setForDate(String forDate) {
        this.forDate = forDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return forDate + " - " + comments;
    }
}
