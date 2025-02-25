package com.porcel;

import java.io.*;
import java.net.*;

public class Main {
    private static final String SERVER = "ftp.dlptest.com";
    private static final int CONTROL_PORT = 21;
    private static final String USER = "dlpuser";
    private static final String PASS = "rNrKYTX9g7z3RgJRmxWuGHbeu";

    private static String filePath = "testmpc.txt";
    private static String remoteFileName = "testMPC1.txt";

    public static void main(String[] args) {
        try (Socket controlSocket = new Socket(SERVER, CONTROL_PORT);
             BufferedReader br = new BufferedReader(new InputStreamReader(controlSocket.getInputStream()));
             PrintWriter pr = new PrintWriter(controlSocket.getOutputStream(), true)) {

            System.out.println("Respuesta del servidor: " + br.readLine());

            pr.println("USER " + USER);
            System.out.println("Respuesta del servidor: " + br.readLine());
            pr.println("PASS " + PASS);
            System.out.println("Respuesta del servidor: " + br.readLine());

            // PASV para LIST
            pr.println("PASV");
            String response = br.readLine();
            System.out.println("Respuesta PASV: " + response);
            Socket dataSocket = openDataConnection(response);

            pr.println("LIST");
            System.out.println("Respuesta LIST: " + br.readLine());
            printDataResponse(dataSocket);
            response = br.readLine();
            System.out.println("Respuesta LIST: " + response);
            dataSocket.close();

            // PASV para STOR
            pr.println("PASV");
            response = br.readLine();
            System.out.println("Respuesta PASV: " + response);
            Socket dataSocket2 = openDataConnection(response);

            pr.println("STOR " + remoteFileName);
            response = br.readLine();
            System.out.println("Respuesta STOR: " + response);

            if (response == null || !response.startsWith("150")) {
                System.out.println("Error: el servidor no aceptó la subida.");
                return;
            }

            try (FileInputStream fi = new FileInputStream(filePath);
                 OutputStream dataOutput = dataSocket2.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fi.read(buffer)) != -1) {
                    dataOutput.write(buffer, 0, bytesRead);
                }
                dataOutput.flush();
            }
            dataSocket2.close();

            System.out.println("Archivo subido con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Socket openDataConnection(String response) throws IOException {
        int start = response.indexOf('(');
        int end = response.indexOf(')', start);
        String[] parts = response.substring(start + 1, end).split(",");
        String ip = parts[0] + "." + parts[1] + "." + parts[2] + "." + parts[3];
        int port = Integer.parseInt(parts[4]) * 256 + Integer.parseInt(parts[5]);
        return new Socket(ip, port);
    }

    private static void printDataResponse(Socket dataSocket) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
