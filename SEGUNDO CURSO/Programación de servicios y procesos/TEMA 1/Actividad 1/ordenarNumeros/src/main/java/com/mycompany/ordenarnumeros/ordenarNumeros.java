package com.mycompany.ordenarnumeros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Clase principal que lee números desde la entrada estándar, los ordena y los imprime en la salida estándar.
 * <p>
 * Este programa utiliza una lista dinámica ({@code ArrayList}) para almacenar números enteros,
 * los ordena de forma ascendente con {@code Collections.sort}, y luego imprime los números ordenados,
 * uno por línea.
 * </p>
 * 
 * @author MCCLA
 */
public class ordenarNumeros {

    /**
     * Método principal que implementa la lógica para leer, ordenar y mostrar números enteros.
     * <p>
     * El programa espera recibir una secuencia de números desde la entrada estándar. Cada número
     * se agrega a una lista dinámica, que posteriormente se ordena en orden ascendente. Finalmente,
     * los números ordenados se imprimen en la salida estándar.
     * </p>
     * 
     * @param args Los argumentos de la línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<Integer> numeros = new ArrayList<>();

        // Leer números desde la entrada estándar
        while (scn.hasNextInt()) {
            int numero = scn.nextInt();
            numeros.add(numero); // Agregar el número a la lista
        }

        // Ordenar la lista
        Collections.sort(numeros);

        // Mostrar los números ordenados
        for (int numero : numeros) {
            System.out.println(numero);
        }
    }
}
