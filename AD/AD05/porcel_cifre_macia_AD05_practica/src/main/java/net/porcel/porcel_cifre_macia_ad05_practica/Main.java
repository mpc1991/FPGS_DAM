/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package net.porcel.porcel_cifre_macia_ad05_practica;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import net.porcel.porcel_cifre_macia_ad05_practica.auxiliars.PersException;
import net.porcel.porcel_cifre_macia_ad05_practica.data.DataAccess;
import net.porcel.porcel_cifre_macia_ad05_practica.dto.Cicle;

/**
 *
 * @author seek_
 */
public class Main {

    private  Properties getProperties() {
        Properties propietats = new Properties();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream("propietats.properties")))) {
            System.out.println("Inside try: ");
            String linea = "";
            while ((linea = br.readLine()) != null) {
                System.out.println("linea: " + linea);
                String[] parts = linea.split("=");
                propietats.setProperty(parts[0], parts[1]);
            }
        } catch (Exception e) {
            System.out.println("Catch getPropeties: " + e.getMessage());
        }
        return propietats;
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        Properties propietats = main.getProperties();
        
        try{
            DataAccess dataAccess = new DataAccess(propietats);
            Cicle dam = dataAccess.getCicle(1);
            System.out.println("Dam: " + dam);
        } catch (Exception e) {
            System.out.println("main: " + e.getMessage());
            System.out.println("main: " + e.getStackTrace());
        }
    }
}
