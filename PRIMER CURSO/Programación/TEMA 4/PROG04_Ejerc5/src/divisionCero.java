
import java.util.Scanner;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 * En este ejercicio se buscan un dividendo y un divisor iguales a -1 hasta
 * entonces se piden numeros contando el numero de dicvisiones que realiza hasta
 * entonces el programa, ademas de controlar la excepcion que supondria un
 * divisor igual a cero
 *
 * @author Felipe Castillo Rodriguez
 * @version 1.0 
 */
public class divisionCero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //dentro del metodo principal declaramos las variables, llamamos a escaner para 
        //meter numeros por teclado
        int dividendo = 0;
        int divisor = 0;
        Scanner scn = new Scanner(System.in);
        int i = 1;
        int res;
        /**
         * Aqui esta el bucle de divisiones hasta conseguir el resultado deseado
         */
        while (dividendo != (-1) && divisor != (-1)) {
            /**
             * @throws el programa controlara una excepcion de tipo aritmetico
             * si se usa cero como divisor mediante el try-catch
             */
            try {

                System.out.println("AHORA DAME NUMEROS QUE DIVIDIR....");
                System.out.println("DAME UN DIVIDENDO :");
                dividendo = scn.nextInt();
                System.out.println("DAME UN DIVISOR :");
                divisor = scn.nextInt();
                res = dividendo / divisor;
                System.out.println("Resultado: " + res);
                i++;
            } catch (ArithmeticException e) {
                System.out.println("El cero como Divisor ocasiono un error");
            }
        }

        System.out.println("Has necesitado " + i + " Intentos");
        System.runFinalization();
    }

}
