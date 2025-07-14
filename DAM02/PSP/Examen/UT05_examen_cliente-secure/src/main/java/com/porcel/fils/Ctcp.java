package com.porcel.fils;

import com.porcel.auxiliars.Handlers;

import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.IOException;

public class Ctcp extends Thread {
    private final DataInputStream in;
    private SecretKey secretKey;

    public Ctcp(DataInputStream in, SecretKey secretKey) {
        this.in = in;
        this.secretKey = secretKey;
    }

    @Override
    public void run(){
        try {
            while(!isInterrupted()){
                String msgCifrado = in.readUTF();
                //System.out.println("cifrado:" + msgCifrado);
                String msgDesCifrado = Handlers.descifrarMensajeAES(msgCifrado, secretKey);
                System.out.println(msgDesCifrado);
            }
        } catch (IOException e) {
            //System.out.println("Saliendo del hilo tcp");
        } catch (Exception e) {
            //System.out.println("Saliendo del hilo tcp");
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
