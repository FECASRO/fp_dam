/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productorconsumidor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author MCCLA
 */
public class Buffer {

    private final char[] buffer;
    private int size;
    private int in, out;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Buffer(int capacidad) {
        buffer = new char[capacidad];
        size = in = out = 0;
    }

    public void producir(char dato) throws InterruptedException {
        lock.lock();
        try {
            while (size == buffer.length) {
                notFull.await(); // Esperar si el buffer esta lleno
            }
            buffer[in] = dato;
            in = (in + 1) % buffer.length;
            size++;
            notEmpty.signal(); // Avisar que hay datos pendientes
        } finally {
            lock.unlock();
        }
    }

    public char consumir() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0) {
                notEmpty.await(); // Esperar si el buffer esta vacio
            }
            char dato = buffer[out];
            out = (out + 1) % buffer.length;
            size--;
            notFull.signal(); // Avisar que hay espacio
            return dato;
        } finally {
            lock.unlock();
        }
    }
}
