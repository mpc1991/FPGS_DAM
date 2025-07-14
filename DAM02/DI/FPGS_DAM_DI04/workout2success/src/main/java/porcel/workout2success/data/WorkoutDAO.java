/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package porcel.workout2success.data;

import porcel.workout2success.data.DAO;
import java.sql.SQLException;
import java.util.List;
import porcel.workout2success.dto.Workout;

/**
 * <p>Interfaz que define las operaciones de acceso a la BBDD para los Workouts</p>
 * <p> Estiende de {@link DAO}
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public interface WorkoutDAO extends DAO<Workout> {

    /**
     * Obtiene todos los workouts de la BBDD
     * 
     * @return Lista de workouts
     * @throws SQLException si da err la consulta
     */
    List<Workout> getAll() throws SQLException;

    /**
     * Obtener lista de workouts asignados a un usuario concreto
     * 
     * @param oid ID del usuario
     * @return Lista de workouts
     * @throws SQLException si falla la consulta
     */
    List<Workout> getWorkoutsPerUser(int oid) throws SQLException;

    int deleteWorkout(int oid) throws SQLException;
}
