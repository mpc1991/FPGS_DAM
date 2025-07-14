package org.example;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class carregarPaginaWeb {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String pagina = br.readLine();

            // Cargar el contenido de la página web
            URL url = new URL(pagina);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            try (BufferedReader in = new BufferedReader (new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }

            connection.disconnect();

            System.out.println("Codi HTML");
            System.out.println(content.toString());
            System.out.flush();
            }
        } catch (Exception e) {

        }
    }
}