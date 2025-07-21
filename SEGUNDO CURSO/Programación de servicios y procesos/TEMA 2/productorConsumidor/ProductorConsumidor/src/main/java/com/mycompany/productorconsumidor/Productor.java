/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productorconsumidor;

/**
 *
 * @author MCCLA
 */
public class Productor implements Runnable {

    private final Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 15; i++) {
                char dato = (char) ('A' + i); // Generar una letra
                buffer.producir(dato);
                System.out.println(Thread.currentThread().getName() + " produjo: " + dato);
                Thread.sleep(500); // dormir hilo como si fuera empleado en producir
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
