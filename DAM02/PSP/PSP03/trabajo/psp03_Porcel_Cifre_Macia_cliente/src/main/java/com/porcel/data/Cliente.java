package com.porcel.data;

import com.porcel.fils.ClienteTCP;
import com.porcel.fils.ClienteUDP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    // Datos servidor
    private static final String HOST = "localhost";
    private static final int PORT = 2000;
    Scanner sc = new Scanner(System.in);
    String mensaje = "";

    public Cliente() {
        System.out.println("Introduce tu nombre: ");
        String nombre = sc.nextLine();


        try (Socket socket = new Socket(HOST, PORT)) { // Conectamos al servidor utilizando un Socket TCP
            // Crear el socket para comunicaciones UDP
            DatagramSocket udpSocket = new DatagramSocket(); // Puerto dinámico asignado automáticamente
            System.out.println("Cliente escuchando en el puerto UDP: " + udpSocket.getLocalPort());

            // Iniciar el cliente UDP para recibir notificaciones
            new ClienteUDP(udpSocket).start();

            // Creamos flujo de entrada/salida para enviar/recibir datos TCP.
            DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());

            // Registrar el puerto UDP con el servidor
            flujoSalida.writeUTF(nombre);  // Enviar nombre al servidor
            flujoSalida.writeInt(udpSocket.getLocalPort());  // Enviar el puerto UDP del cliente

            // Creamos un hilo de escucha de respuestas del servidor
            ClienteTCP clienteTCP = new ClienteTCP(flujoEntrada);
            clienteTCP.start();

            // Bucle para enviar datos al servidor
            while (!mensaje.equalsIgnoreCase("salir")) {
                mensaje = sc.nextLine();
                if (!mensaje.equalsIgnoreCase("salir")) {
                    flujoSalida.writeUTF(nombre + ": " + mensaje);
                }
            }

            // Cerramos recursos y notificamos al cliente
            System.out.println("Desconectando...");
            socket.close();
            udpSocket.close(); // Cerrar el socket UDP
            clienteTCP.detener();
            clienteTCP.join(); // Esperar a que el hilo termine
        } catch (Exception e) {
            System.out.println("Desconectando: " + e.getMessage());
        } finally {
            System.out.println("Desconectado correctamente.");
        }
    }
}
