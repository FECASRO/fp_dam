/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cenafilosofos;

/**
 *
 * @author MCCLA
 */
public class Filosofo implements Runnable {
    private final int id;
    private final Palillo palilloIzquierdo;
    private final Palillo palilloDerecho;

    public Filosofo(int id, Palillo palilloIzquierdo, Palillo palilloDerecho) {
        this.id = id;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        Thread.sleep((int) (Math.random() * 1000)); // tiempo de pensar
    }

    private void comer() throws InterruptedException {
        // Intentar coger los palillos
        palilloIzquierdo.coger();
        palilloDerecho.coger();

        System.out.println("Filósofo " + id + " está comiendo...");
        Thread.sleep((int) (Math.random() * 1000)); // tiempo de comer

        // Soltar los palillos
        palilloIzquierdo.soltar();
        palilloDerecho.soltar();

        System.out.println("Filósofo " + id + " terminó de comer.");
    }
}
