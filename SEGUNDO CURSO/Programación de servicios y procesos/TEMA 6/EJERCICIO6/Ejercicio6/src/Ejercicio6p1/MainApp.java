/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio6p1;

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author MCCLA
 */
public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar nombre de usuario
        String usuario;
        do {
            System.out.print("Introduce tu nombre de usuario (8 minúsculas): ");
            usuario = scanner.nextLine();
        } while (!Validador.validarUsuario(usuario));

        LoggerUtil.registrarLog("Usuario validado: " + usuario);

        // Solicitar nombre de archivo
        String nombreArchivo;
        do {
            System.out.print("Introduce el nombre del archivo (ejemplo.txt): ");
            nombreArchivo = scanner.nextLine();
        } while (!Validador.validarArchivo(nombreArchivo));

        LoggerUtil.registrarLog("Archivo solicitado: " + nombreArchivo);

        // Leer y mostrar contenido del archivo
        leerArchivo(nombreArchivo);
        scanner.close();
    }

    // Método para leer el contenido del archivo
    private static void leerArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            LoggerUtil.registrarLog("Error: Archivo no encontrado - " + nombreArchivo);
            return;
        }

        System.out.println("Contenido del archivo:");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            LoggerUtil.registrarLog("Archivo leído correctamente: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            LoggerUtil.registrarLog("Error de lectura: " + e.getMessage());
        }
    }
}
