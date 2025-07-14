package com.porcel.fils.FServer;

import com.porcel.dto.Cliente;
import com.porcel.servidor.Server;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class FServer extends Thread {
    public Socket s;
    public DatagramSocket udpS;
    String msg = "";
    private final String logPath= "log.log";

    public FServer(Socket socket, DatagramSocket udpS) {
        this.s = socket;
        this.udpS = udpS;
    }
    @Override
    public void run() {
        Cliente cliente = null;
        try (DataInputStream in = new DataInputStream(s.getInputStream());
             DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            String nombre = in.readUTF();
            int puertoUDP = in.readInt();
            System.out.println(nombre + ", "+  puertoUDP);
            cliente = new Cliente(nombre, s.getInetAddress(), out, puertoUDP);
            Server.addClientes(cliente);
            System.out.println(nombre + " añadido a la lista");

            String msgUDP = nombre + " ha conectado";
            broadcastUDP(msgUDP, cliente);

            out.writeUTF("Conectado, puedes empezar.");

            while ((msg = in.readUTF()) != null) {
                System.out.println(msg);
                if (!"salir".equals(msg)) {
                    broadcastTCP(msg, cliente);
                }
                registrarLog(msg);
            }
        } catch (Exception e) {
            //desconectarCliente(cliente);
        } finally {
            desconectarCliente(cliente);
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
    private void desconectarCliente(Cliente cliente) {
        // Limpiamos la lista de clientes
        broadcastUDP(cliente.getNombre() + " se ha desconectado.", cliente);
        com.porcel.servidor.Server.removeCliente(cliente);
    }

    // Metodo para enviar notificaciones TCP a todos los clientes
    // Necesita DataOutputStream out del cliente
    private void broadcastTCP(String mensaje, Cliente c) {
        List<Cliente> clientes = com.porcel.servidor.Server.getClientes();
        for (Cliente cliente : clientes) {
            try {
                if (!cliente.equals(c)) {
                    cliente.getOut().writeUTF(mensaje);
                }
            } catch (IOException e) {
                System.err.println("Error enviando notificación TCP: " + e.getMessage());
            }
        }
    }

    // Metodo para enviar notificaciones UDP a todos los clientes
    // Necesita IP+PuertoUDP
    private void broadcastUDP(String mensaje, Cliente c) {
        List<Cliente> clientes = com.porcel.servidor.Server.getClientes();
        registrarLog("UDP - " + mensaje);
        byte[] buffer = mensaje.getBytes();
        try {
            for (Cliente cliente : clientes) {
                if (!cliente.equals(c)) {
                    InetAddress address = cliente.getIp();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, cliente.getUDPport());
                    udpS.send(packet);  // Enviar notificación UDP al puerto correcto
                }
            }
        } catch (IOException e) {
            System.err.println("Error enviando notificación UDP: " + e.getMessage());
        }
    }
}
