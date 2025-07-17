package com.example.myapplication;
import java.util.ArrayList;

public class PrimeCalculator {
    // Lista para almacenar los números primos ya calculados
    private ArrayList<Integer> primes;

    public PrimeCalculator() {
        primes = new ArrayList<>();
        primes.add(2); // Inicializamos con el primer número primo
    }

    // Método optimizado para calcular el enésimo número primo
    public int calculateNthPrime(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n debe ser mayor que 0");
        }

        // Si ya tenemos el número primo calculado, lo devolvemos directamente
        if (n <= primes.size()) {
            return primes.get(n - 1);
        }

        // Continuamos calculando nuevos números primos
        int candidate = primes.get(primes.size() - 1) + 1;
        while (primes.size() < n) {
            if (isPrime(candidate)) {
                primes.add(candidate);
            }
            candidate++;
        }

        return primes.get(n - 1);
    }

    // Método para verificar si un número es primo
    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int prime : primes) {
            if (prime * prime > number) break; // Optimizamos revisando solo hasta √n
            if (number % prime == 0) return false;
        }
        return true;
    }
}
