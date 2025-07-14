package com.porcel.servidor;

import com.porcel.auxiliars.Handlers;
import com.porcel.dto.Cliente;
import com.porcel.fils.FServer.FServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

public class Server {
    int PORT = 5000;
    int PORT_UDP = 5001;
    private static KeyPair keyPair;

    private static List<Cliente> clientes = new ArrayList();

    public Server(){
        try (ServerSocket ss = new ServerSocket(PORT);
             DatagramSocket udpS = new DatagramSocket(PORT_UDP)){

            // Generar par de claves RSA(publica y privada)
            keyPair = Handlers.generarKeyPar(2048);

            while (true) {
                System.out.println("Servidor iniciado, esperando conexión");
                Socket s = ss.accept();
                System.out.println("Conexión aceptada");

                new FServer(s, udpS, keyPair).start();
                System.out.println("Hilo creado");
            }
        } catch (IOException e) {
        }

    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void addClientes(Cliente cliente) {
        clientes.add(cliente);
    }

    public static void removeCliente(Cliente cliente) {
        clientes.remove(cliente);
    }
}
