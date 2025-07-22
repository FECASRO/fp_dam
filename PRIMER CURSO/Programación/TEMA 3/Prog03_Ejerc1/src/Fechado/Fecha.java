/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fechado;

/**
 *
 * @author FELIPE
 */
public class Fecha {
// AQUI TENEMOS LA ENUMERACION
    enum enumMes{ENERO,FEBRERO,MARZO,ABRIL,MAYO,JUNIO,JULIO,AGOSTO,SEPTIEMBRE,
OCTUBRE,NOVIEMBRE,DICIEMBRE}
//DECLARACION DE VARIABLES
int dia;
enumMes mes;
int anio;
//CONSTRUCTOR DE CLASE 
Fecha (enumMes mes){ 
this.mes=mes;
//aqui dia y año se inicializan solos a cero
}
//SEGUNDO CONSTRUCTOR  DE CLASE
Fecha (int dia, enumMes mes, int anio){
this.dia=dia;
this.mes=mes;
this.anio=anio;
}

// AQUI ESTAN TODOS LOS GETTERS Y LOS SETTERS
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public enumMes getMes() {
        return mes;
    }

    public void setMes(enumMes mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

// METODO IS SUMMER PARA DETERMINAR MESES DE VERANO
public boolean isSummer(){ 
 return (this.mes==enumMes.JUNIO)|| this.mes==enumMes.JULIO||this.mes==enumMes.AGOSTO;
}
//METODO TO STRING DEVOLUCION DE DATOS
public String toString (){
return ""+dia+" de "+mes+" del año "+anio;
}
}
