/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio3;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author MCCLA
 */
public class FileClientAuth {

    /**
     * @param args the command line arguments
     */
   private static final String SERVER = "localhost";
    private static final int PORT = 1600;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER, PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(input.readUTF()); // "Introduce usuario:"
            output.writeUTF(scanner.nextLine());

            System.out.println(input.readUTF()); // "Introduce contraseña:"
            output.writeUTF(scanner.nextLine());

            String response = input.readUTF();
            System.out.println(response);
            if (response.startsWith("ERROR")) return;

            while (true) {
                System.out.println("Selecciona una opción:");
                System.out.println("1. Listar archivos\n2. Leer archivo\n3. Salir");
                String option = scanner.nextLine();
                output.writeUTF(option);

                response = input.readUTF();
                System.out.println(response);

                if (option.equals("2")) {
                    System.out.println("Introduce el nombre del archivo:");
                    output.writeUTF(scanner.nextLine());

                    String fileResponse;
                    while (!(fileResponse = input.readUTF()).equals("EOF")) {
                        System.out.println(fileResponse);
                    }
                } else if (option.equals("3")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
}