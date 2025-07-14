package porcel.proves;

import porcel.eines.EinesByteStreams;
import porcel.eines.EinesCharacterStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class ProvesStreams {

    public static void provesByte() throws IOException {
        String desti = "../desti.txt";
        String origen = "../origen.txt";

        String texto = "";
        Files.write(Path.of(origen), texto.getBytes(), CREATE, APPEND);
        Files.write(Path.of(desti), texto.getBytes(), CREATE, APPEND);

        // escriure bytes
        byte[] dades = "Hola Llorenç".getBytes();
        try {
            EinesByteStreams.escriuByte(origen, dades);
            System.out.println("Dades escrites correctament");
        } catch (Exception e) {

        }

        // tornaBytes
        byte[] bytesLlegits = EinesByteStreams.tornaBytes(origen);
        System.out.println("Contenido del archivo:");
        for (byte b : bytesLlegits) {
            System.out.print((char) b);
        }
        System.out.println();

        // copiaBytes
        EinesByteStreams.copiaBytes(origen, desti);
    }

    public static void provesCharacter() throws FileNotFoundException {
        String desti = "../desti.txt";
        String origen = "../Himne dels pirates UTF-8.txt";
        String dades = "Hola que tal?";

        EinesCharacterStream.mostraCharacters(origen);
        EinesCharacterStream.escriuCharacters(desti, dades);
        EinesCharacterStream.copiaCharacters(origen, desti);
    }
}
