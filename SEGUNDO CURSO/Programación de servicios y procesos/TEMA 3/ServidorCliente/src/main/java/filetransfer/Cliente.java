/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filetransfer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author MCCLA
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
 final String SERVIDOR = "localhost";
        final int PUERTO = 1500;

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());) {

            Scanner scanner = new Scanner(System.in);

            // Solicitar al usuario el nombre del fichero
            System.out.print("Introduce el nombre del fichero a solicitar: ");
            String nombreFichero = scanner.nextLine();

            // Enviar el nombre del fichero al servidor
            out.writeUTF(nombreFichero);

            // Leer respuesta del servidor
            String respuesta = in.readUTF();

            if (respuesta.equals("OK")) {
                System.out.println("Fichero encontrado. Recibiendo contenido...");
  // Leer el contenido del fichero y mostrarlo en pantalla
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            } else {
                // Mostrar mensaje de error
                System.out.println(respuesta);
            }
        } catch (IOException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
}
