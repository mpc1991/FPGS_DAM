package com.porcel.keygens;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

public class KeyHandler {
    public static SecretKey descifrarClaveAES(String claveCifradaBase64, PrivateKey privateKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] claveDescifrada = cipher.doFinal(Base64.getDecoder().decode(claveCifradaBase64));
        return new SecretKeySpec(claveDescifrada, "AES");
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
}
