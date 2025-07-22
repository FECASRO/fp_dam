/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea07;

/**
 *
 * @author FELIPE 
 * Las cuentas corrientes personales, que tienen una comisión de mantenimiento (una cantidad fija anual).
 */
public class CuentaCorrientePersonal extends CuentaCorriente{
    
    private double comisionMantenimiento;
/**Constructor de clase
     * @param comisionMantenimiento recibe
     * @param listaEntidades recibe
     * @param titular recibe
     * @param saldo recibe
     * @param IBAN recibe
*/

    public CuentaCorrientePersonal(double comisionMantenimiento, String listaEntidades, Persona titular, double saldo, String IBAN) {
        super(listaEntidades, titular, saldo, IBAN);
        this.comisionMantenimiento = comisionMantenimiento;
    }
// Getters y Setters
    public double getComisionMantenimiento() {
        return comisionMantenimiento;
    }

    public void setComisionMantenimiento(double comisionMantenimiento) {
        this.comisionMantenimiento = comisionMantenimiento;
    }
    
    @Override
    public String devolverInfoString(){
        return super.devolverInfoString() + ", comisionMantenimiento=" + comisionMantenimiento;
    }
    
}
