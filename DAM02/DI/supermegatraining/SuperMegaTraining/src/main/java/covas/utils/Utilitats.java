package covas.utils;

import covas.vistas.Main;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Toni Covas
 */
public class Utilitats {
    
        public static Image obtenirImage(String nomFitxer) {
        // Obtenir la URL de l'arxiu de imatge
        URL iconURL = Main.class.getResource("/images/"+nomFitxer);
        
        // Crear un ImageIcon a partir de la URL
        ImageIcon icon = new ImageIcon(iconURL);
        
        // Obtenir la imatge del icono
        Image image = icon.getImage();
        
        return image;
    }

        public static ImageIcon obtenirIcon(String nomFitxer) {
        // Obtenir la URL de l'arxiu de imatge
        URL iconURL = Main.class.getResource("/images/"+nomFitxer);
        
        // Crear un ImageIcon a partir de la URL
        ImageIcon icon = new ImageIcon(iconURL);      
               
        
        return icon;
    }
    
    
    
    
}
