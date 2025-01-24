/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnovescifp.spaddd.jdbc.basedades;

import cat.paucasesnovescifp.spaddd.jdbc.auxiliars.JDBCException;
import cat.paucasesnovescifp.spaddd.jdbc.dades.Editor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author seek_
 */
public class DataAccess {

    public static List<Editor> editors = new ArrayList<>(); // Buffer

    // Metodo para realizar la conexion a la BBDD
    public Connection getConnectionPau() throws SQLException {
        String url = "jdbc:mysql://daw.paucasesnovescifp.cat:3306/biblioteca?user=usuari&password=seCret_24";
        Connection con = DriverManager.getConnection(url);

        return con;
    }
    
    // Metodo para realizar la conexion a la BBDD alternativa
    public Connection getConnectionOther() throws SQLException {
        String url = "";
        Connection con = DriverManager.getConnection(url);

        return con;
    }

    // Metodo para añadir editores al buffer verificando primero que no existan
    public void addEditors(Editor editor) throws JDBCException {
        boolean existeix = false;

        for (Editor edit : editors) {
            if (editor.equals(edit)) {
                existeix = true; // Si existe ponemos la flag en true
            }
        }

        if (existeix == false) { // Si la flag sigue en false al llegar a este punto, añadimos el editor a la lista
            editors.add(editor);
        } else {
            throw new JDBCException("l'Editor ja existeix al buffer"); // Si la flag esta en false, lanzamos excepción personalizada
        }
    }
    
    // Metodo para eliminar editores del buffer
    public void deleteEditors(Editor editor) throws JDBCException {
        if (editors.contains(editor)) {
            editors.remove(editor);
        } else {
            throw new JDBCException("l'Editor no existeix al buffer");
        }
    }

    // Metodo para vaciar el buffer
    public void clearEditors() {
        editors.clear();
    }

    // Metodo para añadir editores a la BBDD
    public void addEditorDB() throws JDBCException {
        String sql = "INSERT INTO EDITORS (ID_EDIT, NOM_EDIT, EMAIL_EDIT) VALUES (?, ?, ?) ";

        try (Connection con = getConnectionPau()) {
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement(sql);
            CallableStatement cs = con.prepareCall("{Call get_next_id_edit(?)}"); // método de la BBDD para obtener el siguiente ID disponible.

            cs.registerOutParameter(1, Types.INTEGER);

            try {
                for (Editor editor : editors) {
                    cs.execute();
                    int nextId = cs.getInt(1);  // Obtener el siguiente id disponible
                    editor.setIdentificador(nextId); // Machacamos el id del editor con el primero disponible

                    pst.setInt(1, editor.getIdentificador());
                    pst.setString(2, editor.getNom());
                    pst.setString(3, editor.getCorreu());
                    pst.executeUpdate();
                }
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new JDBCException("Error al insertar los editores: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new JDBCException(e.getMessage());
        } finally {
            editors.clear(); // vaciamos el buffer
        }
    }

    // Método adicional usado para verificar si addEditorDB() funciona correctamente
    public List<String> consultaEditors() throws JDBCException, SQLException {
        List<String> llistaConsultaeditors = new ArrayList<>();

        try (Connection con = getConnectionPau();) {
            String sql = "SELECT * FROM EDITORS";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                llistaConsultaeditors.add(id + " " + nom);
            }
            return llistaConsultaeditors;
        } catch (SQLException e) {
            throw new JDBCException(e.getMessage());
        }
    }

    /* 
    *  Recibiendo el ID, eliminamos el editor de la BBDD.
    *  Para poder eliminarlo, el método primero pondrá la FK de la tabla Llibres en null.
    */
    public void deleteEditorBD(int id) throws JDBCException {
        String sqlLlibres = "UPDATE LLIBRES SET FK_IDEDIT = null WHERE FK_IDEDIT = ?";
        String sqlEditors = "DELETE FROM EDITORS WHERE ID_EDIT = ?";
        try (Connection con = getConnectionPau();) {
            con.setAutoCommit(false);
            try {
                PreparedStatement pstLlibres = con.prepareStatement(sqlLlibres);
                pstLlibres.setInt(1, id);
                int i = pstLlibres.executeUpdate();

                PreparedStatement pstEditors = con.prepareStatement(sqlEditors);
                pstEditors.setInt(1, id);
                int e = pstEditors.executeUpdate();

                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new JDBCException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new JDBCException(e.getMessage());
        }
    }
}
