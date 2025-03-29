package com.porcel.thread;

import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import static com.porcel.keygens.KeyHandler.*;

public class FilServidor implements Runnable{
    private final Socket socket;
    private final PrivateKey privateKey;
    private static PublicKey publicKey;
    private static SecretKey secretKey;

    public FilServidor(Socket socket, PrivateKey privateKey, PublicKey publicKey) {
        this.socket = socket;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public static void setPublicKey(PublicKey key) {
        publicKey = key;
    }

    @Override
    public void run() {
        try(DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                String mensaje;

                try {
                    mensaje = flujoEntrada.readUTF(); // Puede lanzar EOFException si el cliente cierra el socket
                } catch (EOFException e) {
                    System.out.println("Cliente cerró la conexión abruptamente.");
                    break; // Salimos del bucle si el cliente cierra el socket
                }

                if ("exit".equalsIgnoreCase(mensaje)) {
                    System.out.println("Cliente desconectado");
                    break;
                } else if ("getPubKey".equals(mensaje)) {
                    // Enviar clave pública al cliente
                    String clavePublicaBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
                    flujoSalida.writeUTF(clavePublicaBase64);
                    System.out.println("Clave pública enviada al cliente");
                } else if (mensaje.startsWith("CLAVE_AES")) {
                    // Recibir y descifrar la clave AES
                    String claveCifradaBase64 = mensaje.split(" ")[1];
                    secretKey = descifrarClaveAES(claveCifradaBase64, privateKey);
                    System.out.println("Clave AES recibida y almacenada");
                } else if (mensaje.startsWith("Cliente:")) {
                    String mensajeCifradoBase64 = mensaje.split(" ")[1];
                    String mensajeDescifrado = descifrarMensajeAES(mensajeCifradoBase64, secretKey);

                    // Separar hash y mensaje original
                    String[] partes = mensajeDescifrado.split(":", 2);
                    if (partes.length < 2) {
                        System.out.println("Mensaje de descifrado no contiene el formato correcto");
                        return;
                    }

                    String hashRecibido = partes [0];
                    String mensajeOriginal = partes [1];

                    // Verificar la integridad
                    if (verificarHash(mensajeOriginal, hashRecibido)) {
                        System.out.println("Mensaje recibido al cliente: " + mensajeOriginal);
                    } else {
                        System.out.println("Alerta! integridad comprometida");
                    }
                    System.out.println("Mensaje recibido: " + mensajeDescifrado);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
