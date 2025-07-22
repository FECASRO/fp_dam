/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.prog03.figuras;

import com.sun.corba.se.impl.interceptors.SlotTable;

/**
 *
 * @author FELIPE
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//INSTANCIO UN PRIMER OBJETO
        Rectangulo objREctangulo1 = new Rectangulo();
//DOY VALORES A LAS VARIABLES
        objREctangulo1.setBase(4);
        objREctangulo1.setAltura(6);
//IMPRESION POR CONSOLA Y LLAMADAS A METODOS
        System.out.println("PRIMER OBJETO INSTANCIADO:");
        System.out.println("PRIMER CONSTRUCTOR: ");
        System.out.println("Los datos son los siguientes: ");
        System.out.println("Base: " + objREctangulo1.getBase() + " Cm");
        System.out.println("Altura: " + objREctangulo1.getAltura() + " Cm");
        System.out.println("Area del Rectangulo: " + objREctangulo1.getArea() + " Cm cuadrados");
        System.out.println(objREctangulo1.toString());
        System.out.println(objREctangulo1.isCuadrado());

//INSTANCIO SEGUNDO OBJETO
        Rectangulo objRectangulo2 = new Rectangulo(8, 8);
//IMPRESION POR CONSOLA Y LLAMADAS A METODOS
        System.out.println("SEGUNDO OBJETO INSTANCIADO:");
        System.out.println("SEGUNDO CONSTRUCTOR: ");
        System.out.println("Los datos son los siguientes: ");
        System.out.println("Base: " + objRectangulo2.getBase() + " Cm");
        System.out.println("Altura: " + objRectangulo2.getAltura() + " Cm");
        System.out.println("Area del Rectangulo: " + objRectangulo2.getArea() + " Cm cuadrados");
        System.out.println(objRectangulo2.toString());
        System.out.println(objRectangulo2.isCuadrado());

    }

}
