package com.porcel.fils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Cudp extends Thread {
    private final DatagramSocket udps;

    public Cudp(DatagramSocket udps) {
        this.udps = udps;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        while (!isInterrupted()) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            try {
                udps.receive(p);
                String msg = new String(p.getData(),0,p.getLength());
                System.out.println(msg);
            } catch (Exception e) {
                //System.out.println("Saliendo del hilo udp");
            }
        }
        try {
            udps.close();
        } catch (Exception e) {}
    }
    // Método para detener el hilo
    public void detener() {
        this.interrupt();
    }
}
