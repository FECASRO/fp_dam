/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Adivina;

import java.util.Scanner;

/** 
 * Esta clase se ha creado un menu con tres opciones en la primera hay una 
 * configuracion del juego, en la segunda el juego en si, y en la tercera
 * se sale del menu y se cierra la aplicacion
 * @author Felipe Castillo Rodriguez
 * @version 1.0
 */
public class Adivinanza {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

/**
*Aqui declaracion de variables he cambiado numInt de 5 a 0 porque creo que por mi
*logica debe ser asi, y numMax lo dejo a eleccion del jugador o como 5 por defecto
*si no se configura.
*/
        int opc, edad, numOculto, elec;
        int numInt = 0;
        int numMax = 5;
        int i = 0;
        Scanner scn = new Scanner(System.in);
/**
*
*/
        boolean salir = false;
        while (!salir) {
            System.out.println("SELECCIONA UNA OPCION POR FAVOR: ");
            System.out.println("1.- CONFIGURAR ");
            System.out.println("2.- JUGAR ");
            System.out.println("3.- SALIR ");

            opc = scn.nextInt();

            switch (opc) {
                case 1:
                    System.out.println("Dame un numero de intentos permitidos");
                    numMax = scn.nextInt();
               //     System.out.println("Dame un numero de intentos maximos");
               //     numInt = scn.nextInt();
/**
*Intentos permitidos e intentos maximos es lo mismo no? por eso anule esas lineas
*con definir cuantas veces se juega no es necesario mas ....
*/
                    break;

                case 2:
/**
*Aqui la formula que genera un numero aleatorio entre 0 y 20
*/
                    numOculto = (int) Math.floor(Math.random() * 20 + 1);
                    System.out.println("Intenta adivinar el numero entre 0 y 20");
/**
*Aqui comienza el bucle que pide numeros hasta que acertamos o agotamos intentos
*/
                    while (i < numMax) {

                        numInt++;
                        i++;
                        if (numInt == numMax) {
                            System.out.println("PERDISTE HAS ALCANZADO EL NUMERO MAXIMO DE INTENTOS...");
                            salir = true;
                            System.runFinalization();
                            break;

                        }

                        System.out.println("DAME NUMERO: ");
                        elec = scn.nextInt();
                        if (elec < numOculto) {
                            System.out.println("TE HAS QUEDADO CORTO...");

                        } else if (elec > numOculto) {
                            System.out.println("TE HAS PASADO ....");

                        } else if (elec == numOculto) {
                            System.out.println("HAS ACERTADO...VUELVE A JUGAR CUANDO QUIERAS");
                            System.out.println("Has necesitado " + i + " intentos para lograrlo");
                            break;
                        }

                    }

                    salir = true;
                    System.runFinalization();
                    break;

                case 3:
                    System.out.println("Esto es todo amigos , Gracias por jugar");
                    salir = true;
                    System.runFinalization();
                    break;

            }

        }

    }

}
