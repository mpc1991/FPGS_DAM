package com.porcel.dto;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.util.Objects;

public class Cliente {
    String nombre;
    private final InetAddress ip;
    private final int puerto;
    private final DataOutputStream out;
    private final int UDPport;

    public Cliente(String nombre, InetAddress ip, int puerto,
                   DataOutputStream out, int UDPport) {
        this.nombre= nombre;
        this.ip = ip;
        this.puerto = puerto;
        this.out = out;
        this.UDPport = UDPport;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getUDPport() {
        return UDPport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return puerto == cliente.puerto && UDPport == cliente.UDPport && Objects.equals(nombre, cliente.nombre) && Objects.equals(ip, cliente.ip) && Objects.equals(out, cliente.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, ip, puerto, out, UDPport);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", ip=" + ip +
                ", puerto=" + puerto +
                ", out=" + out +
                ", UDPport=" + UDPport +
                '}';
    }
}
