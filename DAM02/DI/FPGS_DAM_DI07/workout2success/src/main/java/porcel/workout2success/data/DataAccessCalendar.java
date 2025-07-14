package porcel.workout2success.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import porcel.workout2success.dto.Workout;
import porcel.workout2success.dto.WorkoutCalendar;

/**
 * <p>Clase dedicada a realizar la conexión con la BBDD para el calendario</p>
 * <p>Proporciona métodos para recuperar entrenamientos almacenados en la BBDD</p>
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class DataAccessCalendar {

    private static String connectionUrl = "jdbc:sqlserver://simulapsqlserver.database.windows.net:1433;database=simulapdb25;user=simulapdbadmin@simulapsqlserver;password=Pwd1234.;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    /**
     * Realiza la conexión a la BBDD usando los datos del archivo aplication.properties
     * 
     * @return Conexión a la BBDD
     * @throws SQLException  Si ocurre un error al establecer la conexión
     */
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

    /**
     * Método para obtener una lista de fechas que conteinen workouts en un mes concreto
     * 
     * @param year Ano de los workouts a consultar
     * @param month Mes de los workouts a consultar
     * @return Lista de workouts de ese ano y mes
     */
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

    /**
     * Lista de workouts para un día en concreto
     * 
     * @param date día al que recuperar workouts
     * @return Lista de workouts del día
     */
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
