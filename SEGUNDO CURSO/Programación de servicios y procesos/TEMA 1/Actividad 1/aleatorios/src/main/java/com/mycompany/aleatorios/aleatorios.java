/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.aleatorios;

import java.util.Random;

/**
 * Clase principal que genera números aleatorios en un rango específico y los imprime en la consola.
 * <p>
 * Este programa genera 40 números aleatorios entre 0 y 100, usando la clase {@code Random}.
 * Los números se imprimen línea por línea en la salida estándar.
 * </p>
 * 
 * @author MCCLA
 */
public class aleatorios {

    /**
     * Método principal del programa que genera e imprime números aleatorios.
     * <p>
     * El método utiliza una instancia de {@code Random} para generar 40 números
     * aleatorios dentro del rango de 0 a 100, inclusive. Cada número se imprime
     * en una nueva línea en la consola.
     * </p>
     * 
     * @param args Los argumentos de la línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar 40 números aleatorios entre 0 y 100
        for (int i = 0; i < 40; i++) {
            int numero = random.nextInt(101); // nextInt(101) genera números entre 0 y 100
            System.out.println(numero);
        }
    }
}
