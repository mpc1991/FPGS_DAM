package com.porcel.client;

import com.porcel.fils.Ctcp;
import com.porcel.fils.Cudp;

import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

import static com.porcel.auxiliars.Handlers.*;

public class Client{
    private static final String HOST = "localhost";
    private static final int PORT = 5000;
    private PublicKey publicKey;
    private SecretKey secretKey;

    Scanner sc = new Scanner(System.in);
    String msg = "";

    public Client() {
        try(Socket s = new Socket(HOST, PORT);
            DatagramSocket udps = new DatagramSocket();
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            System.out.println("Iniciando servidor, escribe tu nombre");
            String nombre = sc.nextLine();
            out.writeUTF(nombre); // nombre
            out.writeInt(udps.getLocalPort()); // env PuertoUDP

            // 1. Solicita clave pública al srv
            System.out.println("Obteniendo clave publica");
            out.writeUTF("getPubKey");
            String clavePublica = in.readUTF();
            publicKey = obtenerClavePublica(clavePublica);

            // 2. Genera y envía clave AES cifrada
            System.out.println("Generando Secret Key");
            secretKey = generarClaveAES();
            String claveAEScifrada = cifrarClaveAES(secretKey, publicKey);
            out.writeUTF("CLAVE_AES "  + claveAEScifrada);

            System.out.println("Generando hilos TCOP y UDP");
            Ctcp ctcp = new Ctcp(in, secretKey); ctcp.start();
            Cudp cudp = new Cudp(udps); cudp.start();

            while(!msg.equalsIgnoreCase("salir")){
                // Esperar antes de la siguiente iteración
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("Interrupción en la espera: " + e.getMessage());
                }
                try {
                    System.out.println("Escribe tu mensaje:");
                    msg = sc.nextLine();
                    if (!msg.equalsIgnoreCase("salir")){
                        //System.out.println("descifrando secre: " + secretKey);
                        String msgCifrado = cifrarMensajeAES(nombre + ": " +msg, secretKey);
                        out.writeUTF(msgCifrado);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);

                }

            }

            sc.close();
            udps.close();
            in.close();
            out.close();
            //ctcp.detener();
            s.close();
            //ctcp.join();
            cudp.detener();
            cudp.join();
        } catch(Exception e) {
            System.out.println("Cerrando aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
