/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog03.figuras;

/**
 *
 * @author FELIPE
 */
public class Rectangulo {

//DECLARACION DE VARIABLES
    float base;
    float altura;
    float area;
//CONSTRUCTOR DE CLASE

    public Rectangulo() {
        // TODAS LAS VARIABLES SE PONEN A CERO     
    }
//CONSTRUCTOR DE CLASE CON PASO DE VARIABLES 

    public Rectangulo(float base, float altura) {
        this.base = base;
        this.altura = altura;

    }
//GETTERS Y SETTERS

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
//METODO PARA CALCULAR EL AREA

    public float getArea() {
        area = base * altura;
        return area;
    }
//METODO TO STRING DE DEVOLUCION DE DATOS

    public String toString() {
        return "A continuacion el Area y la Altura: " + " Area: " + area + " Altura: " + altura;
    }
//METODO PARA CALCULAR SI LA FIGURA ES UN CUADRADO O NO

    public boolean isCuadrado() {
        if (base == altura) {
            System.out.println("El rectangulo es un cuadrado");
            return true;

        }
        System.out.println("El rectangulo no es un cuadrado");
        return false;
    }
}
