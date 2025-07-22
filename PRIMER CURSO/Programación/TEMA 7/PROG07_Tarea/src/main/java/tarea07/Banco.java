/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea07;

/**
 *
 * @author FELIPE
 * @version 1.0 
 * Banco: mantendrá como atributo la estructura que almacena las cuentas. Dispondrá de los siguientes métodos:
 * Constructor o constructores.
 * abrirCuenta: recibe por parámetro un objeto CuentaBancaria y lo almacena en la estructura. Devuelve true o false indicando si la operación se realizó con éxito.
 * listadoCuentas: no recibe parámetro y devuelve un array donde cada elemento es una cadena que representa la información de una cuenta.
 * informacionCuenta: recibe un iban por parámetro y devuelve una cadena con la información de la cuenta o null si la cuenta no existe.
 * ingresoCuenta: recibe un iban por parámetro y una cantidad e ingresa la cantidad en la cuenta. Devuelve true o false indicando si la operación se realizó con éxito.
 * retiradaCuenta: recibe un iban por parámetro y una cantidad y trata de retirar la cantidad de la cuenta. Devuelve true o false indicando si la operación se realizó con éxito.
 * obtenerSaldo: Recibe un iban por parámetro y devuelve el saldo de la cuenta si existe. En caso contrario devuelve -1.
 */
public class Banco {

    private CuentaBancaria[] cuentas;
    private final int MAX_CUENTAS = 100;
    private int numeroCuentas;

    public Banco() {
        this.cuentas = new CuentaBancaria[this.MAX_CUENTAS];
        this.numeroCuentas = 0;
    }
/** * recibe por parámetro un objeto CuentaBancaria y lo almacena en la estructura.
 * Devuelve true o false indicando si la operación se realizó con éxito.
     * @param c recibe objeto cuenta bancaria
     * @return true or false
*/
    public boolean abrirCuenta(CuentaBancaria c) {

        if (this.numeroCuentas >= this.MAX_CUENTAS) {
            System.out.println("No hay suficiente espacio");
            return false;
        }

        CuentaBancaria existe = this.buscarCuenta(c.getIBAN());
        if (existe != null) {
            System.out.println("Ya existe la cuenta bancaria");
            return false;
        }

        this.cuentas[this.numeroCuentas] = c;
        this.numeroCuentas++;
        return true;

    }
/**metodo listado cuentas no recibe parámetro y devuelve un array donde cada 
 * elemento es una cadena que representa la información de una cuenta.
     * @return array infocuentas
*/
    public String[] listadoCuentas() {
        String[] infoCuentas = new String[this.numeroCuentas];
        for (int i = 0; i < this.numeroCuentas; i++) {
            infoCuentas[i] = this.cuentas[i].devolverInfoString();
        }
        return infoCuentas;
    }
/**informacionCuenta: recibe un iban por parámetro y devuelve una cadena con la
 *  información de la cuenta o null si la cuenta no existe.
     * @param IBAN recibe
     * @return devolverInfoString o null si la cuenta no existe
*/
    public String informacionCuenta(String IBAN) {

        CuentaBancaria c = this.buscarCuenta(IBAN);
        if (c != null) {
            return c.devolverInfoString();
        }
        return null;
    }
/** * ingresoCuenta: recibe un iban por parámetro y una cantidad e ingresa la 
 *cantidad en la cuenta.Devuelve true o false indicando si la operación se 
 realizó con éxito.
     * @param IBAN recibe
     * @param cantidad recibe
     * @return true or false
*/
    public boolean ingresoCuenta(String IBAN, double cantidad) {

        CuentaBancaria c = this.buscarCuenta(IBAN);
        if (c != null) {
            c.setSaldo(c.getSaldo() + cantidad);
            return true;
        }
        return false;
    }
/** * retiradaCuenta: recibe un iban por parámetro y una cantidad y trata de retirar
  * la cantidad de la cuenta.Devuelve true o false indicando si la operación se 
  * realizó con éxito.
     * @param IBAN recibe
     * @param cantidad recibe
     * @return true or false
*/
    public boolean retiradaCuenta(String IBAN, double cantidad) {

        CuentaBancaria c = this.buscarCuenta(IBAN);
        if (c != null) {

            boolean sePuedeRetirar = false;
            
            if (c.getSaldo() - cantidad > 0) {
                sePuedeRetirar = true;
            }else if(c instanceof CuentaCorrienteEmpresa){
                CuentaCorrienteEmpresa aux = (CuentaCorrienteEmpresa)c;
                if(Math.abs(c.getSaldo() - cantidad) < aux.getMaximoDescubierto()){
                    sePuedeRetirar = true;
                }
            }
            
            if(sePuedeRetirar){
                c.setSaldo(c.getSaldo() - cantidad);
            }
            
            return sePuedeRetirar;

        }
        return false;
    }
/** obtenerSaldo: Recibe un iban por parámetro y devuelve el saldo de la cuenta 
  * si existe.En caso contrario devuelve -1.
     * @param IBAN recibe
     * @return saldo o -1
*/
    public double obtenerSaldo(String IBAN) {
        CuentaBancaria c = this.buscarCuenta(IBAN);
        if (c != null) {
            return c.getSaldo();
        }
        return -1;
    }

    private CuentaBancaria buscarCuenta(String IBAN) {
        for (int i = 0; i < this.numeroCuentas; i++) {
            if (this.cuentas[i].getIBAN().equals(IBAN)) {
                return this.cuentas[i];
            }
        }
        return null;
    }

}