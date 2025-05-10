package com.porcel.fils.FServer;

import com.porcel.auxiliars.Handlers;
import com.porcel.dto.Cliente;
import com.porcel.servidor.Server;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.List;

import static com.porcel.auxiliars.Handlers.*;

public class FServer extends Thread {
    public Socket s;
    public DatagramSocket udpS;
    String msg = "";
    private final String logPath= "log.log";

    private final PrivateKey privateKey; // Clave privada del servidor utilizada para descifrar mensajes
    private PublicKey publicKey; // Clave pública del servidor, que se envía a los clientes para que puedan cifrar los datos
    private SecretKey secretKey; // Clave secreta simétrica (AES) utilizada para cifrar y descifrar los mensajes con el cliente

    public FServer(Socket socket, DatagramSocket udpS, KeyPair keyPair) {
        this.s = socket;
        this.udpS = udpS;
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }
    @Override
    public void run() {
        Cliente cliente = null;
        try (DataInputStream in = new DataInputStream(s.getInputStream());
             DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            String nombre = in.readUTF();
            int puertoUDP = in.readInt();
            System.out.println(nombre + ", "+  puertoUDP);
            cliente = new Cliente(nombre, s.getInetAddress(), s.getPort(), out, puertoUDP);
            Server.addClientes(cliente);
            System.out.println(nombre + " añadido a la lista");

            String msgUDP = nombre + " ha conectado";
            broadcastUDP(msgUDP, cliente);

            while ((msg = in.readUTF()) != null) {
                //System.out.println(msg);
                if (msg.equals("salir")) {
                    broadcastUDP(cliente.getNombre() + " se ha desconectado.", cliente);
                    desconectarCliente(cliente);
                } else if (msg.equals("getPubKey")) {
                    String clavePublica = Base64.getEncoder().encodeToString(publicKey.getEncoded());
                    out.writeUTF(clavePublica);
                    //System.out.println("Clave pública enviada al cliente: " + cliente.getNombre());
                    //System.out.println("Clave pública enviada en Base64: " + clavePublica);

                } else if (msg.startsWith("CLAVE_AES")) {
                    try {
                        // Recibir y descifrar la clave AES
                        String[] partes = msg.split(" ", 2);
                        if (partes.length < 2) {
                            System.out.println("Error: formato de CLAVE_AES incorrecto. ");
                            continue;
                        }
                        String claveCifradaBase64 = partes[1];
                        String datosDescifrados = descifrarClaveAES(claveCifradaBase64, privateKey);

                        // Separar el hash de la clave y la clave en sí
                        String[] datos = datosDescifrados.split(":", 2);
                        if (datos.length < 2) {
                            System.out.println("Error: clave simétrica descifrada mal formada.");
                            continue;
                        }

                        String hashRecibido = datos[0];
                        String claveSimetrica = datos[1];

                        // Verificar la integridad de la clave AES
                        if (verificarHash(claveSimetrica, hashRecibido)) {
                            secretKey = obtenerClaveAESDesdeBase64(claveSimetrica);
                            cliente.setSecretKey(secretKey);
                            //System.out.println("Clave AES recibida y verificada correctamente.");
                            String acuseCifrado = cifrarMensajeAES("DataReceived", secretKey);
                            out.writeUTF(acuseCifrado); // Acuse de recibo cifrado
                        } else {
                            System.out.println("¡Alerta! Integridad de clave AES comprometida.");
                            s.close();
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Error al procesar CLAVE_AES: " + e.getMessage());
                        s.close();
                        return;
                    }
                } else {
                    //System.out.println("estado msg" + msg);
                    broadcastTCP(msg, cliente);

                }
                registrarLog(msg);
            }
        } catch (Exception e) {
            broadcastUDP(cliente.getNombre() + " se ha desconectado.", cliente);
            desconectarCliente(cliente);
        } finally {
            broadcastUDP(cliente.getNombre() + " se ha desconectado.", cliente);
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
    private void broadcastTCP(String msgCifrado, Cliente c) throws Exception {
        List<Cliente> clientes = com.porcel.servidor.Server.getClientes();

        for (Cliente cliente : clientes) {
            try {
                if (!cliente.equals(c)) {
                    String msgDescifrado = Handlers.descifrarMensajeAES(msgCifrado, c.getSecretKey());
                    String msgReCifrado = Handlers.cifrarMensajeAES(msgDescifrado,cliente.getSecretKey());
                    System.out.println("broadcastTCP a " + cliente.getNombre());
                    System.out.println("mensaje " + msgReCifrado);
                    cliente.getOut().writeUTF(msgReCifrado);
                }
            } catch (IOException e) {
                System.err.println("Error enviando notificación TCP: " + e.getMessage());
            }
        }
    }

    // Metodo para enviar notificaciones UDP a todos los clientes
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
