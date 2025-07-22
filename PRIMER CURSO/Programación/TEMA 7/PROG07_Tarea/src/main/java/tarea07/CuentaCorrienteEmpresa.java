/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea07;

/**
 *
 * @author FELIPE
 * Las cuentas corrientes de empresa, que no la tienen. Además, las cuentas de 
 * empresa permiten tener una cierta cantidad de descubierto (máximo descubierto 
 * permitido) y por tanto un tipo de interés por descubierto y una comisión fija
 * por cada descubierto que se tenga. Es el único tipo de cuenta que permite tener
 * descubiertos.
 */
public class CuentaCorrienteEmpresa extends CuentaCorriente {
    
    private double maximoDescubierto;
    private double tipoInteresDescubierto;
    private double comisiónFijaDescubierto;
/**Constructor de clase
     * @param maximoDescubierto
     * @param tipoInteresDescubierto recibe
     * @param comisiónFijaDescubierto recibe
     * @param listaEntidades recibe
     * @param titular recibe
     * @param saldo recibe
     * @param IBAN recibe
*/
    public CuentaCorrienteEmpresa(double maximoDescubierto, double tipoInteresDescubierto, double comisiónFijaDescubierto, String listaEntidades, Persona titular, double saldo, String IBAN) {
        super(listaEntidades, titular, saldo, IBAN);
        this.maximoDescubierto = maximoDescubierto;
        this.tipoInteresDescubierto = tipoInteresDescubierto;
        this.comisiónFijaDescubierto = comisiónFijaDescubierto;
    }
// Getters y Setters
    public double getMaximoDescubierto() {
        return maximoDescubierto;
    }

    public void setMaximoDescubierto(double maximoDescubierto) {
        this.maximoDescubierto = maximoDescubierto;
    }

    public double getTipoInteresDescubierto() {
        return tipoInteresDescubierto;
    }

    public void setTipoInteresDescubierto(double tipoInteresDescubierto) {
        this.tipoInteresDescubierto = tipoInteresDescubierto;
    }

    public double getComisiónFijaDescubierto() {
        return comisiónFijaDescubierto;
    }

    public void setComisiónFijaDescubierto(double comisiónFijaDescubierto) {
        this.comisiónFijaDescubierto = comisiónFijaDescubierto;
    }
    
    @Override
    public String devolverInfoString(){
        return super.devolverInfoString() + ", maximoDescubierto=" + maximoDescubierto + ", tipoInteresDescubierto=" + tipoInteresDescubierto + ", comisionFijaDescubierto=" + comisiónFijaDescubierto;
    }

    
}
