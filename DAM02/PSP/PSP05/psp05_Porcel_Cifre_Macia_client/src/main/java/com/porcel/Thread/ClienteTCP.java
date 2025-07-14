package com.porcel.Thread;

import javax.crypto.SecretKey;
import java.io.DataInputStream;

import static com.porcel.keys.KeyHandlers.descifrarMensajeAES;

/**
 * Clase ClienteTCP:
 * Hilo encargado de escuchar mensajes del servidor y mostrarlos en la consola.
 */
public class ClienteTCP extends Thread {
    private final DataInputStream flujoEntrada; // Flujo de entrada para recibir mensajes TCP del servidor
    String mensaje = "";
    boolean running = true;
    SecretKey secretKey;

    public ClienteTCP(DataInputStream flujoEntrada, SecretKey secretKey) {
        this.flujoEntrada = flujoEntrada;
        this.secretKey = secretKey;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) { // Verificamos si el hilo se ha detenido
                // Leer y mostrar mensajes del servidor
                //mensaje = flujoEntrada.readUTF(); // Almacenamos el mensaje del servidor
                //System.out.println(mensaje);

                // Esperar la respuesta cifrada del servidor
                String respuestaCifrada = flujoEntrada.readUTF();

                // Descifrar la respuesta cifrada del servidor
                String mensajeDescifrado = descifrarMensajeAES(respuestaCifrada, secretKey);
                System.out.println("Mensaje del servidor: " + mensajeDescifrado);
            }
        } catch (Exception e) {
            //System.err.println("Error en ClienteTCP: " + e.getMessage());
        } finally {
            try {
                flujoEntrada.close();
                this.interrupt();

            } catch (Exception e) {
                System.err.println("Error cerrando flujo de entrada: " + e.getMessage());
            }
        }
    }

    // Método para detener el hilo
    public void detener() {
        this.interrupt();

    }
}

