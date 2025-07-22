/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LUXURECONCESIONARIO;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author FELIPE
 * @version 2.0 
 * Esta es la clase concesionario aqui se almacenan los vehiculos y se ejecutan los metodos 
 * llamados desde la clase principal
 */
public class Concesionario {

//creacion del array de almacenamiento , inicializacion a 0 y defenicion de capacidad maxima de 50 
    private Vehiculo[] vehiculos;
    private int numVehiculos;
    
    public Concesionario(){
        this.numVehiculos = 0;
        this.vehiculos = new Vehiculo[50];
    }

/**metodo buscaVehiculo Recibe como parámetro una matrícula,
 * @param matricula 
 * busca el vehículo en el concesionario    
 * @return y devuelve una cadena con los datos del vehículo o null si el vehículo buscado no existe */
  
    public Vehiculo buscaVehiculo(String matricula){
        
        for (int i = 0; i < numVehiculos; i++) {
            Vehiculo v = this.vehiculos[i];
            
            if(v.getMatricula().equals(matricula)){
                return v;
            }
        }
        return null;
    }
/** 
     * insertarVehiculo: Recibe todos los datos de un vehículo y trata de insertarlo en el concesionario.
     * @param v
     * @return Devuelve 0 si se hizo con éxito, -1 si el concesionario esta vacio y -2 si la matrícula ya existe.
*/
   
    public int insertarVehiculo(Vehiculo v){
        
        if(this.numVehiculos == this.vehiculos.length){
            return -1;
        }
        
        if(this.buscaVehiculo(v.getMatricula()) != null){
            return -2;
        }else{
            
            this.vehiculos[this.numVehiculos] = v;
            this.numVehiculos++;
            return 0;
        }
        
        
    }
/** listaVehículos: Lista por pantalla los datos de todos los vehículos del concesionario.*/ 
   
    public void listarVehiculos(){
        for (int i = 0; i < numVehiculos; i++) {
            Vehiculo v = this.vehiculos[i];
            System.out.println("Vehiculo Nº "+ (i+1)+v.toString());
        }

    }
/**
 *El metodo borraVehiculos elimina vehiculos del array
     * @param matricula recibe una matricula por parametro 
 * busca el objeto al que le pertenece, si lo encuentra mueve los objetos del array una posicion a la
 * izda sobre la posicion del elemento seleccionado y elimina una posicion del array quedando el elemento
 * eliminado 

*/
public void borraVehiculos(String matricula){

    for (int i = 0; i < numVehiculos; i++) {
        Vehiculo v=this.vehiculos[i];
 if(v.getMatricula().equals(matricula)){
              // Borramos el elemento en la posición i
            for (int j = i; j < numVehiculos - 1; j++) {
                this.vehiculos[j] = this.vehiculos[j + 1];
            }
            numVehiculos--;
            break;   
                
            }
        }
        
    

}
 /** * actualizaKms: 
     * @param matricula Recibe por parámetro una matrícula  
     * @param kms Recibe por parametro número de kilómetros 
     * busca el vehículo con la cuya matrícula coincida y actualiza sus kilómetros.
     * @return Devuelve true si se hizo con éxito y false en caso contrario.*/   
    public boolean actualizaKms(String matricula, int kms){
            
        for (int i = 0; i < numVehiculos; i++) {
            Vehiculo v = this.vehiculos[i];
            
            if(v.getMatricula().equals(matricula)){
                v.setNumKM(kms);
                return true;
            }
        }
        return false;
        
    }
    
}