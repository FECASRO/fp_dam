/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJERCICIO1;

/**
 *
 * @author MCCLA
 */
public class Paginas {

     public static final String primeraCabecera = "Content-Type: text/html;charset=UTF-8";

    public static final String html_index = "<html>"
            + "<head><title>Index</title></head>"
            + "<body><h1>¡Enhorabuena!</h1>"
            + "<p>Tu servidor HTTP mínimo funciona correctamente</p>"
            + "</body></html>";

    public static final String html_quijote = "<html>"
            + "<head><title>Quijote</title></head>"
            + "<body><h1>Así comienza el Quijote</h1>"
            + "<p>En un lugar de la Mancha, de cuyo nombre no quiero acordarme...</p>"
            + "</body></html>";

    public static final String html_noEncontrado = "<html>"
            + "<head><title>No Encontrado</title></head>"
            + "<body><h1>¡ERROR! Página no encontrada</h1>"
            + "<p>La página que solicitaste no existe en nuestro servidor.</p>"
            + "</body></html>";
}