/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Multiplos;

import java.util.Scanner;

/** 
 * En esta clase se piden 2 numeros y se calcula el Minimo Comun Multiplo de ambos 
 * @author Felipe Castillo Rodriguez
 * @version 1.0 
 */
public class Multiplos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//se piden los numeros		
        Scanner leer = new Scanner(System.in);
        System.out.println("Dame dos numeros enteros para hallar su MCM: ");
        int num1 = leer.nextInt();
        System.out.println("Dame el segundo: ");
        int num2 = leer.nextInt();

        //declaro e inicializo variables
        int mcm, i;

        mcm = 1;
        i = 2;

//	bucle donde se calcula la operacion 
        while (i <= num1 || i <= num2) {

            if (num1 % i == 0 || num2 % i == 0) {

                mcm = mcm * i;

                if (num1 % i == 0) {
                    num1 = num1 / i;
                }
                if (num2 % i == 0) {
                    num2 = num2 / i;
                }

            } else {
                i = i + 1;
            }
        }

        System.out.println("El M.C.M. de estos dos numeros es: " + mcm);
    }

}
