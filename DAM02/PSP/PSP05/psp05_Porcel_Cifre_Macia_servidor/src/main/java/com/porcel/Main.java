package com.porcel;

import com.porcel.keygens.GeneradorKeyPair;
import com.porcel.server.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;

public class Main {
    public static void main(String[] args) {
        new Server();
    }
}