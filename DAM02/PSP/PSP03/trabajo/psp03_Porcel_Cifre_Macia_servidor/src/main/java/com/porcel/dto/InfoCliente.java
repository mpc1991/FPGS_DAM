package com.porcel.dto;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.util.Objects;

public class InfoCliente {
    private final String nombre;
    private final InetAddress ip;
    private final int puerto;
    private final DataOutputStream flujoSalida;
    private final int puertoUDP;

    // Constructor actualizado para aceptar puerto UDP
    public InfoCliente(String nombre, InetAddress ip, int puerto, DataOutputStream flujoSalida, int puertoUDP) {
        this.nombre = nombre;
        this.ip = ip;
        this.puerto = puerto;
        this.puertoUDP = puertoUDP;  // Guardamos el puerto UDP
        this.flujoSalida = flujoSalida;
    }

    public String getNombre() {
        return nombre;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public DataOutputStream getFlujoSalida() {
        return flujoSalida;
    }

    // Getter para el puerto UDP
    public int getPuertoUDP() {
        return puertoUDP;
    }

    @Override
    public String toString() {
        return "InfoCliente{" +
                "nombre='" + nombre + '\'' +
                ", ip='" + ip + '\'' +
                ", puerto=" + puerto +
                ", puertoUDP=" + puertoUDP +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoCliente that = (InfoCliente) o;
        return puerto == that.puerto && puertoUDP == that.puertoUDP && Objects.equals(nombre, that.nombre) && Objects.equals(ip, that.ip) && Objects.equals(flujoSalida, that.flujoSalida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, ip, puerto, flujoSalida, puertoUDP);
    }
}
