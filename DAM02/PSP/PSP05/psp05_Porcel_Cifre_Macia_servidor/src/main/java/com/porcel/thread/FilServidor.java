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

// Classe encargada de manejar la lógica del cliente
public class FilServidor implements Runnable {
    private final Socket socket;
    private final PrivateKey privateKey; // Clave privada del servidor utilizada para descifrar mensajes
    private PublicKey publicKey; // Clave pública del servidor, que se envía a los clientes para que puedan cifrar los datos
    private SecretKey secretKey; // Clave secreta simétrica (AES) utilizada para cifrar y descifrar los mensajes con el cliente

    public FilServidor(Socket socket, PrivateKey privateKey, PublicKey publicKey) {
        this.socket = socket;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public void setPublicKey(PublicKey key) {
        this.publicKey = key;
    }

    @Override
    public void run() {
        // Flujo de entrada y salida para la comunicación con el cliente
        try (DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
             DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                String mensaje;

                try {
                    mensaje = flujoEntrada.readUTF(); // Recibe el mensaje del cliente
                } catch (EOFException e) {
                    //System.out.println("Cliente cerró la conexión abruptamente.");
                    break; // Salimos del bucle si el cliente cierra el socket
                }

                // Comprobamos si el cliente ha solicitado desconectarse
                if ("exit".equalsIgnoreCase(mensaje)) {
                    System.out.println("Cliente desconectado");
                    break;
                } else if ("getPubKey".equals(mensaje)) { // Si el mensaje es la solicitud de la clave pública
                    // Enviar clave pública al cliente
                    String clavePublicaBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
                    flujoSalida.writeUTF(clavePublicaBase64);
                    System.out.println("Clave pública enviada al cliente");
                } else if (mensaje.startsWith("CLAVE_AES")) { // Si el mensaje contiene la clave AES cifrada
                    try {
                        // Recibir y descifrar la clave AES
                        String[] partes = mensaje.split(" ", 2);
                        if (partes.length < 2) {
                            System.out.println("Error: formato de CLAVE_AES incorrecto.");
                            continue;
                        }
                        String claveCifradaBase64 = partes[1];
                        String datosDescifrados = descifrarClaveAES(claveCifradaBase64, privateKey);

                        // Separar el hash de la clave y la clave en sí
                        String[] datos = datosDescifrados.split(":", 2);
                        if (datos.length < 2) {
                            System.out.println("Error: clave simétrica descifrada mal formada.");
                            continue;
                        }

                        String hashRecibido = datos[0];
                        String claveSimetrica = datos[1];

                        // Verificar la integridad de la clave AES
                        if (verificarHash(claveSimetrica, hashRecibido)) {
                            secretKey = obtenerClaveAESDesdeBase64(claveSimetrica);
                            System.out.println("Clave AES recibida y verificada correctamente.");
                            String acuseCifrado = cifrarMensajeAES("DataReceived", secretKey);
                            flujoSalida.writeUTF(acuseCifrado); // Acuse de recibo cifrado
                        } else {
                            System.out.println("¡Alerta! Integridad de clave AES comprometida.");
                            socket.close();
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Error al procesar CLAVE_AES: " + e.getMessage());
                        socket.close();
                        return;
                    }
                } else if (mensaje.startsWith("Cliente:")) { // Si el mensaje comienza con "Cliente:"
                    try {
                        String[] partesMensaje = mensaje.split(" ", 2);
                        if (partesMensaje.length < 2) {
                            System.out.println("Error: formato de mensaje incorrecto.");
                            continue;
                        }

                        String mensajeCifradoBase64 = partesMensaje[1];
                        String mensajeDescifrado = descifrarMensajeAES(mensajeCifradoBase64, secretKey);

                        // Separar hash y mensaje original
                        String[] partes = mensajeDescifrado.split(":", 2);
                        if (partes.length < 2) {
                            System.out.println("Mensaje de descifrado no contiene el formato correcto");
                            continue;
                        }

                        String hashRecibido = partes[0];
                        String mensajeOriginal = partes[1];

                        // Verificar la integridad
                        if (verificarHash(mensajeOriginal, hashRecibido)) {
                            System.out.println("Mensaje recibido del cliente: " + mensajeOriginal);

                            // Enviar acuse de recibo cifrado al cliente
                            String acuseCifrado = cifrarMensajeAES("DataReceived & hash verified", secretKey);
                            flujoSalida.writeUTF(acuseCifrado); // Acuse de recibo cifrado
                        } else {
                            System.out.println("¡Alerta! Integridad comprometida");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al procesar mensaje del cliente: " + e.getMessage());
                        socket.close();
                        return;
                    }
                }
            }
            socket.close();
            System.out.println("Conexión con cliente cerrada.");
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
