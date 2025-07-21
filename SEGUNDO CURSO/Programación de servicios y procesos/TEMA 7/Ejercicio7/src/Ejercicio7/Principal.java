/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio7;

import javax.crypto.SecretKey;

/**
 *
 * @author MCCLA
 */
public class Principal {

    public static void main(String[] args) {
        // Variables de usuario y contraseña (puedes personalizarlas)
        String usuario = "usuario123";
        String password = "contraseña";

        try {
            // Generar la clave
            SecretKey clave = GeneradorClave.generarClave(usuario, password);
            
            // El mensaje a encriptar
            String mensaje = "Este es un mensaje secreto.";

            // Encriptar el mensaje y guardarlo en un archivo
            Encriptador.encriptar(mensaje, "fichero.cifrado", clave);
            System.out.println("Mensaje encriptado y guardado en 'fichero.cifrado'.");
            
            // Desencriptar el archivo y mostrar el contenido
            String mensajeDesencriptado = Desencriptador.desencriptar("fichero.cifrado", clave);
            System.out.println("Mensaje desencriptado: " + mensajeDesencriptado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}