/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnovescifp.spaddd.jdbc;

import cat.paucasesnovescifp.spaddd.jdbc.auxiliars.JDBCException;
import cat.paucasesnovescifp.spaddd.jdbc.basedades.DataAccess;
import cat.paucasesnovescifp.spaddd.jdbc.dades.Editor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Macia Porcel Cifre Classe que interactuará per pantalla.
 * Trabajo AD03 para el módulo GSDAM para paucasesnoves.
 * Ejercicio para entender la conexión con la BBDD y modificar sus propiedades.
 */
public class ProvesJDBC {

    private static List<String> llista = new ArrayList<>();

    public static void main(String[] args) throws JDBCException, SQLException {
        try {
            DataAccess da = new DataAccess();
            
            // Rellenamos el buffer con algunos editores
            for (int i = 1; i < 5; i++) {
                Editor editor = new Editor(i, "pau" + i, "pau"+i+"@gmail");
                da.addEditors(editor);
            }
            
            // Insertamos en la BBDD los editores que tenemos en el buffer
            da.addEditorDB();

            // Consultamos los editores que existen en la BBDD, los almacenamos en una lista y la iteramos para verlo por pantalla
            llista = da.consultaEditors();
            for (String llista : llista) {
                System.out.println(llista);
            }

            /* 
            *  Indicando el ID, eliminamos el editor de la BBDD.
            *  Para poder eliminarlo, el método primero pondrá la FK de la tabla Llibres en null.
            */
            da.deleteEditorBD(780);
            
        } catch (Exception e) {
            throw new JDBCException("Error trobat: " + e.getMessage());
        }
    }
}
