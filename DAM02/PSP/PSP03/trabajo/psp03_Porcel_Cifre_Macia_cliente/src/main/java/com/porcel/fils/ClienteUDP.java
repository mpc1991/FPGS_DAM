package com.porcel.fils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClienteUDP extends Thread {
    private final DatagramSocket udpSocket; // Socket UDP para recibir mensajes del servidor

    public ClienteUDP(DatagramSocket udpSocket) {
        this.udpSocket = udpSocket;
    }

    @Override
    public void run() {
        try {
            // Buffer para almacenar los datos recibidos
            byte[] buffer = new byte[1024];

            while (true) {
                // Creamos un paquete para recibir datos
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                // Recibimos datos a través del socket UDP
                udpSocket.receive(packet);
                // Convertir los datos recibidos a una cadena de texto
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("NOTIFICACIÓN UDP: " + mensaje); // Mostramos el mensaje recibido
            }
        } catch (Exception e) {
            //System.err.println("Error en ClienteUDP: " + e.getMessage());
        } finally {
            udpSocket.close();
        }
    }
    // Método para detener el hilo
    public void detener() {
        this.interrupt();
    }
}
