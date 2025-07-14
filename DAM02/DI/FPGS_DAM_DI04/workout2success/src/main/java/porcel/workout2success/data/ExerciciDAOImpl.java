/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package porcel.workout2success.data;

import porcel.workout2success.data.ExerciciDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import porcel.workout2success.data.DataAccess;
import porcel.workout2success.dto.Exercici;
import static porcel.workout2success.data.DataAccess.getConnection;

/**
 * <p>Interfaz para realizar las interacciones con la BBDD de {@link ExerciciDAO}</p>
 * <p>Contiene los métodos que interactuan con la BBDD</p>
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class ExerciciDAOImpl implements ExerciciDAO {

    @Override
    public int save(Exercici entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Exercici entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Exercici entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Exercici entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método para obtener una lista de ejercicios filtrados por un workout
     * 
     * @param oid identificador del workout a obtener
     * @return Lista de workouts filtrada
     */
    @Override
    public List<Exercici> getExercicisPerWorkout(int oid) {
        ArrayList<Exercici> exercicis = new ArrayList<>();
        String sql = "SELECT ExercicisWorkouts.IdExercici,"
                + " Exercicis.NomExercici, Exercicis.Descripcio, Exercicis.DemoFoto"
                + " FROM ExercicisWorkouts INNER JOIN Exercicis ON ExercicisWorkouts.IdExercici=Exercicis.Id"
                + " WHERE ExercicisWorkouts.IdWorkout=?";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, oid);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("IdExercici");
                String nomExercici = resultSet.getString("NomExercici");
                String descripcio = resultSet.getString("Descripcio");
                String demoFoto = resultSet.getString("DemoFoto");

                Exercici exercici = new Exercici(id, nomExercici, descripcio, demoFoto);
                exercicis.add(exercici);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercicis;
    }

    /**
     * Obtiene la lista de todos los ejercicios de la BBDD
     * 
     * @return lista de todos los ejercicios de la BBDD
     * @throws SQLException si da error en la consulta
     */
    @Override
    public List<Exercici> getAll() throws SQLException {
        Connection con = DataAccess.getConnection();

        String sql = "SELECT * FROM Exercicis;";

        List<Exercici> exercicisList = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("Id");
            String nomExercici = rs.getString("NomExercici");
            String descripcio = rs.getString("Descripcio");
            String demoFoto = rs.getString("DemoFoto");

            Exercici exercici = new Exercici(id, nomExercici, descripcio, demoFoto);

            exercicisList.add(exercici);
        }

        return exercicisList;
    }

    /**
     * Inserta un ejercicio en un workout específico
     * 
     * @param idWorkout id del workout al que añadir el ejercicio
     * @param idExercici id del ejercicio a implmenetar en el workout
     * @return número de filas afectadas
     */
    @Override
    public int insertExerciciPerWorkout(int idWorkout, int idExercici) {
        String sqlInsertExerciciWorkout = "INSERT INTO ExercicisWorkouts (IdWorkout, IdExercici) VALUES (?, ?)";
        Connection conn = null;

        try {
            conn = DataAccess.getConnection(); // Inicializar la conexión
            try (PreparedStatement psInsertExerciciWorkout = conn.prepareStatement(sqlInsertExerciciWorkout)) {
                psInsertExerciciWorkout.setInt(1, idWorkout);
                psInsertExerciciWorkout.setInt(2, idExercici);
                int rowsAffected = psInsertExerciciWorkout.executeUpdate();

                return rowsAffected;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
        return 0;
    }

    /**
     * Elimina un ejercicio de la BBDD
     * @param oid ID del ejercicio a eliminar
     * @return número de filas afectadas
     * @throws SQLException Si da error la eliminación
     */
    @Override
    public int deleteExercici(int oid) throws SQLException {
        Connection con = DataAccess.getConnection();
        String sqlDeleteDependents = "DELETE FROM ExercicisWorkouts WHERE IdExercici = ?;";
        String sqlDeleteExercici = "DELETE FROM Exercicis WHERE Id=?;";
        int affectedRows;

        try {
            con.setAutoCommit(false);

            try (PreparedStatement stmtDependents = con.prepareStatement(sqlDeleteDependents)) {
                stmtDependents.setInt(1, oid);
                stmtDependents.executeUpdate();
            }

            try (PreparedStatement stmt = con.prepareStatement(sqlDeleteExercici)) {
                stmt.setInt(1, oid);
                affectedRows = stmt.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            throw ex;
        }
        DataAccess.closeConnection(con);
        return affectedRows;
    }
}
