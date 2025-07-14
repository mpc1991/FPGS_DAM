/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package porcel.workout2success.data;

import porcel.workout2success.data.WorkoutDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import porcel.workout2success.data.DataAccess;
import porcel.workout2success.dto.Workout;

/**
 * <p>Interfaz para realizar las interacciones con la BBDD de {@link WorkoutDAO}</p>
 * <p>Contiene los métodos que interactuan con la BBDD</p>
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class WorkoutDAOImpl implements WorkoutDAO {

    /**
     * 
     * Método para obtener todos los Workouts de la BBDD
     * 
     * @return Lista de workouts
     * @throws SQLException si falla la consulta
     */
    @Override
    public List<Workout> getAll() throws SQLException {
        Connection con = DataAccess.getConnection();

        String sql = "SELECT * FROM Workouts;";

        List<Workout> workoutsList = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String forDate = rs.getString("ForDate");
            int userId = rs.getInt("UserId");
            String comments = rs.getString("Comments");

            Workout workout = new Workout(id, forDate, userId, comments);

            workoutsList.add(workout);
        }

        DataAccess.closeResultSet(rs);
        DataAccess.closeConnection(con);
        return workoutsList;
    }

    /**
     * Obtener lista de workouts asignados a un usuario concreto
     * 
     * @param oid ID del usuario del que obtener los workouts
     * @return Lista de workouts
     * @throws SQLException si falla la consulta
     */
    @Override
    public List<Workout> getWorkoutsPerUser(int oid) throws SQLException {
        Connection con = DataAccess.getConnection();

        String sql = "SELECT Workouts.Id, Workouts.ForDate, Workouts.UserId, Workouts.Comments"
                + " FROM Workouts"
                + " WHERE Workouts.UserId=?"
                + " ORDER BY Workouts.ForDate";

        List<Workout> workoutsList = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, oid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String forDate = rs.getString("ForDate");
                int userId = rs.getInt("UserId");
                String comments = rs.getString("Comments");

                Workout workout = new Workout(id, forDate, userId, comments);

                workoutsList.add(workout);
            }
            DataAccess.closeResultSet(rs);
            DataAccess.closeConnection(con);
        }
        return workoutsList;
    }

    /**
     * Método para guardar un workout en la BBDD (todavía no implementado)
     * 
     * @param entity workout a guardar
     * @return
     * @throws SQLException si la consulta falla
     */
    @Override
    public int save(Workout entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * /**
     * Método para añadir un usuario en la BBDD
     * 
     * @param workout workout a añadir
     * @return el Id del nuevo Workout
     * @throws SQLException si ocurre un error en la consulta
     */
    @Override
    public int insert(Workout workout) throws SQLException { //adaptado de chatGPT
        Connection con = DataAccess.getConnection();

        String sql = "INSERT INTO Workouts (ForDate, UserId, Comments) VALUES (?, ?, ?);";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, workout.getForDate());
            ps.setInt(2, workout.getUserId());
            ps.setString(3, workout.getComments());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating workout failed, no ID obtained.");
                    }
                }
            } else {
                throw new SQLException("Creating workout failed, no rows affected.");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw ex;
        } finally {
            DataAccess.closeConnection(con);
        }
    }

    /**
     * Método para actualizar un workout en la BBDD (no implementado aun)
     * 
     * @param entity workout a actualizar
     * @return
     * @throws SQLException si la consulta falla
     */
    @Override
    public int update(Workout entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método para eliminar un workout de la BBDD (todavía no implementado)
     * 
     * @param entity workout a eliminar
     * @return
     * @throws SQLException Si la consulta da error
     */
    @Override
    public int delete(Workout entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método para eliminar un workout específico de la BBDD
     * 
     * @param oid Id del workout a eliminar
     * @return número de filas afectadas
     * @throws SQLException si ocurre err durante la consulta
     */
    @Override
    public int deleteWorkout(int oid) throws SQLException {
        Connection con = DataAccess.getConnection();
        String sql = "DELETE FROM Workouts WHERE Id=?;";
        int affectedRows;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, oid);

            affectedRows = stmt.executeUpdate();
        }
        //DataAccess.closeResultSet(rs);
        DataAccess.closeConnection(con);
        return affectedRows;
    }

}
