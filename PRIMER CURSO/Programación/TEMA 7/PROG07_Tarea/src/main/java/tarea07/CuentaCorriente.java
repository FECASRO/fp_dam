/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea07;

/**
 *
 * @author FELIPE
 * Las cuentas corrientes no son remuneradas, pero tienen asociada una lista de 
 * entidades autorizadas para cobrar recibos domiciliados en la cuenta.
 */
public class CuentaCorriente extends CuentaBancaria {
    
    private String listaEntidades;
/**constructor de clase recibe parametros y hereda otros de la clase abstracta
     * @param listaEntidades recibe
     * @param titular recibe
     * @param saldo recibe
     * @param IBAN recibe
 */
    public CuentaCorriente(String listaEntidades, Persona titular, double saldo, String IBAN) {
        super(titular, saldo, IBAN);
        this.listaEntidades = listaEntidades;
    }
//Getters y Setteres
    public String getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(String listaEntidades) {
        this.listaEntidades = listaEntidades;
    }
    
    @Override
    public String devolverInfoString(){
        return super.devolverInfoString() + ", listaEntidades=" + listaEntidades;
    }

}