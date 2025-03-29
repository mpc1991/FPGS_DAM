package com.porcel.keys;

import javax.crypto.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyHandlers {

    public static PublicKey obtenerClavePublica(String clavePublicaBase64) throws Exception {
        byte[] claveBytes = Base64.getDecoder().decode(clavePublicaBase64);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(claveBytes));
    }

    public static SecretKey generarClaveAES() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    public static String cifrarClaveAES(SecretKey clave, PublicKey publicKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] claveCifrada = cipher.doFinal(clave.getEncoded());
        return Base64.getEncoder().encodeToString(claveCifrada);
    }

    public static String cifrarMensajeAES(String mensaje, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String hash = generarHash(mensaje); // Generar hash
        String mensajeCompleto = hash + ":" + mensaje; // Concatenar hash y mensaje

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] mensajeCifrado = cipher.doFinal(mensajeCompleto.getBytes());
        return Base64.getEncoder().encodeToString(mensajeCifrado);
    }

    public static String generarHash(String mensaje) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
}
