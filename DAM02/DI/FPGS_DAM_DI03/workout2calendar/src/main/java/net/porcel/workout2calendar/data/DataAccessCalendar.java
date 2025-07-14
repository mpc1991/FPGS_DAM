package net.porcel.workout2calendar.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.porcel.workout2calendar.dto.WorkoutCalendar;

public class DataAccessCalendar {

    private static String connectionUrl = "jdbc:sqlserver://localhost:1433;database=simulabdb;user=sa;password=Pwd1234;encrypt=false;loginTimeout=10";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        Properties properties = new Properties();

        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static List<LocalDate> getWorkoutDatesForMonth(int year, int month) {
        List<LocalDate> workoutDates = new ArrayList<>();
        String sql = "SELECT CONVERT(DATE, ForDate) AS workout_date FROM Workouts "
                + "WHERE YEAR(ForDate) = ? AND MONTH(ForDate) = ?";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, year);
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                workoutDates.add(rs.getDate("workout_date").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }

        return workoutDates;
    }

    public static List<WorkoutCalendar> getWorkoutsForDay(LocalDate date) {
        List<WorkoutCalendar> workouts = new ArrayList<>();
        String query = """
        SELECT 
                    Workouts.Id AS WorkoutId,
                    Usuaris.Nom AS UserName,
                    Workouts.Comments AS WorkoutComment,
                    COUNT(ExercicisWorkouts.IdExercici) AS NumberOfExercises
                FROM 
                    Workouts
                INNER JOIN 
                    Usuaris ON Workouts.UserId = Usuaris.Id
                LEFT JOIN 
                    ExercicisWorkouts ON Workouts.Id = ExercicisWorkouts.IdWorkout
                WHERE 
                    CONVERT(DATE, Workouts.ForDate) = ?  -- Convierte ForDate a solo la parte de la fecha
                GROUP BY 
                    Workouts.Id, Usuaris.Nom, Workouts.Comments;
    """;

        try (Connection connection = getConnection(); // Obtiene la conexión de la base de datos
                 PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(date)); // Convierte LocalDate a SQL Date
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                WorkoutCalendar workout = new WorkoutCalendar();
                workout.setId(rs.getInt("WorkoutId"));
                workout.setUserName(rs.getString("UserName"));
                workout.setComment(rs.getString("WorkoutComment"));
                workout.setNumberOfExercises(rs.getInt("NumberOfExercises"));
                workouts.add(workout);
            }
        } catch (SQLException e) {
            System.err.println("Error ejecutando la consulta getWorkoutsForDay: " + e.getMessage());
            e.printStackTrace();
        }

        return workouts;
    }

}
