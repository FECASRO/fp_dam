/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.adaeropuertos;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author MCCLA
 */
public class DBAirport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean continuar = true;
        Scanner scn = new Scanner(System.in);

        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("CONEXIÓN A LA BASE DE DATOS CORRECTA");

            while (continuar) {
                try {
                    // Mostrar el menú
                    mostrarMenu();
                    int opc = scn.nextInt();
                    scn.nextLine();

                    switch (opc) { //Menu para simplificar  la toma de decisiones
                        case 1:
                            System.out.println("Datos genericos de los vuelos");
                            System.out.println("----------------------------------");
                            DatabaseOperations.getAllFlights();
                            break;
                        case 2:
                            System.out.println("Datos de los pasajeros");
                            System.out.println("----------------------------------");
                            DatabaseOperations.getAllPassengers();
                            break;
                        case 3:

                            System.out.println("----------------------------------");
                            System.out.println("Introduce el código del vuelo:");
                            String codigoVuelo = scn.next();
                            DatabaseOperations.porCodigo(codigoVuelo);
                            break;
                        case 4:

                            System.out.println("Codigo del Vuelo: ");
                            codigoVuelo = scn.nextLine(); 

                            System.out.println("Hora de Salida (DD/MM/YY HH:MM): ");
                            String hSalida = scn.nextLine(); 

                            System.out.println("Destino: ");
                            String destino = scn.nextLine(); 

                            System.out.println("Procedencia: ");
                            String procedencia = scn.nextLine(); 

                            System.out.println("Fumador (Si/No): ");
                            String fumador = scn.nextLine();

                            try {
                                
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
                                sdf.setLenient(false); 
                                sdf.parse(hSalida);

                                // Llamar al método insertarVuelo con los datos obtenidos
                                DatabaseOperations.insertarVuelo(codigoVuelo, hSalida, destino, procedencia, fumador);

                            } catch (ParseException e) {
                                System.err.println("Formato de hora de salida inválido. Use 'DD/MM/YY HH:MM'.");
                            } catch (Exception e) {
                                System.err.println("Error al insertar vuelo: " + e.getMessage());
                            }
                            break;
                        case 5:
                            System.out.println("Borrar Vuelo por código de vuelo");
                            System.out.println("----------------------------------");
                            System.out.println("código del vuelo a borrar: ");
                            codigoVuelo = scn.next();
                            DatabaseOperations.borrarVuelo(codigoVuelo);

                            break;
                        case 6:
                            System.out.println("Borrar Vuelo por código de vuelo");
                            System.out.println("----------------------------------");
                            DatabaseOperations.aDESFumar();

                            break;
                        case 7:
                            System.out.println("Saliendo del programa...");
                            continuar = false;
                            break;
                        default:
                            System.out.println("Por favor, seleccione una opción válida (1-7).");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, introduzca un número.");
                    scn.nextLine(); // Limpiar el scanner
                } catch (Exception e) {
                    System.out.println("Error inesperado: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Fallo al conectar con la base de datos: " + e.getMessage());
        } finally {
            scn.close();
            System.out.println("Programa finalizado.");
        }
    }

    private static void mostrarMenu() { //menu modificado para poder controlar errores que me puedan surgir
        System.out.println("\n-------MENU AEROPUERTO-------");
        System.out.println("1. Mostrar y pedir información de la base de datos.");
        System.out.println("2. Mostrar la información de la tabla pasajeros.");
        System.out.println("3. Ver información de pasajeros de un vuelo (por código).");
        System.out.println("4. Insertar un vuelo.");
        System.out.println("5. Borrar un vuelo por su número.");
        System.out.println("6. Modificar vuelos de fumadores a no fumadores.");
        System.out.println("7. Salir.");
        System.out.println("-----------------------------");
        System.out.print("Seleccione una opción: ");
    }
}
