/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package porcel.workout2success.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import porcel.workout2success.data.DataAccess;

public class WorkoutDAOImpl implements WorkoutDAO {

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

    @Override
    public int save(Workout entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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

    @Override
    public int update(Workout entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Workout entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
