package com.porcel.exercici1;

import com.porcel.mpcException.MpcException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

public class Exercici1 {

    public static List<String> getDirContent(Path p) throws MpcException {

        // Verificar si la ruta es un directorio.
        if (!Files.isDirectory(p)) {
            throw new MpcException("La ruta debe ser un directorio");

        } else {
            // Creamos una lista donde almacenaremos los datos de cada elemento
            List<String> dades = new ArrayList<>();

            // Obtener todos los elementos en una ruta
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(p)) {
                for (Path path : ds) {

                    if (Files.isDirectory(path)) {
                        dades.add("\nDirectori");
                        dades.add("nom" + path.getFileName().toString());
                    } else {
                        dades.add("\nFitxer");
                        dades.add("Nom: " + path.getFileName().toString());

                        // verificamos los permisos del archivo.
                        if (Files.isReadable(path)) {
                            dades.add("És de lectura");
                        }
                        if (Files.isWritable(path)) {
                            dades.add("És de escritura");
                        }
                        if (Files.isExecutable(path)) {
                            dades.add("És executable");
                        }

                        // Es oculto?
                        if (Files.isHidden(path)) {
                            dades.add("És ocult.");
                        } else {
                            dades.add("No és ocult");
                        }

                        // Propietario
                        UserPrincipal fileOwner = Files.getOwner(path);
                        dades.add("Owner: " + fileOwner.getName());

                        // Dada darrera modificació
                        dades.add("Darrera modificació: " + Files.getLastModifiedTime(path).toString());
                    }
                }
            } catch (IOException e) {
                throw new MpcException("Error: error al obtener datos de los elementos del directorio");
            }
            return dades;
        }
    }
}
