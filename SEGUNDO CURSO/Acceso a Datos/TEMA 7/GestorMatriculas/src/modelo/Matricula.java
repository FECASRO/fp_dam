/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author MCCLA
 */
public class Matricula {
    private String dni;
    private String nombreModulo;
    private String curso;
    private double nota;

    public Matricula(String dni, String nombreModulo, String curso, double nota) {
        this.dni = dni;
        this.nombreModulo = nombreModulo;
        this.curso = curso;
        this.nota = nota;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombreModulo() { return nombreModulo; }
    public void setNombreModulo(String nombreModulo) { this.nombreModulo = nombreModulo; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    @Override
    public String toString() {
        return dni + " - " + nombreModulo + " (" + curso + ") -> Nota: " + nota;
    }
}