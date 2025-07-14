package com.porcel.auxiliars;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;
//RSA se usa para enviar la clave AES cifrada de forma segura.
//AES se usa para cifrar los datos reales.
public class Handlers {
    // KeyPar
    public static KeyPair generarKeyPar(int len) {
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

    // SecretKey
    public static SecretKey generarClaveAES() {
        SecretKey sKey = null;
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(256);
                sKey = kgen.generateKey();

            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        return sKey;
    }

    // Metodo para descifrar una clave AES previamente cifrada con una clave pública RSA
    public static String descifrarClaveAES(String claveCifradaBase64, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey); // Inicializa el descifrado
        byte[] claveDescifrada = cipher.doFinal(Base64.getDecoder().decode(claveCifradaBase64)); // Descifra la clave
        return new String(claveDescifrada); // Devuelve la clave en texto plano
    }

    // Convierte una clave AES en formato Base64 a un objeto SecretKey
    public static SecretKey obtenerClaveAESDesdeBase64(String claveBase64) {
        byte[] decodedKey = Base64.getDecoder().decode(claveBase64); //  Decodifica la clave en Base64
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); // Crea y devuelve un objeto SecretKey para AES
    }

    // Verifica si el hash calculado del mensaje coincide con el hash recibido.
    public static boolean verificarHash(String mensaje, String hashRecibido) throws NoSuchAlgorithmException {
        String hashCalculado = generarHash(mensaje);
        //System.out.println(hashRecibido + " hash received");
        //System.out.println(hashCalculado + "hash calculated");
        return hashCalculado.equals(hashRecibido);
    }

    // Genera un hash del mensaje utilizando el algoritmo SHA-256.
    private static String generarHash(String mensaje) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    // Metodo para cifrar un mensaje utilizando el algoritmo AES.
    public static String cifrarMensajeAES(String mensaje, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String hash = generarHash(mensaje); // Generar hash a partir del mensaje
        String mensajeCompleto = hash + ":" + mensaje; // Concatenar hash + mensaje

        // Crear Objeto Cipher para cifrar con AES
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Iniciar el cifrado con la clave secreta

        // Cifrar los datos y devolverlos
        byte[] mensajeCifrado = cipher.doFinal(mensajeCompleto.getBytes());
        return Base64.getEncoder().encodeToString(mensajeCifrado);
    }

    // Metodo para descifrar un mensaje cifrado utilizando el algoritmo AES.
    public static String descifrarMensajeAES(String mensajeCifradoBase64, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] mensajeDescifrado = cipher.doFinal(Base64.getDecoder().decode(mensajeCifradoBase64));
        String completo = new String(mensajeDescifrado);
        String[] partes = completo.split(":", 2);

        if (partes.length != 2) throw new SecurityException("Mensaje corrupto o mal formado");

        String hashOriginal = partes[0];
        String mensaje = partes[1];
        String hashCalculado = generarHash(mensaje);

        if (!hashOriginal.equals(hashCalculado)) {
            throw new SecurityException("Integridad del mensaje comprometida");
        }

        return mensaje;
    }
}