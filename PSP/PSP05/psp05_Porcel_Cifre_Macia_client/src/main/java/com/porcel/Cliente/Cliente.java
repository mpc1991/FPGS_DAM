package com.porcel.Cliente;

import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

import static com.porcel.keys.KeyHandlers.*;

public class Cliente {
    // Datos srv
    private static final String HOST = "localhost";
    private static final int PORT = 5000;
    private PublicKey publicKey; // Clave pública que se recibe del server
    private SecretKey secretKey; // Clave AES generada por el cliente
    private Scanner scanner = new Scanner(System.in);

    public Cliente() throws IOException {
        // Conectamos al servidor utilizando un Socket TCP
        try (Socket socket = new Socket(HOST, PORT)) {
            // Creamos flujo de entrada/salida para enviar/recibir datos TCP.
            DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());

            // Solicitar la clave pública al servidor
            flujoSalida.writeUTF("getPubKey"); // Solicitud clave pub
            String clavePublicaBase64 = flujoEntrada.readUTF(); // Recepción de la clave pub
            publicKey = obtenerClavePublica(clavePublicaBase64); // Formatear la clave pub

            // Generar clave AES con la clave pub del srv
            secretKey = generarClaveAES();

            // Cifrar la clave AES con la clave pub del srv
            String claveCifradaBase64 = cifrarClaveAES(secretKey, publicKey);
            flujoSalida.writeUTF("CLAVE_AES " + claveCifradaBase64);
            System.out.println("Clave AES: " + claveCifradaBase64 + "enviada al servidor");

            // Bucle para enviar mensajes
            while (true) {
                System.out.println("Mensaje: ");
                String mensaje = scanner.nextLine(); // Leer mensaje desde la consola
                if (mensaje.equals("exit")) { // Salir si se escribe exit
                    break;
                }
                // Cifrar el mensaje antes de enviarlo al servidor
                String mensajeCifrado = cifrarMensajeAES(mensaje, secretKey);
                flujoSalida.writeUTF("Cliente: " + mensajeCifrado);

                // Esperar la respuesta cifrada del servidor
                String respuestaCifrada = flujoEntrada.readUTF();

                // Descifrar la respuesta cifrada del servidor
                String mensajeDescifrado = descifrarMensajeAES(respuestaCifrada, secretKey);
                System.out.println("Mensaje del servidor: " + mensajeDescifrado);
            }
            // Cerramos recursos y notificamos al cliente
            System.out.println("Desconectando...");
        } catch (InterruptedException e) {
            System.out.println("Interrupción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}
