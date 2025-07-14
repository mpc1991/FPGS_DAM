/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package porcel.workout2success.data;

import porcel.workout2success.data.DAO;
import java.sql.SQLException;
import java.util.List;
import porcel.workout2success.dto.Usuari;

/**
 * <p>Interfaz que define las operaciones de acceso a la BBDD para los Usuarios</p>
 * <p> Estiende de {@link DAO}
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public interface UsuariDAO extends DAO<Usuari> {

    /**
     * Obtiene un usuario filtrado por email
     * 
     * @param email filtro por el que vamos a obtener el usuario
     * @return Objeto usuario
     * @throws SQLException si da err la consulta
     */
    Usuari get(String email) throws SQLException;

    /**
     * Obtiene una lista de todos los usuarios almacenados en la BBDD
     * 
     * @return Lista de todos los usuarios de la BBDD
     * @throws SQLException si salta un error en la consulta
     */
    List<Usuari> getAll() throws SQLException;

    /**
     * Método para obtener los usuarios del instructor que ha iniciado sesiçon
     * 
     * @param mail identificador del usuario a obtener
     * @return Lista de usuarios
     * @throws SQLException si da error la consulta
     */
    List<Usuari> getMyUsers(String mail) throws SQLException;
}
