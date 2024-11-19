package porcel.eines;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EinesCharacterStream {

    public static void mostraCharacters(String origen) throws FileNotFoundException {
        try (FileReader fr = new FileReader(origen)) {
            while (fr.ready()) {
                char d = (char)fr.read();
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
