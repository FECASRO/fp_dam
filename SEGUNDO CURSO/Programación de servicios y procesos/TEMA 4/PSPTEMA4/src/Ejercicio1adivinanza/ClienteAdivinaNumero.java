package Ejercicio1adivinanza;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author MCCLA
 */
public class ClienteAdivinaNumero {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 2000;

        try (
            Socket socket = new Socket(serverAddress, port);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor Adivina el número.");

            String serverResponse;
            while ((serverResponse = input.readLine()) != null) {
                System.out.println("Servidor: " + serverResponse);

                // Si el servidor nos dice que hemos ganado, salimos
                if (serverResponse.contains("¡Felicidades")) {
                    break;
                }

                // Pedir un número y validarlo
                String userInput;
                int numeroCliente;
                while (true) {
                    System.out.print("Introduce tu número: ");
                    userInput = reader.readLine();
                    
                    try {
                        numeroCliente = Integer.parseInt(userInput);
                        break; // Salimos del bucle si es un número válido
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Debes introducir un número válido.");
                    }
                }

                // Enviar número al servidor
                output.println(numeroCliente);
            }
        } catch (IOException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
}