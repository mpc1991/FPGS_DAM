package porcel.workout2success.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataAccess {

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

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public static void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }
}
