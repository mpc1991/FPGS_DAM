package com.porcel.keygens;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

public class KeyHandler {
    public static String descifrarClaveAES(String claveCifradaBase64, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] claveDescifrada = cipher.doFinal(Base64.getDecoder().decode(claveCifradaBase64));
        return new String(claveDescifrada); // Devuelve la clave en texto plano junto con el hash
    }

    public static SecretKey obtenerClaveAESDesdeBase64(String claveBase64) {
        byte[] decodedKey = Base64.getDecoder().decode(claveBase64);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static  String descifrarMensajeAES(String mensajeCifradoBase64, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] mensajeDescifrado = cipher.doFinal(Base64.getDecoder().decode(mensajeCifradoBase64));
        return new String(mensajeDescifrado);
    }

    public static boolean verificarHash(String mensaje, String hashRecibido) throws NoSuchAlgorithmException {
        String hashCalculado = generarHash(mensaje);
        return hashCalculado.equals(hashRecibido);
    }

    private static String generarHash(String mensaje) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    public static String cifrarMensajeAES(String mensaje, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] mensajeCifrado = cipher.doFinal(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(mensajeCifrado);
    }
}
