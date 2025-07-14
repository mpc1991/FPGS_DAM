package com.porcel.fils;

import com.porcel.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Nodo extends Thread {
    private String nombre;
    private int puerto;
    private int puertoSiguiente;
    private boolean isInicio;

    public Nodo(String nombre, int puerto, int puertoSiguiente, boolean isInicio) {
        this.nombre = nombre;
        this.puerto = puerto;
        this.puertoSiguiente = puertoSiguiente;
        this.isInicio = isInicio;
    }

    @Override
    public void run() {
        String msg = "";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            if (!isInicio) {
                byte[] buffer = new byte[1024];
                    DatagramPacket p = new DatagramPacket(buffer, buffer.length);
                    socket.receive(p);
                    msg = new String(p.getData(), 0, p.getLength());
            }

            Main.msg += nombre;
            System.out.println(Main.msg);

            try {
                byte[] buffer = msg.getBytes();
                DatagramPacket p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), puertoSiguiente);

                DatagramSocket socket2 = new DatagramSocket();
                socket2.send(p);

                if(isInicio){
                    byte[] buffe = new byte[1024];
                    DatagramPacket p1 = new DatagramPacket(buffer, buffer.length);
                    socket.receive(p1);
                    msg = new String(p1.getData(), 0, p1.getLength());

                    System.out.println("Mensaje desde nodo 1: " + Main.msg);
                }
            } catch (Exception e) {

            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
