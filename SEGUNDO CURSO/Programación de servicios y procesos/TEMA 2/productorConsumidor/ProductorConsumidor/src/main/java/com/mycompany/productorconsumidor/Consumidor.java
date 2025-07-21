/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productorconsumidor;

/**
 *
 * @author MCCLA
 */
public class Consumidor implements Runnable {

    private final Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 15; i++) {
                char dato = buffer.consumir();
                System.out.println(Thread.currentThread().getName() + " consumió: " + dato);
                Thread.sleep(700); // dormir hilo como si fuera tiempo de consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
