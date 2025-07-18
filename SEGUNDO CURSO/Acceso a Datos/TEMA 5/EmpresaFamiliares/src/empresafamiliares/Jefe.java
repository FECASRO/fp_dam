/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresafamiliares;

/**
 *
 * @author MCCLA
 */
public class Jefe {
private String nombre;
    private int aniosEmpresa;
    private int edad;
    private Hijo hijo;

    public Jefe(String nombre, int aniosEmpresa, int edad, Hijo hijo) {
        this.nombre = nombre;
        this.aniosEmpresa = aniosEmpresa;
        this.edad = edad;
        this.hijo = hijo;
    }

    public String getNombre() { return nombre; }
    public int getAniosEmpresa() { return aniosEmpresa; }
    public int getEdad() { return edad; }
    public Hijo getHijo() { return hijo; }

    public void setEdad(int edad) { this.edad = edad; }
}