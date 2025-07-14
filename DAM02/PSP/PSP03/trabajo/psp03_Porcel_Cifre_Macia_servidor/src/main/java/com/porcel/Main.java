package com.porcel;

import com.porcel.dto.InfoCliente;

import java.io.DataOutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    private final static int PORT = 2000; // Puerto comunicacion TCP
    private final static int UDP_PORT = 2001; // Puerto comunicacion UDP

    // Lista de clientes conectados
    static CopyOnWriteArrayList<InfoCliente> clientesConectados = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try (DatagramSocket udpSocket = new DatagramSocket(UDP_PORT)) {  // Socket UDP para notificaciones
            ServerSocket serverSocket = new ServerSocket(PORT); // Socket de tipo servidor TCP
            System.out.println("Escucho el puerto " + PORT );

            while (true) { // Bucle que va a aceptar conexiones y crear Threads para atenderlas
                Socket socketCliente = serverSocket.accept(); // esperamos conexion

                // Creamos Thread para atender las conexiones
                new Servidor(socketCliente, clientesConectados, udpSocket, UDP_PORT).start(); //Usamos un thread para atender al cliente
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
