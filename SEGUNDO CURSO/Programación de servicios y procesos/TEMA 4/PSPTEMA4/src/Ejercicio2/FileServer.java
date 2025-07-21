/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MCCLA
 */
public class FileServer {

    private static final int PUERTO = 1500;

    public static void main(String[] args) {
        System.out.println("Servidor escuchando en el puerto " + PUERTO);

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Crear un hilo para manejar el cliente
                new ManejadorCliente(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

/**
 * Clase que maneja cada cliente en un hilo separado.
 */
class ManejadorCliente extends Thread {

    private Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {
            // Leer el nombre del archivo solicitado por el cliente
            String fileName = input.readUTF();
            System.out.println("Cliente solicita el fichero: " + fileName);

            File file = new File(fileName);
            System.out.println("Buscando archivo en: " + file.getAbsolutePath());
            if (file.exists() && file.isFile()) {
                output.writeUTF("OK");

                // Enviar el contenido del fichero al cliente
                try (FileInputStream fileInput = new FileInputStream(file)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInput.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("Fichero enviado: " + fileName);
            } else {
                output.writeUTF("ERROR: El fichero no existe.");
                System.out.println("Fichero no encontrado: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Error con el cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error cerrando conexión con cliente: " + e.getMessage());
            }
        }
    }
}
