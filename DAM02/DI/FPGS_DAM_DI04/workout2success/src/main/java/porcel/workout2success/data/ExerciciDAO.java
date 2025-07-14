/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package porcel.workout2success.data;

import porcel.workout2success.data.DAO;
import java.sql.SQLException;
import java.util.List;
import porcel.workout2success.dto.Exercici;

/**
 * <p>Interfaz que defina las operaciones de acceso a la BBDD para los ejercicios</p>
 * <p> Estiende de {@link DAO}
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public interface ExerciciDAO extends DAO<Exercici> {

    /**
     * Obtiene una lista de todos los ejercicios almacenados en la BBDD
     * 
     * @return Lista de todos los ejercicios de la BBDD
     * @throws SQLException si salta un error en la consulta
     */
    List<Exercici> getAll() throws SQLException;

    /**
     * Obtiene una lista de todos los ejercicios almacenados en la BBDD que perteneces a un Workout específico
     * 
     * @param oid Identificador del Workout
     * @return lista de ejercicios de un workout
     * @throws SQLException si salta error en la consulta
     */
    List<Exercici> getExercicisPerWorkout(int oid) throws SQLException;

    /**
     * Inserta un ejercicio en un workout específico
     * 
     * @param idWorkout id del workout al que añadir el ejercicio
     * @param idExercici id del ejercicio a implmenetar en el workout
     * @return número de filas afectadas
     * @throws SQLException si la consulta da error
     */
    int insertExerciciPerWorkout(int idWorkout, int idExercici) throws SQLException;

    /**
     * Elimina un ejercicio de la BBDD
     * @param oid ID del ejercicio a eliminar
     * @return número de filas afectadas
     * @throws SQLException Si da error la eliminación
     */
    int deleteExercici(int oid) throws SQLException;
}
