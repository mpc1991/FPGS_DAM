/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.ut5.practica.component.controladora;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.porcel.ut5.practica.component.auxiliars.MPCException;
import net.porcel.ut5.practica.component.auxiliars.TractarValors;
import net.porcel.ut5.practica.component.dto.Conection;
import net.porcel.ut5.practica.component.dto.Servidor;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;

/**
 *
 * @author seek_
 */
public class Controladora {

    public static Connection getConnection() throws MPCException {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            // Cargar las propiedades desde el archivo de configuración
            properties.load(Controladora.class.getClassLoader().getResourceAsStream("properties/propietats.properties"));
            String connectionUrl = properties.getProperty("connectionUrl");

            // Establecer la conexión
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            throw new MPCException("Error al obtener la conexión: " + e.getMessage());
        }
        return connection;
    }

    // Método para obtener todos los servidores
    public static List<Servidor> getAllservers() throws MPCException {
        List<Servidor> servidors = new ArrayList<>();

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM \"ut5-practica\".servidors";
            PreparedStatement ps = con.prepareStatement(sql);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Campos de la BBDD
                    String codi = rs.getString("codi");
                    String descripcio = rs.getString("descripcio");
                    boolean servidorActiu = rs.getBoolean("servidor_actiu");

                    // Obtener  array de usuarios 
                    Array usuarisArray = rs.getArray("usuaris"); // retorna una referencia
                    String[] usuaris = new String[0]; // Inicializar vacío por seguridad
                    if (usuarisArray != null) {
                        usuaris = (String[]) usuarisArray.getArray();
                        usuarisArray.free();
                    }
                    
                    // Obtener la cadena del tipo compuesto (conexión)
                    PGobject conectionPGObject = (PGobject) rs.getObject("conection");
                    String conectionValue = conectionPGObject.getValue(); // Obtener el valor como texto

                    // Usar la clase TractarValors para procesar la cadena
                    String[] attributes = TractarValors.parseValue(conectionValue);

                    // Asignar los valores a variables
                    String sgdb = attributes[0];  // Primer atributo: sgdb
                    String host = attributes[1];  // Segundo atributo: host
                    int port = Integer.parseInt(attributes[2]);  // Tercer atributo: port  

                    // Crear el objeto Conection
                    Conection conection = new Conection(sgdb, host, port);

                    // Crear el objeto Servidor
                    Servidor servidor = new Servidor(codi, descripcio, usuaris, servidorActiu, conection);
                    servidors.add(servidor);

                    // Imprimir el servidor
                    //System.out.println(servidor.toString());
                }
            }
            return servidors;
        } catch (Exception e) {
            throw new MPCException("Error al recuperar todos los servidores: " + e.getMessage());
        }
    }

    // Método para recuperar un servidor a partir de su codigo.
    public static Servidor getServerById(String codiAObtenir) throws MPCException {
        Servidor servidor = null;

        try (Connection con = getConnection()) {
            String sql = """
                         SELECT * 
                         FROM \"ut5-practica\".servidors
                         WHERE codi = ?
                         """;

            PreparedStatement ps = con.prepareStatement(sql); // Prepara la consulta con el interrogante
            ps.setString(1, codiAObtenir); // Cambia el primer interrogante por codiAObtenir

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Campos de la BBDD
                    String codi = rs.getString("codi");
                    String descripcio = rs.getString("descripcio");
                    boolean servidorActiu = rs.getBoolean("servidor_actiu");

                    // Obtener Array de usuarios
                    Array usuarisArray = rs.getArray("usuaris");
                    String[] usuaris = (String[]) usuarisArray.getArray();
                    usuarisArray.free();

                    //Obtener tipo compuesto
                    PGobject pgObject = (PGobject) rs.getObject("conection");
                    String conectionValue = pgObject.getValue();
                    String[] attributes = TractarValors.parseValue(conectionValue); // procesar la cadena

                    String sgdb = attributes[0];
                    String host = attributes[1];
                    int port = Integer.parseInt(attributes[2]);
                    Conection conection = new Conection(sgdb, host, port);

                    servidor = new Servidor(codi, descripcio, usuaris, servidorActiu, conection);
                }
            }
            return servidor;
        } catch (Exception e) {
            throw new MPCException("Error al obtener el servidor: " + e.getMessage());
        }
    }

    // Inserir un servidor
    public static void inserirServidor(Servidor servidor) throws MPCException {
        try (Connection con = getConnection()) {
            String sql = """
                         INSERT INTO \"ut5-practica\".servidors
                         VALUES (?,?,?,?,ROW(?,?,?))
                         """;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, servidor.getCodi());
            ps.setString(2, servidor.getDescripcio());
            Array usuarisArray = con.createArrayOf("text", servidor.getUsuaris());
            ps.setArray(3, usuarisArray);
            ps.setBoolean(4, servidor.isServidoractiu());
            ps.setString(5, servidor.getConection().getSgdb());
            ps.setString(6, servidor.getConection().getHost());
            ps.setInt(7, servidor.getConection().getPort());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new MPCException("Error al insertar nuevo servidor" + e.getMessage());
        }
    }

    // Eliminar un usuari d'un servidor
    public static int deleteUserByNif(String codiServidor, String NIF) throws MPCException, SQLException {
        int modificaciones = 0;
        String sql = """ 
                     UPDATE \"ut5-practica\".servidors
                     SET usuaris = array_remove(usuaris,?)
                     WHERE codi = ?
                     """;

        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, NIF);
            ps.setString(2, codiServidor);

            modificaciones = ps.executeUpdate();
        } catch (Exception e) {
            throw new MPCException("Error al insertar los datos" + e.getMessage());
        }
        return modificaciones;
    }

    // Modificar les dades de la conexió d'un servidor
    public static int setConectionData(String sgdb, String host, int port, String codi) throws MPCException, SQLException {
        int modificaciones = 0;
        String sql = """
                     UPDATE \"ut5-practica\".servidors
                     SET conection = ROW(?,?,?)
                     WHERE codi = ?
                     """;
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sgdb);
            ps.setString(2, host);
            ps.setInt(3, port);
            ps.setString(4, codi);

            modificaciones = ps.executeUpdate();
        } catch (Exception e) {
            throw new MPCException("Error al modificar los datos: " + e.getMessage());
        }
        return modificaciones;
    }
}
