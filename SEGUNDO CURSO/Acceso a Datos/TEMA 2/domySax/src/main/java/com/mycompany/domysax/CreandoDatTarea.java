/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.domysax;

import java.io.RandomAccessFile;

/**
 *
 * @author MCCLA
 * Descripcion:  Crear un fichero EMPLEADOS.DAT de acceso aleatorio, que 
 *contenga al menos cinco empleados. Dicho fichero contendrá los 
 *campos siguientes: CODIGO (int), NOMBRE (string), DIRECCION (string)
 *SALARIO (float) y COMISION (float).
 */
public class CreandoDatTarea {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     try (RandomAccessFile raf = new RandomAccessFile("EMPLEADOS.DAT", "rw")) {
            // Escribo 5 empleados
            String[] nombres = {"Ana", "Luis", "Carmen", "Miguel", "Sara"};
            String[] direcciones = {"Calle 1", "Calle 2", "Calle 3", "Calle 4", "Calle 5"};
            float[] salarios = {2500.5f, 3200.75f, 1800.0f, 2900.4f, 3100.9f};
            float[] comisiones = {200.0f, 300.5f, 150.0f, 0.0f, 180.5f};
            
            for (int i = 0; i < nombres.length; i++) {
                raf.writeInt(i + 1); // Código
                raf.writeUTF(String.format("%-20s", nombres[i])); // Nombres de 20 letras
                raf.writeUTF(String.format("%-30s", direcciones[i])); // Dirección de 30 letras
                raf.writeFloat(salarios[i]); // Salario
                raf.writeFloat(comisiones[i]); // Comisiónes
            }
            System.out.println("Archivo EMPLEADOS.DAT creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}