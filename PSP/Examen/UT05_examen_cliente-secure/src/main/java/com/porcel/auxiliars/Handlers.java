package com.porcel.auxiliars;

import javax.crypto.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
//RSA keyPar
//AES se usa para cifrar los datos reales. (secretKey)
public class Handlers {

    // Obtener la clave pública codificada
    public static PublicKey obtenerClavePublica(String clavePublicaBase64) throws Exception {
        // Decodificar la clave publica
        byte[] claveBytes = Base64.getDecoder().decode(clavePublicaBase64);

        // Crear Objeto Keyfactory para generar claves RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        // Crear la clave pub
        return keyFactory.generatePublic(new X509EncodedKeySpec(claveBytes));
    }

    // Generar clave AES con tamaño 256 bits
    public static SecretKey generarClaveAES() throws NoSuchAlgorithmException {
        // Crear generador de claves para el algoritmo AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // Inicializar con una longitud de 256 bits
        return keyGen.generateKey(); // Generar y devolver la clave
    }

    // Cifra la clave AES usando la clave pública del servidor con algoritmo RSA // verificar integridad
    public static String cifrarClaveAES(SecretKey clave, PublicKey publicKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        // Generar un hash de la clave AES
        String hashClave = generarHash(Base64.getEncoder().encodeToString(clave.getEncoded()));

        // Concatenar el hash y la clave AES
        String datosParaCifrar = hashClave + ":" + Base64.getEncoder().encodeToString(clave.getEncoded());

        // Crear Objeto Cipher para cifrar con RSA
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); // Iniciar el cifrado con la clave pub

        // Cifrar los datos y devolverlos
        byte[] claveCifrada = cipher.doFinal(datosParaCifrar.getBytes());
        return Base64.getEncoder().encodeToString(claveCifrada);
    }

    // Generar un hash SHA256 a partir de un mensaje
    public static String generarHash(String mensaje) throws NoSuchAlgorithmException {
        // Crear un Objeto MessageDigest para usar SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(mensaje.getBytes()); // Generar el hash
        return Base64.getEncoder().encodeToString(hash); // Devolver el hash en formato Base64
    }

    // Descifrar un mensaje cifrado con AES
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

    // Cifrar mensaje utilizando clave secreta AES
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
}
