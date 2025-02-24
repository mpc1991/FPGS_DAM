package com.porcel;

import java.io.*;
import java.net.*;

public class Main {
    // Conexión FTP
    private static final String SERVER = "ftp.dlptest.com";
    private static final int CONTROL_PORT = 21;
    private static final String USER = "dlpuser";
    private static final String PASS = "rNrKYTX9g7z3RgJRmxWuGHbeu";

    // Archivo local/destino
    private static String filePath = "testmpc.txt";
    private static String remoteFileName = "mpctest4.txt";

    private static Socket socket; // Para la conexión al servidor
    private static BufferedReader br; // Para recibir datos del servidor
    private static PrintWriter pr; // Para enviar comandos al servidor
    private static FileInputStream fi; // Para leer el archivo
    private static OutputStream os; // Para enviar archivos al servidor

    public static void main(String[] args) throws IOException {
        try {
            // 1. Conexión al servidor por hostname/puerto
            socket = new Socket(SERVER, CONTROL_PORT);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pr = new PrintWriter(socket.getOutputStream(), true);
            fi = new FileInputStream(filePath);
            os = socket.getOutputStream();

            // 2. Leer la respuesta del servidor
            String response = br.readLine();
            System.out.println("Respuesta del servidor: " + response);

            // 3. Auth
            pr.println("USER " + USER);
            response = br.readLine();
            System.out.println("Respuesta del servidor: " + response);
            pr.println("PASS " + PASS);
            response = br.readLine();
            System.out.println("Respuesta del servidor: " + response);

            // 4. Enviar el comando PASV para habilitar el modo pasivo
            pr.println("PASV");
            response = br.readLine();
            System.out.println("Respuesta del servidor: " + response);

            // Extraer la IP y Puerto
            int start = response.indexOf('(');                         // Buscar inicio de la dirección y el puerto
            int end = response.indexOf(')', start);                // Buscar el final
            String pasvResponse = response.substring(start + 1, end);  // Extraer la información
            String[] pasvParts = pasvResponse.split(",");        // Separar la dirección IP y el puerto

            // Construir la IP y calcular el puerto.
            String ip = pasvParts[0] + "." + pasvParts[1] + "." + pasvParts[2] + "." + pasvParts[3];
            int port = Integer.parseInt(pasvParts[4]) * 256 + Integer.parseInt(pasvParts[5]);

            // 5. Conectar al canal de datos usando IP/Puerto
            socket = new Socket(ip, port); // Conexión con el servidor de datos
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 6. LIST para obtener los archivos del directorio
            pr.println("LIST");
            response = Main.br.readLine();
            System.out.println("Respuesta del servidor: " + response);

            // 7. Leer la respuesta a LIST
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Mostrar el contenido de los archivos/directorios
            }

            // STOR para subir datos
            pr.println("STOR " + remoteFileName);
            response = br.readLine();
            System.out.println("Respuesta del servidor: " + response);

            // Leer el archivo local y enviar al servidor
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fi.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
            System.out.println("Archivo subido con éxito.");

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } finally {
            socket.close();
            os.close();
            fi.close();
            pr.close();
            br.close();
        }
    }
}