/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cenafilosofos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author MCCLA
 */
public class Palillo {
   private final Semaphore semaphore = new Semaphore(1); // Un palillo puede ser usado por un filósofo a la vez

    public void coger() throws InterruptedException {
        semaphore.acquire();
    }

    public void soltar() {
        semaphore.release();
    }
}  

