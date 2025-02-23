package com.porcel;
/**
 * Código para enviar mensajes a través del servidor SMTP de Google.
 * Comentado y creado con ayuda de chatGPT para entender en todo momento qué se esta haciendo.
 */

import java.io.*;
import java.net.*;
import java.util.Base64;
import javax.net.ssl.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String smtpServer = "smtp.gmail.com"; // Servidor SMTP de gmail.
        int port = 587; // Puerto estandar para STARTTLS

        // Crear socket y conectar al servidor SMTP
        Socket socket = new Socket(smtpServer, port);
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(socket, smtpServer, port, true);

        // Flujo de entrada para leer las respuestas del servidor
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Flujo de salida para enviar comandos al servidor.
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Mensaje de bienvenida del servidor SMTP
        System.out.println("Servidor: " + in.readLine());

        // Enviamos HELO para identificarse con el servidor
        out.println("HELO mpc.com");
        String line;
        while ((line = in.readLine()) != null) { // Leer toda las respuestas del servidor
            System.out.println("Servidor: " + line);
            if (line.startsWith("250 ")) {
                break; // Termina cuando se recibe 250
            }
        }

        // Enviar comando STARTTLS - solicita cifrado TLS en la conexión
        out.println("STARTTLS");
        out.flush();
        String starttlsResponse = in.readLine();
        System.out.println("Servidor: " + starttlsResponse);

        // Reasignar los flujos de entrada y salida al nuevo socket seguro
        in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        out = new PrintWriter(sslSocket.getOutputStream(), true);

        // Autenticación SMTP
        out.println("AUTH LOGIN");
        System.out.println("Servidor: " + in.readLine());

        String username = "ifcmacia@gmail.com";
        String password = "yygq zhzz teva kksa";
        // Enviar nombre de usuario codificado
        out.println(Base64.getEncoder().encodeToString(username.getBytes()));
        System.out.println("Servidor: " + in.readLine());
        // Enviar contraseña codificada
        out.println(Base64.getEncoder().encodeToString(password.getBytes()));
        System.out.println("Servidor: " + in.readLine());

        // Enviar correo
        out.println("MAIL FROM: <" + username + ">");
        System.out.println("Servidor: " + in.readLine());
        out.println("RCPT TO: <maciaporcel@paucasesnovescifp.cat>");
        System.out.println("Servidor: " + in.readLine());

        // Preparar el mensaje
        out.println("DATA");
        System.out.println("Servidor: " + in.readLine());
        // Contenido del mensaje
        out.println("Subject: Felicidades\n\nPor completar esta tarea te aprobamos PSP sin necesidad de examinarte.\nEnhorabuena");
        out.println("."); // Indicar el final del mensaje
        System.out.println("Servidor: " + in.readLine());

        // Cerrar sesión
        out.println("QUIT");
        System.out.println("Servidor: " + in.readLine());

        // Cerrar la conexión
        sslSocket.close();
    }
}
