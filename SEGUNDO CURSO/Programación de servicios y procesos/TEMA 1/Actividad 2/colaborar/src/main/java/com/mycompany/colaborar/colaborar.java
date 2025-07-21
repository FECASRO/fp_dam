/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.colaborar;

import java.io.File;
import java.io.IOException;

/**
 * Clase principal que coordina la ejecución de varias instancias del programa "lenguaje".
 * Este programa lanza múltiples procesos del programa "lenguaje" para generar palabras
 * aleatorias y almacenarlas en un único archivo combinado. Cada instancia genera un número
 * creciente de palabras, comenzando con 10 y aumentando en pasos de 10.
 *
 * @author MCCLA
 */
public class colaborar {

    /**
     * Punto de entrada principal del programa.
     * Este método lanza una serie de procesos de la clase `lenguaje`, cada uno de los cuales
     * genera palabras aleatorias y las escribe en un archivo común. Los procesos se ejecutan
     * secuencialmente, y el programa informa del estado de cada instancia.
     */
    public static void main(String[] args) {
        // Nombre del archivo donde se guardarán todas las palabras
        String archivoFinal = "C:\\\\Users\\\\MCCLA\\\\Documents\\\\granFicheroDeLenguaje.txt";

        // Número de instancias de lenguaje a lanzar
        int numInstancias = 10;

        try {
            for (int i = 1; i <= numInstancias; i++) {
                // Número de palabras a generar para esta instancia
                int palabrasAGenerar = i * 10;

                // Comando para lanzar el programa lenguaje
                ProcessBuilder pb = new ProcessBuilder(
                        "java",
                        "-cp", "target/classes", // Ruta de las clases compiladas
                        "com.mycompany.lenguaje.lenguaje",
                        String.valueOf(palabrasAGenerar),
                        archivoFinal
                );

                // Establecer el directorio de trabajo donde se encuentra target/classes
                pb.directory(new File("C:\\Users\\MCCLA\\Documents\\NetBeansProjects\\lenguaje"));

                // Añadir salida de errores en caso de fallos
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);

                // Ejecutar el proceso
                Process proceso = pb.start();

                // Esperar a que el proceso termine
                int estado = proceso.waitFor();
                if (estado == 0) {
                    System.out.println("Instancia " + i + " completada con éxito.");
                } else {
                    System.err.println("Instancia " + i + " falló con código: " + estado);
                }
            }

            System.out.println("¡Todas las instancias se ejecutaron correctamente!");
            System.out.println("Archivo combinado generado: " + archivoFinal);

        } catch (IOException | InterruptedException e) {
            System.err.println("Ocurrió un error al ejecutar las instancias: " + e.getMessage());
        }
    }
}
