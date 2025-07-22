/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LUXURECONCESIONARIO;

/**
 *
 * @author FELIPE
 * @version 2.0 
 * Esta es la clase Validacion y sera llamada para comprobar que los datos introducidos son correctos
 * en relacion al formato que deseamos
 */
public class Validacion {

/**
 * Este metodo valida el Dni solicitado
     * @param DNI recibe por parametro un DNI
     * @return devuelve true si la expresion introducida se corresponde con el patron establecido */
    public static boolean validarDNI(String DNI){
        return DNI.matches("^[0-9]{8}[T|R|W|A|G|M|Y|F|P|D|X|B|N|J|Z|S|Q|V|H|L|C|K|E]$");
    }
 /**
 * Este metodo valida la matricula solicitada
     * @param matricula recibe por parametro una matricula  
     * @return devuelve true si la expresion introducida se corresponde con el patron establecido */   
    public static boolean validarMatricula(String matricula){
        return matricula.matches("^[0-9]{4}[A-Z]{3}$");
    }
/**
     * @param nombre recibe por parametro un nombre     
     * @return si el nombre es mayor de 40 caracteres o menos de 3 palabras devuelve false
         en caso contrario devuelve true*/    
    public static boolean validarNombre(String nombre){
        
        if(nombre.length() > 40){
            return false;
        }
        
        char caracter;
        int numEspacios = 0;
        for (int i = 0; i < nombre.length(); i++) {
            caracter = nombre.charAt(i);
            if(caracter == ' '){
                numEspacios++;
            }
            if(Character.isDigit(caracter)){
                return false;
            }
        }
        
        if(numEspacios >= 2){
            return true;
        }
        return false;
    }
    
}