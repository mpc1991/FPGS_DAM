/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porcel.exercici2;

import com.porcel.dto.Modul;
import com.porcel.mpcException.MpcException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Exercici2 {
    public static void setLlistaModulsDataStreams(List<Modul> moduls, Path p) throws MpcException {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(p.toFile())))){
            for (Modul modul : moduls) {
                dos.writeUTF(modul.getCodi());
                dos.writeUTF(modul.getNom());
                dos.writeInt(modul.getHores());
            }
        } catch (Exception e) {
            throw new MpcException("Error: escritura en el archivo fallida");
        }
    }
    
    public static ArrayList<Modul> getLlistaModulsDataStreams(Path p) throws MpcException, FileNotFoundException{
        ArrayList<Modul> content = new ArrayList<>();
        
        FileInputStream fi = new FileInputStream(p.toFile());
        BufferedInputStream bis = new BufferedInputStream(fi);
        
        try (DataInputStream di = new DataInputStream(bis)) {
            while(true) {
                String codi = di.readUTF();
                String nom = di.readUTF();
                int hores = di.readInt();
                
                Modul modul = new Modul(codi, nom, hores);
                content.add(modul);
            }
        } catch (EOFException e) {
        } catch (Exception e) {
            throw new MpcException("Error al recuperar les dades de l'archiu");
        }    
        return content;
    }
    
    public static void setLlistaModulsObjectStreams(List<Modul> moduls, Path p) throws MpcException, FileNotFoundException{
        
        FileOutputStream fos = new FileOutputStream(p.toFile());
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            //for (Modul modul : moduls) {
                oos.writeObject(moduls);
            //}
        } catch (IOException e) {
            throw new MpcException("Error: exritura errónea");
        }
    }
    
    public static List<Modul> getLlistaModulsObjectStreams(Path p) throws FileNotFoundException, MpcException, IOException{
        
        List<Modul> moduls = new ArrayList<>();
        
        FileInputStream fis = new FileInputStream(p.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
        
        try (ObjectInputStream ois = new ObjectInputStream(bis)){
            //while (true) {
                moduls = (List<Modul>)ois.readObject();
            //}
            return moduls;
        } catch (IOException e) { 
            throw new MpcException ("Error: lectura del archivo incorrecta");
        } catch (ClassNotFoundException cnf) {
            throw new MpcException ("Class not found: " + cnf.getMessage());
        }
        
    }
}
