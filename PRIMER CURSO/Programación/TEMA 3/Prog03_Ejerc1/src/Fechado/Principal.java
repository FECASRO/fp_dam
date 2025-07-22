/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Fechado;

import Fechado.Fecha.enumMes;

/**
 *
 * @author FELIPE
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //INSTANCIACION DE CLASE 
        Fecha objFecha1 = new Fecha(enumMes.FEBRERO);
        objFecha1.setDia(20);
        objFecha1.setAnio(2000);
        //IMPRESION EN CONSOLA Y LLAMADAS A METODOS
        System.out.println("Primera Fecha inicializada con el primer constructor");
        System.out.println("La fecha es " + objFecha1.toString());
        System.out.println(objFecha1.isSummer() ? "Es verano" : "No es verano");
        // SEGUNDO OBJETO INSTANCIADO
        Fecha objFecha2 = new Fecha(20, enumMes.AGOSTO, 2015);
        //IMPRESION EN CONSOLA Y LLAMADAS A METODOS
        System.out.println("Segunda fecha, inicializada con el segundo constructor");
        System.out.println("La fecha 2 contiene el año "+ objFecha2.getAnio());
        System.out.println(objFecha2.toString());
        System.out.println(objFecha2.isSummer()? "Es verano":"No es verano");
    }

}
