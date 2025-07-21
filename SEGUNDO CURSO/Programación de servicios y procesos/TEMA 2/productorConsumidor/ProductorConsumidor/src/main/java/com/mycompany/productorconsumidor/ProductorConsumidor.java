/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.productorconsumidor;

/**
 *
 * @author MCCLA
 */
public class ProductorConsumidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Buffer buffer = new Buffer(6); // Búfer con capacidad 6

        Thread productor = new Thread(new Productor(buffer), "Productor");
        Thread consumidor = new Thread(new Consumidor(buffer), "Consumidor");

        productor.start();
        consumidor.start();
    }
}
