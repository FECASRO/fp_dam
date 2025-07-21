/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 *
 * @author MCCLA
 */
public class GeneradorClave {

    // Método para generar la clave a partir del nombre de usuario y la contraseña
    public static SecretKey generarClave(String usuario, String password) throws NoSuchAlgorithmException {
        // Concatenar nombre de usuario y contraseña
        String semilla = usuario + password;
        
        // Usar SecureRandom con la semilla
        SecureRandom random = new SecureRandom(semilla.getBytes());
        
        // Generar un hash SHA-256
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] clave = sha256.digest(semilla.getBytes());
        
        // Tomar los primeros 16 bytes (128 bits) de la clave
        return new SecretKeySpec(clave, 0, 16, "AES");
    }
}
