/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.CipherInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.security.InvalidKeyException;

/**
 *
 * @author MCCLA
 */
public class Desencriptador {

    // Método para desencriptar el contenido de un archivo
    public static String desencriptar(String archivoCifrado, SecretKey clave) throws Exception {
        // Obtener instancia del algoritmo AES
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        
        // Leer el archivo cifrado y desencriptar
        try (FileInputStream fis = new FileInputStream(archivoCifrado);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             InputStreamReader isr = new InputStreamReader(cis);
             BufferedReader br = new BufferedReader(isr)) {
             
            StringBuilder resultado = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                resultado.append(linea);
            }
            return resultado.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}