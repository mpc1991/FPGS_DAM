package porcel.eines;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EinesCharacterStream {

    public static void mostraCharacters(String origen) throws FileNotFoundException {
        try (FileReader fr = new FileReader(origen)) {
            while (fr.ready()) {
                char d = (char)fr.read();
                System.out.print(d);
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escriuCharacters(String desti, String dades) {
        try (FileWriter out = new FileWriter(desti)) {
            out.write(dades);

        } catch (IOException e) {}
    }

    public static void copiaCharacters(String origen, String desti) {
        try (FileReader fr = new FileReader(origen)) {
            try (FileWriter fw = new FileWriter(desti)) {
                while (fr.ready()) {
                    char d = (char)fr.read();
                    fw.write(d);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
