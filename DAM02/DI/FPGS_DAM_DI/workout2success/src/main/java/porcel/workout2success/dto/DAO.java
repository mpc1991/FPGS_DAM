/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package porcel.workout2success.dto;

import java.sql.SQLException;

public interface DAO<T> {

    int save(T entity) throws SQLException;

    int insert(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(T entity) throws SQLException;
}
