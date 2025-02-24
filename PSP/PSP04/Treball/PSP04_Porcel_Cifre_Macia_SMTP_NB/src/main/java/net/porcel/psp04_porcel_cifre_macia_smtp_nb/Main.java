/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package net.porcel.psp04_porcel_cifre_macia_smtp_nb;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import org.apache.commons.net.ftp.FTPClient;

public class Main {
    private static String server = "ftp.dlptest.com";
    private static int port = 21;
    private static String user = "dlpuser";
    private static String pass = "rNrKYTX9g7z3RgJRmxWuGHbeu";
    private static String filePath = "mpctest.txt";
    private static String remoteFileName = "mpctest2.txt";
    private static Socket controlSocket;
    private static BufferedReader controlReader;
    private static PrintWriter controlWriter;
    private static String dataAddress;
    private static int dataPort;

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        String server = "ftp.dlptest.com";
        String username = "dlpuser";
        String password = "rNrKYTX9g7z3RgJRmxWuGHbeu";

        try {
            // Conectar al servidor FTP
            ftpClient.connect(server);
            if (ftpClient.login(username, password)) {
                System.out.println("Conectat amb èxit al servidor FTP.");

                // Activar el mode PASV
                ftpClient.enterLocalPassiveMode();

                // Llistar el contingut del directori actual
                String[] files = ftpClient.listNames();
                if (files != null) {
                    System.out.println("Fitxers i directoris al servidor FTP:");
                    Arrays.stream(files).forEach(System.out::println);
                } else {
                    System.out.println("No s'han trobat fitxers o directoris.");
                }

                // Tancar la connexió
                ftpClient.logout();
            } else {
                System.out.println("No s'ha pogut autenticar.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
