/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LUXURECONCESIONARIO;

/**
 *
 * @author FELIPE
 * @version 2.0 
 * Esta es la clase Vehiculo aqui llegan los datos recogidos y se almacenan en las variables
 * y se mandan a la clase concesionario a traves de toString
 */
public class Vehiculo {
// variables encapsuladas
    private String marca;
    private String matricula;
    private int numKM;
    private String descripcion;
    private double precio;
    private String nombrePropietario;
    private String dniPropietario;
//constructor vacio
    public Vehiculo() {
    }
//constructor recibe paso de parametros
    public Vehiculo(String marca, String matricula, int numKM, String descripcion, double precio, String nombrePropietario, String dniPropietario) {
        this.marca = marca;
        this.matricula = matricula;
        this.numKM = numKM;
        this.descripcion = descripcion;
        this.precio = precio;
        this.nombrePropietario = nombrePropietario;
        this.dniPropietario = dniPropietario;
    }
// Getters y Setters para recogida y muestra  de datos de variables encapsuladas
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNumKM() {
        return numKM;
    }

    public void setNumKM(int numKM) {
        this.numKM = numKM;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getDniPropietario() {
        return dniPropietario;
    }

    public void setDniPropietario(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }
    
 // Metodo toString devuelve los datos solicitados, esta sobreescrito por la clase concesionario   
    @Override
    public String toString() {
        return "...Datos del Vehiculo: " + "Marca=" + marca + ", Matricula=" + matricula + ", Kilometros=" + numKM + ", Descripcion=" + descripcion + ", Precio=" + precio;
    }
    
}

