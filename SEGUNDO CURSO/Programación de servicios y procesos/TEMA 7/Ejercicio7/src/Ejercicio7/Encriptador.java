/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.CipherOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author MCCLA
 */
public class Encriptador {

    // Método para encriptar texto
    public static void encriptar(String mensaje, String archivoCifrado, SecretKey clave) throws Exception {
        // Obtener instancia del algoritmo AES
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        
        // Crear el archivo de salida cifrado
        try (FileOutputStream fos = new FileOutputStream(archivoCifrado);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {
            cos.write(mensaje.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
