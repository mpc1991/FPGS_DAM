package com.porcel.Cliente;

import com.porcel.Threads.ClienteTCP;
import com.porcel.keys.KeyHandlers;

import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

import static com.porcel.keys.KeyHandlers.*;

public class Cliente {
    // Datos serv
    private static final String HOST = "localhost";
    private static final int PORT = 5000;
    private PublicKey publicKey; // Clave pública que se recibe del server
    private SecretKey secretKey; // Clave AES
    private Scanner scanner = new Scanner(System.in);

    public Cliente() throws IOException {
        // Conectamos al servidor utilizando un Socket TCP
        try (Socket socket = new Socket(HOST, PORT)) {
            // Creamos flujo de entrada/salida para enviar/recibir datos TCP.
            DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());



            // Solicitar la clave pública al servidor
            flujoSalida.writeUTF("getPubKey");
            String clavePublicaBase64 = flujoEntrada.readUTF();
            publicKey = obtenerClavePublica(clavePublicaBase64);

            // Generar clave AES
            secretKey = generarClaveAES();

            // Cifrar la clave AES con la clave pub del server
            String claveCifradaBase64 = cifrarClaveAES(secretKey, publicKey);
            flujoSalida.writeUTF("CLAVE_AES " + claveCifradaBase64);
            System.out.println("Clave AES: " + claveCifradaBase64 + "enviada al servidor");

            // Enviar mensajes cifrados
            while (true) {
                System.out.println("Mensaje: ");
                String mensaje = scanner.nextLine();
                if (mensaje.equals("exit")) {
                    break;
                }
                // Cifrar el mensaje antes de enviarlo al servidor
                String mensajeCifrado = cifrarMensajeAES(mensaje, secretKey);
                flujoSalida.writeUTF("Cliente: " + mensajeCifrado);

                // Esperar la respuesta del servidor (que estará cifrada)
                String respuestaCifrada = flujoEntrada.readUTF();

                // Descifrar la respuesta del servidor
                String mensajeDescifrado = descifrarMensajeAES(respuestaCifrada, secretKey);
                System.out.println("Mensaje del servidor: " + mensajeDescifrado);
            }
            // Cerramos recursos y notificamos al cliente
            System.out.println("Desconectando...");
            socket.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
