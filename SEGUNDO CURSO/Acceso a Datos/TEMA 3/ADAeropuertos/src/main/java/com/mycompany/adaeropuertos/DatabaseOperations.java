/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adaeropuertos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author MCCLA
 */
public class DatabaseOperations {

//metodo para obtener todos los datos 
    public static void getAllFlights() {

        String query = "SELECT *FROM VUELOS";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Código de Vuelo: " + rs.getString("COD_VUELO"));
                System.out.println("Hora de Salida: " + rs.getString("HORA_SALIDA"));
                System.out.println("Destino: " + rs.getString("DESTINO"));
                System.out.println("Procedencia: " + rs.getString("PROCEDENCIA"));
                System.out.println("Plazas Fumador: " + rs.getInt("PLAZAS_FUMADOR"));
                System.out.println("Plazas No Fumador: " + rs.getInt("PLAZAS_NO_FUMADOR"));
                System.out.println("--------------------------");
            }

        } catch (Exception e) {
            System.err.println("Error al obtener los vuelos: " + e.getMessage());
        }

    }
//metodo para obtener datos de los pasajeros

    public static void getAllPassengers() {
        String query = "SELECT *FROM PASAJEROS";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("--------------------------");
                System.out.println("Código de Vuelo: " + rs.getString("COD_VUELO"));
                System.out.println("Tipo de plaza: " + rs.getString("TIPO_PLAZA"));
                System.out.println("Fumador / No Fumador: " + rs.getString("FUMADOR"));
                System.out.println("--------------------------");
            }
        } catch (Exception e) {
        }

    }
//metodo para obtener datos por el codigo del vuelo

    public static void porCodigo(String codigoVuelo) {

        String query = "SELECT * FROM PASAJEROS WHERE COD_VUELO = ?";

        try (Connection conn = DBConnection.getConnection();
                java.sql.PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, codigoVuelo);

            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hayResultados = false;

                while (rs.next()) {
                    hayResultados = true;
                    System.out.println("--------------------------");
                    System.out.println("Código de Vuelo: " + rs.getString("COD_VUELO"));
                    System.out.println("Tipo de Plaza: " + rs.getString("TIPO_PLAZA"));
                    System.out.println("Fumador / No Fumador: " + rs.getString("FUMADOR"));
                    System.out.println("--------------------------");
                }

                if (!hayResultados) {
                    System.out.println("No se encontraron pasajeros para el código de vuelo: " + codigoVuelo);
                }
            }

        } catch (Exception e) {
            System.err.println("Error al obtener pasajeros para el código de vuelo " + codigoVuelo + ": " + e.getMessage());
        }

    }

//Insertar vuelo pasando datos

public static void insertarVuelo(String codigoVuelo, String hSalida,String destino, String procedencia, String fumador) {

 String query = "INSERT INTO VUELOS (COD_VUELO, HORA_SALIDA, DESTINO, PROCEDENCIA, PLAZAS_FUMADOR) VALUES (?, ?, ?, ?, ?)";

   try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        // Corrigiendo error hora
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
        try {
            sdf.parse(hSalida); 
        } catch (ParseException e) {
            System.err.println("Formato de hora de salida inválido. Use 'DD/MM/YY HH:MM'.");
            return; // Salir si es incorrecto
        }

        pstmt.setString(1, codigoVuelo);
        pstmt.setString(2, hSalida); 
        pstmt.setString(3, destino);
        pstmt.setString(4, procedencia);

        // Eliminando error , pasando de letras a numeros
        int plazasFumador = fumador.equalsIgnoreCase("Si") ? 1 : 0;
        pstmt.setInt(5, plazasFumador);

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Vuelo insertado correctamente.");
        }
    } catch (Exception e) {
        System.err.println("Error al insertar vuelo: " + e.getMessage());
    }
}
public static void borrarVuelo(String codigoVuelo) {
    // Consulta SQL para eliminar un vuelo por codigo de vuelo
    String query = "DELETE FROM VUELOS WHERE COD_VUELO = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, codigoVuelo);

        // consulta
        int rowsAffected = pstmt.executeUpdate();

        // resultado
        if (rowsAffected > 0) {
            System.out.println("Vuelo eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún vuelo con el código proporcionado.");
        }

    } catch (Exception e) {
        System.err.println("Error al eliminar vuelo: " + e.getMessage());
    }
}
public static void aDESFumar() {
    //modificar los vuelos de fumadores a no fumadores
    String query = "UPDATE VUELOS SET PLAZAS_FUMADOR = 0, PLAZAS_NO_FUMADOR = PLAZAS_NO_FUMADOR + PLAZAS_FUMADOR WHERE PLAZAS_FUMADOR > 0";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        // consulta
        int rowsAffected = pstmt.executeUpdate();

        // resultado
        if (rowsAffected > 0) {
            System.out.println("Todos los vuelos de fumadores se han actualizado a no fumadores.");
        } else {
            System.out.println("No se encontraron vuelos de fumadores para actualizar.");
        }

    } catch (Exception e) {
        System.err.println("Error al actualizar vuelos: " + e.getMessage());
    }
}

}
