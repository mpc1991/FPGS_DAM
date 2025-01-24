package com.porcel;

import com.porcel.dto.InfoCliente;

import java.io.*;
import java.net.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Servidor extends Thread {
    private final Socket socketCliente; // Socket TCP para comunicaciones
    private final DatagramSocket udpSocket; // Socket UDP para notificaciones
    private final int udpPort; // Puerto UDP
    private static CopyOnWriteArrayList<InfoCliente> clientesConectados = new CopyOnWriteArrayList<>();
    private static final String logPath = "log.log";

    public Servidor(Socket socketCliente, CopyOnWriteArrayList<InfoCliente> clientesConectados, DatagramSocket udpSocket, int udpPort) {
        this.socketCliente = socketCliente;
        this.clientesConectados = clientesConectados;
        this.udpSocket = udpSocket;
        this.udpPort = udpPort;
    }

    public void run() {
        InfoCliente infoCliente = null;

        try (DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
             DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream())) {

            // Registrar cliente
            String nombreCliente = flujoEntrada.readUTF(); // Almaceno el nombre del cliente
            int puertoUDPCliente = flujoEntrada.readInt();  // Almaceno el puerto UDP del cliente

            // Creamos el cliente
            infoCliente = new InfoCliente(nombreCliente, socketCliente.getInetAddress(), socketCliente.getPort(), flujoSalida, puertoUDPCliente);
            clientesConectados.add(infoCliente);
            System.out.println("Cliente conectado, información:");
            System.out.println(infoCliente.toString());

            // Notificar vía UDP
            String mensajeUDP = (nombreCliente + " se ha conectado.");
            enviarNotificacionUDP(mensajeUDP, infoCliente);


            // Enviar mensaje de confirmación al cliente
            flujoSalida.writeUTF("MSG TCP Servidor - Se ha conectado de forma correcta.");
            flujoSalida.writeUTF("MSG TCP Servidor - Puedes empezar a chatear.");

            String mensajeTCP;
            while ((mensajeTCP = flujoEntrada.readUTF()) != null) {
                System.out.println("Mensaje recibido: " + mensajeTCP);
                enviarBroadcastTCP(mensajeTCP, infoCliente);
                registrarLog(mensajeTCP);
            }
        } catch (Exception e) {
            // Cliente ha cerrado la conexión o ha sido desconectado
        } finally {
            // En cualquier caso, limpiamos la lista y notificamos a los demás clientes
            if (infoCliente != null) {
                desconectarCliente(infoCliente);
                try {
                    socketCliente.close(); // Asegurarse de cerrar el socket para evitar fugas de recursos
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void registrarLog(String log){
        try (BufferedWriter br = new BufferedWriter(new FileWriter(logPath, true))) {
            br.write(log);
            br.newLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo para quitar el cliente de la lista y notificar a los demas
    private void desconectarCliente(InfoCliente clienteInfo) {
        // Limpiamos la lista de clientes
        clientesConectados.remove(clienteInfo);
        enviarNotificacionUDP(clienteInfo.getNombre() + " se ha desconectado.", clienteInfo);
    }

    // Metodo para enviar notificaciones TCP a todos los clientes
    private void enviarBroadcastTCP(String mensaje, InfoCliente infoCliente) {
        for (InfoCliente cliente : clientesConectados) {
            try {
                if (!cliente.equals(infoCliente)) {
                    cliente.getFlujoSalida().writeUTF(mensaje);
                }
            } catch (IOException e) {
                System.err.println("Error enviando notificación TCP: " + e.getMessage());
            }
        }
    }

    // Metodo para enviar notificaciones UDP a todos los clientes
    private void enviarNotificacionUDP(String mensaje, InfoCliente remitente) {
        registrarLog("UDP - " + mensaje);
        byte[] buffer = mensaje.getBytes();
        try {
            for (InfoCliente cliente : clientesConectados) {
                if (!cliente.equals(remitente)) {
                    InetAddress address = cliente.getIp();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, cliente.getPuertoUDP());
                    udpSocket.send(packet);  // Enviar notificación UDP al puerto correcto
                }
            }
        } catch (IOException e) {
            System.err.println("Error enviando notificación UDP: " + e.getMessage());
        }
    }
}
