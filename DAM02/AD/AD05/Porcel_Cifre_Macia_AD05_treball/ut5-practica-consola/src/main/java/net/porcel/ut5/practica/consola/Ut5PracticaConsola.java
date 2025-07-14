/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package net.porcel.ut5.practica.consola;

import java.util.List;
import net.porcel.ut5.practica.component.auxiliars.MPCException;
import net.porcel.ut5.practica.component.controladora.Controladora;
import net.porcel.ut5.practica.component.dto.Conection;
import net.porcel.ut5.practica.component.dto.Servidor;

/**
 *
 * @author MPC
 */
public class Ut5PracticaConsola {

    public static void main(String[] args) {
        List<Servidor> servidors = null;
        try {
            System.out.println("1. Recuperar tots els servidors de la base de dades:");
            servidors = Controladora.getAllservers();
            System.out.println("Mostrando todos los servidores:");
            for (Servidor servidor : servidors) {
                System.out.println(servidor);
            }
        } catch (Exception e) {
            System.out.println("getAllServers error: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("2. Recuperar un servidor a partir de su código");
            Servidor servidorEx2 = Controladora.getServerById("2");
            System.out.println(servidorEx2);
        } catch (Exception e) {
            System.out.println("getServerById error: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("3. Insertar un nuevo servidor");
            String codi = "1006";
            String descripcio = "2";
            String[] usuaris = {"asd1", "asd2", "asd3", "asd4", "asd5"};
            boolean servidoractiu = false;
            String sgdb = "MPCSql";
            String host = "192.168...";
            int port = 5432;
            Conection conection = new Conection(sgdb, host, port);
            Servidor servidorEx3 = new Servidor(codi, descripcio, usuaris, servidoractiu, conection);
            Controladora.inserirServidor(servidorEx3);
            mostrarServidores();
        } catch (Exception e) {
            System.out.println("inserirServidor error: " + e.getMessage());
        }
        
        try {
            System.out.println("");
            System.out.println("4. Eliminar un usuario de un servidor");
            int modificaciones = Controladora.deleteUserByNif("1006", "asd1");
            mostrarServidores();

        } catch (Exception e) {
            System.out.println("deleteUserByNif error: " + e.getMessage());
        }
        
        try {
            System.out.println("");
            System.out.println("5. Modificar los datos de la conexión de un servidor");
            String sgdb = "New SGDB";
            String host = "19999999";
            int port = 99999;
            String codi = "2";
            Controladora.setConectionData(sgdb, host, port, codi);
            mostrarServidores();
        } catch (Exception e) {
            System.out.println("setConectionData error: " + e.getMessage());
        }
    }

    public static void mostrarServidores() throws MPCException {
        // Metodo adicional para ver todos los servidores en el estado actual.
        List<Servidor> servidors = Controladora.getAllservers();
        System.out.println("Mostrando todos los servidores:");
        for (Servidor servidor : servidors) {
            System.out.println(servidor);
        }
    }
}
