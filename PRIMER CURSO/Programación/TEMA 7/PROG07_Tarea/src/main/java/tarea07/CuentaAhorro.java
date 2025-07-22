/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea07;

/**
 *
 * @author FELIPE
 * La cuenta de ahorro hereda de cuenta bancaria Las cuentas de ahorro son 
 * remuneradas y tienen un determinado tipo de interés.
 */
public class CuentaAhorro extends CuentaBancaria {
    
    private double tipoInteresAnual;
/**Constructor de clase
     * @param tipoInteresAnual recibe
     * @param titular recibe
     * @param saldo recibe
     * @param IBAN recibe
*/
    public CuentaAhorro(double tipoInteresAnual, Persona titular, double saldo, String IBAN) {
        super(titular, saldo, IBAN);
        this.tipoInteresAnual = tipoInteresAnual;
    }
/**getter y setter para obtener interes anual y devolverlo
     * @return tipoInteresAnual  
 */
    public double getTipoInteresAnual() {
        return tipoInteresAnual;
    }

    public void setTipoInteresAnual(double tipoInteresAnual) {
        this.tipoInteresAnual = tipoInteresAnual;
    }
    
    @Override
    public String devolverInfoString(){
        return super.devolverInfoString() + ", tipoInteresAnual=" + tipoInteresAnual;
    }
    
}