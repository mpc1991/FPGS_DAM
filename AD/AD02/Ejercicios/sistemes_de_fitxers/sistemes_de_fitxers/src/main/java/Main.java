import dto.Clase;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathDirectories =    "../sistemes_de_fitxers\\src\\main\\java";              // ejercicio punto: 1
        String pathFiles =          "../sistemes_de_fitxers\\src\\main\\java\\Main.java";   // ejercicio punto: 2
        String crearPath =          "../sistemes_de_fitxers\\test_directory";               // ejercicio punto: 3
        String copiarFitxer =       "../sistemes_de_fitxers\\Main.java";                    // ejercicio punto: 4
        String mouFitxer =          "../sistemes_de_fitxers\\test_directory\\Main.java";    // ejercicio punto: 5
        String crearFitxerText =    "../sistemes_de_fitxers\\test_directory\\texto.txt";    // ejercicio punto: 6
        String afegirText =         "¡¡me muerooo!!\n";                                     // ejercicio punto: 6

        System.out.println(Clase.mostrarInfopath(pathDirectories));                         // ejercicio punto: 1
        System.out.println(Clase.mostrarInfoFiles(pathFiles));                              // ejercicio punto: 2
        Clase.crearDirectori(crearPath);                                                    // ejercicio punto: 3
        Clase.copiarFitxer(pathFiles, copiarFitxer);                                        // ejercicio punto: 4
        Clase.mouFitxer(copiarFitxer, mouFitxer);                                           // ejercicio punto: 5
        Clase.afegirText(crearFitxerText, afegirText);                                      // ejercicio punto: 6
        List<String> lineas = Clase.llegirText(crearFitxerText);                            // ejercicio punto: 7
        System.out.println("");
        System.out.println("Contenido del fichero:");
        for (String linea : lineas) {
            System.out.println(linea);
        }
    }
}
