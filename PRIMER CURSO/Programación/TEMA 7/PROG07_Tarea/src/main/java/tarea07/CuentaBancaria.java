/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea07;
/**
 *
 * @author FELIPE
 * La cuenta Bancaria es una clase abstracta que recibe datos de persona e implementa 
 * imprimible, vale de enlace entre el resto de las clases aportando a las demas 
 * los datos recibidos de persona y aportando el metodo de la interfaz Imprimible
 */
public abstract class CuentaBancaria implements Imprimible {
//variables    
    private Persona titular;
    private double saldo;
    private String IBAN;
/**constructor de clase
     * @param titular recibe
     * @param saldo recibe
     * @param IBAN recibe
*/
    public CuentaBancaria(Persona titular, double saldo, String IBAN) {
        this.titular = titular;
        this.saldo = saldo;
        this.IBAN = IBAN;
    }
// Getters y Setters
    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
    
    @Override
    public String devolverInfoString(){
        return "titular cuenta: " + titular.devolverInfoString() + ", saldo=" + saldo + ", IBAN=" + IBAN;
    }

}