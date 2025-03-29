package com.porcel.server;

import com.porcel.keygens.GeneradorKeyPair;
import com.porcel.thread.FilServidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;

public class Server {
    private static int port = 5000;
    private static KeyPair keyPair;

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(port)) { // Arranca el servidor
            System.out.println("Server started on port: " + port + ", waiting connection...");

            // Generar par de claves RSA(publica y privada)
            keyPair = GeneradorKeyPair.randomGenerate(2048);

            // Bucle infinito para aceptar conexiones de clientes
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                // Pasa tanto la clave privada como la pública al hilo para que pueda cifrar y descifrar los mensajes.
                new Thread(new FilServidor(socket, keyPair.getPrivate(), keyPair.getPublic())).start();
            }
        } catch (Exception e) {
            System.out.println("Err:" + e.getMessage());
        }
    }

    public static PublicKey getPublicKey() {
        return keyPair.getPublic();
    }
}
