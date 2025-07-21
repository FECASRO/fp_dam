/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author MCCLA
 */
public class FileServerAuth {

    /**
     * @param args the command line arguments
     */
   private static final int PORT = 1600;
     static final String USERNAME = "javier";
     static final String PASSWORD = "secreta";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escuchando en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                new ClientHandler(clientSocket).start(); // Manejar cada cliente en un hilo
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            output.writeUTF("Introduce usuario:");
            String user = input.readUTF();
            output.writeUTF("Introduce contraseña:");
            String pass = input.readUTF();

            if (!user.equals(FileServerAuth.USERNAME) || !pass.equals(FileServerAuth.PASSWORD)) {
                output.writeUTF("ERROR: Usuario o contraseña incorrectos.");
                socket.close();
                return;
            }

            output.writeUTF("Autenticación correcta. Menú:\n1. Listar archivos\n2. Leer archivo\n3. Salir");

            while (true) {
                String option = input.readUTF();
                switch (option) {
                    case "1":
                        listFiles(output);
                        break;
                    case "2":
                        output.writeUTF("Introduce el nombre del archivo:");
                        String fileName = input.readUTF();
                        sendFileContent(fileName, output);
                        break;
                    case "3":
                        output.writeUTF("Sesión cerrada.");
                        socket.close();
                        return;
                    default:
                        output.writeUTF("Opción no válida.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error con el cliente: " + e.getMessage());
        }
    }

    private void listFiles(DataOutputStream output) throws IOException {
        File directory = new File(".");
        File[] files = directory.listFiles();

        if (files != null) {
            StringBuilder fileList = new StringBuilder("Archivos disponibles:\n");
            for (File file : files) {
                if (file.isFile()) {
                    fileList.append(file.getName()).append("\n");
                }
            }
            output.writeUTF(fileList.toString());
        } else {
            output.writeUTF("No se pudieron listar los archivos.");
        }
    }

    private void sendFileContent(String fileName, DataOutputStream output) throws IOException {
        File file = new File(fileName);

        if (file.exists() && file.isFile()) {
            output.writeUTF("OK");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.writeUTF(line);
                }
                output.writeUTF("EOF"); // Marca de final de archivo
            }
        } else {
            output.writeUTF("ERROR: El archivo no existe.");
        }
    }
}