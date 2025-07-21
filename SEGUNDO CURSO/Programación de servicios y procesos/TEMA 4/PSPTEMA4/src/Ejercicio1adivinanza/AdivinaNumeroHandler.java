package Ejercicio1adivinanza;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MCCLA
 */
public class AdivinaNumeroHandler implements Runnable {
    private Socket socket;
    private int numeroSecreto;

    public AdivinaNumeroHandler(Socket socket) {
        this.socket = socket;
        this.numeroSecreto = new Random().nextInt(101);
        System.out.println("Número secreto para " + socket.getInetAddress() + ": " + numeroSecreto);
    }

    @Override
    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String mensaje;
            boolean acertado = false;

            while (!acertado && (mensaje = entrada.readLine()) != null) {
                int numeroCliente;
                try {
                    numeroCliente = Integer.parseInt(mensaje);
                } catch (NumberFormatException e) {
                    salida.println("ERROR: Envia un número válido.");
                    continue;
                }

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
            System.err.println("Error con el cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error cerrando socket: " + e.getMessage());
            }
        }
    }
}