/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EJERCICIO1;

import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author MCCLA
 */
public class ServidorHTTP {

    public static void main(String[] args) {
        try {
            // Asociamos al servidor el puerto 8066
            ServerSocket socServidor = new ServerSocket(8066);
            imprimeDisponible();
            Socket socCliente;

            while (true) {
                // Espera conexiones de clientes
                socCliente = socServidor.accept();
                System.out.println("Atendiendo al cliente...");
                System.out.println("Petición recibida");

                // Procesa la solicitud del cliente
                procesaPeticion(socCliente);

                // Cierra la conexión
                socCliente.close();
                System.out.println("Cliente atendido");
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    /**
     * Procesa la petición HTTP recibida
     */
    private static void procesaPeticion(Socket socketCliente) {
        try {
            // Variables locales
            String peticion;
            String html;

            // Flujo de entrada para leer la petición
            BufferedReader bufLeer = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socketCliente.getOutputStream(), true);

            // Leer la línea de la petición
            peticion = bufLeer.readLine();
            if (peticion == null) {
                return;  // Evita errores si la petición es nula
            }
            // Limpia la petición de espacios en blanco
            peticion = peticion.replaceAll(" ", "");

            // Obtener la fecha actual en formato HTTP
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            String dateHeader = "Date: " + format.format(new Date());

            // Verifica si es una petición GET
            if (peticion.startsWith("GET")) {
                peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));

                // Si es la página principal
                if (peticion.length() == 0 || peticion.equals("/")) {
                    html = Paginas.html_index;
                    printWriter.println(Mensajes.lineaInicial_OK);
                } // Si es la página del Quijote
                else if (peticion.equals("/quijote")) {
                    html = Paginas.html_quijote;
                    printWriter.println(Mensajes.lineaInicial_OK);
                } // Si la página no existe
                else {
                    html = Paginas.html_noEncontrado;
                    printWriter.println(Mensajes.lineaInicial_NotFound);
                }

                // Enviar las cabeceras HTTP
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println(dateHeader);  // Añadimos la cabecera Date
                printWriter.println("Content-Length: " + html.length());
                printWriter.println("\n");

                // Enviar el contenido HTML
                printWriter.println(html);
            }
        } catch (IOException e) {
            System.err.println("Error al procesar la petición: " + e.getMessage());
        }
    }

    /**
     * Mensaje que indica que el servidor está en ejecución
     */
    private static void imprimeDisponible() {
        System.out.println("El Servidor WEB se está ejecutando en el puerto 8066.\n"
                + "Prueba en tu navegador:\n\n"
                + "http://localhost:8066/ para la página de bienvenida\n"
                + "http://localhost:8066/quijote para ver un fragmento del Quijote\n"
                + "http://localhost:8066/cualquiercosa para simular un error");
    }
}
