package com.porcel.fils;

import java.io.DataInputStream;
import java.io.IOException;

public class Ctcp extends Thread {
    private final DataInputStream in;

    public Ctcp(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run(){
        String msg = "";
        try {
            while(!isInterrupted()){
                msg = in.readUTF();
                System.out.println(msg);
            }
        } catch (IOException e) {
            // System.out.println("Saliendo del hilo tcp");
        } finally {
            try {
                in.close();
                this.interrupt();
            } catch (Exception e) {
            }
        }
    }
    // Método para detener el hilo
    public void detener() {
        this.interrupt();
    }
}
