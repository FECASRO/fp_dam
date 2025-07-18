/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import modelo.*;


/**
 *
 * @author MCCLA
 */
public class Main {
    public static void main(String[] args) {
        GestorMatricula gestor = new GestorMatricula();

        // Listar todas las matrículas
        System.out.println("Lista de todas las matrículas:");
        for (Matricula m : gestor.listarMatriculas()) {
            System.out.println(m);
        }

        // Listar por DNI
        System.out.println("\nMatrículas del alumno con DNI '12345678A':");
        for (Matricula m : gestor.listarMatriculasPorDNI("12345678A")) {
            System.out.println(m);
        }

        // Agregar una nueva matrícula
        Matricula nuevaMatricula = new Matricula("96385274F", "Redes", "23-24", 8.0);
        gestor.agregarMatricula(nuevaMatricula);

        // Listar después de agregar
        System.out.println("\nLista actualizada de matrículas:");
        for (Matricula m : gestor.listarMatriculas()) {
            System.out.println(m);
        }
    }
}
