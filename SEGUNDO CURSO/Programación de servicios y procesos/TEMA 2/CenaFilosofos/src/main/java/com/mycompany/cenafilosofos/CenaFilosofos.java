/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.cenafilosofos;

/**
 *
 * @author MCCLA
 */
public class CenaFilosofos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

   final int NUM_FILOSOFOS = 5;

        // Creo un array de palillos
        Palillo[] palillos = new Palillo[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            palillos[i] = new Palillo();
        }

        // Creo y arranco a los filósofos
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Palillo palilloIzquierdo = palillos[i];
            Palillo palilloDerecho = palillos[(i + 1) % NUM_FILOSOFOS];

            // Doy palillos alternando el orden para evitar interbloqueo
            if (i % 2 == 0) {
                filosofos[i] = new Filosofo(i, palilloIzquierdo, palilloDerecho);
            } else {
                filosofos[i] = new Filosofo(i, palilloDerecho, palilloIzquierdo);
            }

            new Thread(filosofos[i], "Filósofo " + i).start();
        }
    }
}
    
    

