package porcel.workout2success.data;

import java.sql.SQLException;

/**
 * Interfaz genérica para las operaciones hacia la BBDD
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 * @param <T> Objeto a tratar
 */
public interface DAO<T> {

    /**
     * guardar datos en la BBDD
     * 
     * @param entity entidad que guardar en la BBDD
     * @return Cantidad de filas afectadas
     * @throws SQLException Si ocurre un error
     */
    int save(T entity) throws SQLException;

    /**
     * insertar datos en la BBDD
     * @param entity entidad que guardar en la BBDD
     * @return Cantidad de filas afectadas
     * @throws SQLException Si ocurre un error
     */
    int insert(T entity) throws SQLException;

    /**
     * Actualizar datos en la BBDD
     * 
     * @param entity entidad a actualizar
     * @return Cantidad de filas afectadas
     * @throws SQLException Si ocurre un error
     */
    int update(T entity) throws SQLException;

    /**
     * Eliminar datos de la BBDD
     * 
     * @param entity entidad a eliminar
     * @return Cantidad de filas afectadas
     * @throws SQLException Si ocurre un error
     */
    int delete(T entity) throws SQLException;
}
