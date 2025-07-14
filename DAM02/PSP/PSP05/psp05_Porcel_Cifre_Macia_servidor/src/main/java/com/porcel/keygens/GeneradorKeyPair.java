package com.porcel.keygens;

import java.security.*;

public class GeneradorKeyPair {
    public static KeyPair randomGenerate(int len) {
        KeyPair keys = null; // Variable para almacenar el par de claves generado
        try {
            // Crear un generador de pares de claves utilizando el algoritmo RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            // Inicializar el generador con la longitud especificada
            keyGen.initialize(len);

            // Generar el par de claves
            keys = keyGen.genKeyPair();
        } catch (Exception ex) {
            System.err.println("Generador no disponible.");
        }
        return keys;
    }
}
