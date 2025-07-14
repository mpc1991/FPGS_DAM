package dto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public abstract class Clase {

    public static String mostrarInfopath(String path) throws IOException {
        Path p = Path.of(path); // ejercicio punto: 1

        String ruta = p.toString();
        int cuantitatElements = p.getNameCount();
        String index = p.getName(1).toString();
        String darrerelement = p.getFileName().toString();

        String solucio =
                "Informació de la carpeta: " + "\n" +
                "ruta: " + ruta + "\n" +
                        "Cuantitat d'elements: " + cuantitatElements + "\n" +
                        "Index: " + index + "\n" +
                        "Darrerelelement: " + darrerelement + "\n\n";

        return solucio;
    }

    public static String mostrarInfoFiles(String path) throws IOException {
        Path p = Path.of(path);

        boolean isRegular = Files.isRegularFile(p);
        boolean isDirectory = Files.isDirectory(p);
        boolean isReadable = Files.isReadable(p);
        boolean isWriteable = Files.isWritable(p);
        boolean isexecutable = Files.isExecutable(p);
        double tamany = Files.size(p);
        boolean isHidden = Files.isHidden(p);
        FileTime lastMod = Files.getLastModifiedTime(p);
        UserPrincipal owner = Files.getOwner(p);

        String solucio =
                "Informació de l'arxiu: " + "\n" +
                "Es regular? " + isRegular + "\n" +
                        "Es un directori? " + isDirectory + "\n" +
                        "Es Readable? " + isReadable + "\n" +
                        "Es writable? " + isWriteable + "\n" +
                        "Es executable? " + isexecutable + "\n" +
                        "Quin tamany te? " + tamany + "\n" +
                        "Es hidden? " + isHidden + "\n" +
                        "Fecha darrera modificació: " + lastMod + "\n" +
                        "Owner : " + owner;

        return solucio;
    }

    public static void crearDirectori(String path) throws IOException {
        Path p = Path.of(path);
        Files.createDirectories(p);
    }

    public static void copiarFitxer(String source, String desti) throws IOException {
        Path src = Path.of(source);
        Path dest = Path.of(desti);

        Files.copy(src, dest, REPLACE_EXISTING);
    }

    public static void mouFitxer(String source, String desti) throws IOException {
        Path src = Path.of(source);
        Path dest = Path.of(desti);

        Files.move(src, dest, REPLACE_EXISTING);
    }

    public static void afegirText(String source, String texto) throws IOException {
        Path src = Path.of(source);
        Files.write(src, texto.getBytes(), CREATE, APPEND);

    }

    public static List<String> llegirText(String source) throws IOException {
        Path src = Path.of(source);
        List<String> lineas = Files.readAllLines(src);
        return lineas;
    }
}
