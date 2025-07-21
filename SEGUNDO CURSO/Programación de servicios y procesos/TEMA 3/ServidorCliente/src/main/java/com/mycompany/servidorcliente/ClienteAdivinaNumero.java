/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.servidorcliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author MCCLA
 */
public class ClienteAdivinaNumero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   final String HOST = "localhost";
        final int PUERTO = 2000;
        Socket socket = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Conexión al servidor
            socket = new Socket(HOST, PUERTO);
            System.out.println("Conectado al servidor en el puerto " + PUERTO);

            // Streams de entrada y salida
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            String respuesta;
            boolean acertado = false;

            // Bucle para enviar números hasta acertar
            while (!acertado) {
                System.out.print("Introduce un número entre 0 y 100: ");
                String numero = scanner.nextLine();
                salida.println(numero); // Envio número al servidor

                respuesta = entrada.readLine(); // Leer respuesta del servidor
                System.out.println("Servidor: " + respuesta);

                if ("ACERTASTE".equals(respuesta)) {
                    acertado = true;
                }
            }

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        } finally {
            try {
                if (entrada != null) entrada.close();
                if (salida != null) salida.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Error cerrando recursos: " + e.getMessage());
            }
            scanner.close();
        }
    }
}