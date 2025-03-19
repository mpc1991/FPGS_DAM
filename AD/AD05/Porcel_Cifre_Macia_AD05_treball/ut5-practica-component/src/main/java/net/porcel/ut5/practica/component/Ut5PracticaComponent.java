/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package net.porcel.ut5.practica.component;

import java.sql.Array;
import java.sql.Connection;
import java.util.List;
import net.porcel.ut5.practica.component.auxiliars.MPCException;
import net.porcel.ut5.practica.component.controladora.Controladora;
import net.porcel.ut5.practica.component.dto.Conection;
import net.porcel.ut5.practica.component.dto.Servidor;

/**
 *
 * @author seek_
 */
public class Ut5PracticaComponent {

    public static void main(String[] args) throws MPCException {
        System.out.println("Hello World!");
        try {

            //1
            List<Servidor> servidors = Controladora.getAllservers();
            for (Servidor servidor : servidors) {
                System.out.println(servidor);
            }

            //2
            //Servidor servidor = Controladora.getServerById("2");
            //System.out.println(servidor);
            //3
            //String codi = "1001";
            //String descripcio = "2";
            //String[] usuaris = {"asd1", "asd2"};
            //boolean servidoractiu = false;
            //String sgdb = "MPCSql";
            //String host = "192.168...";
            //int port = 5432;
            //Conection conection = new Conection(sgdb, host, port);
            //Servidor servidor = new Servidor(codi, descripcio, usuaris, servidoractiu, conection);
            //Controladora.inserirServidor(servidor);
            //4
            //int modificaciones = Controladora.deleteUserByNif("2", "LGL");
            //String sgdb = "New SGDB";
            //String host = "19999999";
            //int port = 99999;
            //String codi = "1";
            
            //int modificacions = Controladora.setConectionData(sgdb, host, port, codi);

        } catch (Exception e) {
            System.out.println("Main: " + e.getMessage());
        }
    }
}
