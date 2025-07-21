/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.servidorcliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author MCCLA
 */
public class ServidorAdivinaNumero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PUERTO = 2000;
        ServerSocket servidor = null;
        Socket cliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;

        try {
            // Creo el servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            
            // Genero un número secreto
            Random random = new Random();
            int numeroSecreto = random.nextInt(101); // Número entre 0 y 100
            System.out.println("Número secreto generado: " + numeroSecreto);

            // Acepto la conexión del cliente
            cliente = servidor.accept();
            System.out.println("Cliente conectado");

            // Streams de entrada y salida
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salida = new PrintWriter(cliente.getOutputStream(), true);

            String mensaje;
            boolean acertado = false;

            // Bucle para los mensajes del cliente
            while (!acertado && (mensaje = entrada.readLine()) != null) {
                int numeroCliente;
                try {
                    numeroCliente = Integer.parseInt(mensaje);
                } catch (NumberFormatException e) {
                    salida.println("ERROR: Por favor, envía un número válido.");
                    continue;
                }

                // Comparo el número recibido con el número secreto
                if (numeroCliente < numeroSecreto) {
                    salida.println("MENOR");
                } else if (numeroCliente > numeroSecreto) {
                    salida.println("MAYOR");
                } else {
                    salida.println("ACERTASTE");
                    acertado = true;
                }
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            try {
                if (entrada != null) entrada.close();
                if (salida != null) salida.close();
                if (cliente != null) cliente.close();
                if (servidor != null) servidor.close();
            } catch (IOException e) {
                System.err.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }
}
