package porcel.workout2success.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Clase dedicada a realizar la conexión con la BBDD
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class DataAccess {

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
            properties.load(DataAccess.class.getClassLoader().getResourceAsStream("properties/aplication.properties"));
            String connectionUrl = properties.getProperty("connectionUrl");

            connection = DriverManager.getConnection(connectionUrl);

        } catch (Exception e) {
        }
        return connection;
    }

    /**
     * Método para cerrar la conexión a la BBDD
     * 
     * @param connection Conexión a cerrar
     * @throws SQLException Si ocurre un error
     */
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    /**
     * Método para cerrar el statement
     * 
     * @param statement el statement a cerrar
     * @throws SQLException si ocurre un error
     */
    public static void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }

    /**
     * Método para cerrar el preparedStatement
     * 
     * @param preparedStatement el preparedStatement a cerrar
     * @throws SQLException Si ocurre un error
     */
    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    /**
     * Método para cerrar el resultSet
     * 
     * @param resultSet el resultSet a cerrar
     * @throws SQLException Si ocurre un error
     */
    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }
}
