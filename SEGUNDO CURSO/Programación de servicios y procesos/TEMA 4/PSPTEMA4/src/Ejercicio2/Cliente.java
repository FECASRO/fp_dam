/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author MCCLA
 */
public class Cliente {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 1500;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            // Pedir el nombre del archivo al usuario
            System.out.print("Introduce el nombre del fichero a solicitar: ");
            String nombreFichero = scanner.nextLine();

            // Enviar el nombre al servidor
            out.writeUTF(nombreFichero);

            // Leer respuesta del servidor
            String respuesta = in.readUTF();

            if (respuesta.equals("OK")) {
                System.out.println("Fichero encontrado. Recibiendo contenido...");

                // Crear un archivo local para almacenar los datos recibidos
                FileOutputStream fileOutput = new FileOutputStream("descargado_" + nombreFichero);
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    fileOutput.write(buffer, 0, bytesRead);
                }

                fileOutput.close();
                System.out.println("Fichero recibido y guardado como: descargado_" + nombreFichero);
            } else {
                System.out.println(respuesta); // Mensaje de error si el archivo no existe
            }
        } catch (IOException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
}
