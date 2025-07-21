/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.lenguaje;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase principal que genera un archivo con un conjunto de palabras aleatorias.
 * La aplicación genera un número específico de palabras aleatorias, cuyas longitudes
 * varían entre 5 y 10 caracteres, y las escribe en un archivo indicado por el usuario.
 *
 * @author MCCLA
 */
public class lenguaje {

    /**
     * Punto de entrada principal de la aplicación.
     * <p>
     * Este método recibe dos argumentos:
     * 1. El número de palabras a generar.
     * 2. El nombre del archivo donde se almacenarán las palabras.
     * </p>
     * <p>
     * Uso: <code>java lenguaje &lt;numero_de_palabras&gt; &lt;nombre_fichero&gt;</code>
     * </p>
     *
     * @param args los argumentos de la línea de comandos. Deben contener exactamente dos elementos:
     *             el número de palabras a generar (entero) y el nombre del archivo de salida.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java lenguaje <numero_de_palabras> <nombre_fichero>");
            return;
        }

        try {
            int numeroDePalabras = Integer.parseInt(args[0]);
            String nombreFichero = args[1];

            try (FileWriter writer = new FileWriter(nombreFichero)) {
                Random random = new Random();
                for (int i = 0; i < numeroDePalabras; i++) {
                    String palabra = generarPalabraAleatoria(random, 5, 10); // Longitud entre 5 y 10 caracteres
                    writer.write(palabra + System.lineSeparator());
                }
            }

            System.out.println("Se generaron " + numeroDePalabras + " palabras en el archivo: " + nombreFichero);
        } catch (NumberFormatException e) {
            System.out.println("El primer argumento debe ser un número entero.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Genera una palabra aleatoria con longitud dentro de un rango dado.
     *
     * @param random el generador de números aleatorios.
     * @param minLen la longitud mínima de la palabra.
     * @param maxLen la longitud máxima de la palabra.
     * @return una palabra aleatoria compuesta por letras en minúscula (de 'a' a 'z').
     */
    private static String generarPalabraAleatoria(Random random, int minLen, int maxLen) {
        int longitud = random.nextInt(maxLen - minLen + 1) + minLen;
        StringBuilder palabra = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            char letra = (char) (random.nextInt(26) + 'a'); // Letras de 'a' a 'z'
            palabra.append(letra);
        }
        return palabra.toString();
    }
}