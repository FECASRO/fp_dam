/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pro04_ejerc1;

import java.util.Scanner;

/**
 * Este ejercicio consiste en pedir un numero si es mayor 30 acaba el programa,
 * si es menor que 30 se imprime la tabla de multiplicar de ese numero, con tres
 * bucles diferentes
 *
 * @author FELIPE
 * @version 1.0
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * Aqui declaro variables y pido datos
         */
        Scanner scn = new Scanner(System.in);
        int a;
        System.out.println("INTRODUCE UN NUMERO MENOR QUE 30 POR FAVOR: ");
        a = scn.nextInt();
        /**
         * Primer bucle
         */

        if (a > 30) {
            System.out.println("EL NUMERO NO ES LO PEDIDO");
            System.runFinalization();
        } else {
            System.out.println("ESTA ES LA TABLA DEL NUMERO " + a);
            System.out.println(a + " x1= " + a * 1 + "\n" + a + " x2= " + a * 2 + "\n" + a + " x3= " + a * 3 + "\n"
                    + +a + " x4= " + a * 4 + "\n" + a + " x5= " + a * 5 + "\n" + a + " x6= " + a * 6 + "\n" + a + " x7= " + a * 7 + "\n" + a + " x8= "
                    + "" + a * 8 + "\n" + a + " x9= " + a * 9 + "\n" + a + " x10= " + a * 10);

            /**
             * Metodo for con iteracion para conseguir la tabla a multiplicar
             * por la variable a
             */
            System.out.println("Este es el segundo bucle y el mas sencillo: ");
            for (int i = 1; i <= 10; i++) {
                System.out.println(a + " x" + i + "= " + a * i);

            }
            /**
             * ultimo bucle
             */

            System.out.println("Este es el tercer bucle y definitivo: ");
            int mult = 1;
            while (mult <= 10) {
                System.out.println(a + " x" + mult + "= " + a * mult);
                mult++;
            }
        }

    }

}
