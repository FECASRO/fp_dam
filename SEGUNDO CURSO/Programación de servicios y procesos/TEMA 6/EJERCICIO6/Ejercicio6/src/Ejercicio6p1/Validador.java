/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio6p1;

/**
 *
 * @author MCCLA
 */
public class Validador {
    // Valida el nombre de usuario (8 caracteres en minúsculas)
    public static boolean validarUsuario(String usuario) {
        return usuario.matches("^[a-z]{8}$");
    }

    // Valida el formato del nombre del archivo (8 caracteres + 3 de extensión)
    public static boolean validarArchivo(String archivo) {
        return archivo.matches("^[a-zA-Z0-9]{1,8}\\.[a-zA-Z0-9]{3}$");
    }
}