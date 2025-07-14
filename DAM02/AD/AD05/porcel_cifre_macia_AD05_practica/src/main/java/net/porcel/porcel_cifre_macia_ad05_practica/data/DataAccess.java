/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.porcel.porcel_cifre_macia_ad05_practica.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import net.porcel.porcel_cifre_macia_ad05_practica.dto.Cicle;
import org.postgresql.PGConnection;

/**
 *
 * @author seek_
 */
public class DataAccess {

    private final String URL;
    private final Properties PROPIETATS;

    public DataAccess(String URL, Properties PROPIETATS) {
        this.URL = URL;
        this.PROPIETATS = PROPIETATS;
    }

    public DataAccess(Properties propietats) {
        this.URL = propietats.getProperty("url");
        this.PROPIETATS = propietats;
    }

    // Recuperar cicle per identificador
    public Cicle getCicle(int codi) throws SQLException {
        Cicle cicle = null;
        String sql = "SELECT codi, nom FROM \"ORDB\".cicles WHERE codi = ?";

        try (Connection con = DriverManager.getConnection(URL, PROPIETATS); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codi); // Asigna directamente el valor de codi

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cicle = new Cicle(rs.getInt("codi"), rs.getString("nom"));
                }
            }
        }
        return cicle;
    }
}
