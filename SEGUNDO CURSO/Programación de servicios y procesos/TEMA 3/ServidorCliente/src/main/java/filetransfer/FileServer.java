/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filetransfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MCCLA
 */
public class FileServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int port = 1500;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Creo flujos de entrada y salida
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                // Leo el nombre del fichero solicitado por el cliente
                String fileName = input.readUTF();
                System.out.println("Cliente solicita el fichero: " + fileName);

                File file = new File(fileName);
                if (file.exists() && file.isFile()) {
                    // Enviar confirmación de que el fichero existe
                    output.writeUTF("OK");

                    // Enviar el contenido del fichero
                    FileInputStream fileInput = new FileInputStream(file);
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = fileInput.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    fileInput.close();
                    System.out.println("Fichero enviado: " + fileName);
                } else {
                    // Enviar mensaje de error si el fichero no existe
                    output.writeUTF("ERROR: El fichero no existe.");
                    System.out.println("Fichero no encontrado: " + fileName);
                }

                // Cierro conexión con el cliente
                clientSocket.close();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
