/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package LUXURECONCESIONARIO;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author FELIPE
 * @version 2.0 
 * Esta es la clase pricipal, aqui se pintara el menu y se pediran
 * datos, se instanciara un objeto Concesionario se realizaran las validaciones
 * de los datos de entrada, y se mostraran los adatos por pantalla
 */
public class Principal {

    public static void main(String[] args) {

//instancia de Scanner y dos añadidos para evitar problemas de decimales con punto o coma y espacios
        Scanner sn = new Scanner(System.in);
        sn.useDelimiter("\n");
        sn.useLocale(Locale.US);
// salidas y opciones de los bucles
        boolean salir = false;
        boolean correcto = false;
        int opcion;

//instanciacion de objeto concesionario y variables
        Concesionario concesionario = new Concesionario();
        Vehiculo v;
        String marca, matricula, descripcion, nombreProp, DNIProp;
        int kms = 0;
        double precio = 0;

//bucle de menu principal hasta opcion 5 se repetira
        while (!salir) {

            System.out.println("1. Nuevo vehiculo");
            System.out.println("2. Listar vehiculos");
            System.out.println("3. Buscar vehiculo");
            System.out.println("4. Modificar kms vehiculo");
            System.out.println("5. Salir");
            System.out.println("\n BAJO TU RESPONSABILIDAD.... ");
            System.out.println("6. BORRAR VEHICULO.");
            System.out.println("Elige una opcion");
            opcion = sn.nextInt();

            switch (opcion) {

                case 1:

/* Se introducen los datos solicitados, se validan desde la clase Validacion los requeridos
 y si todo es correcto se crea un nuevo vehiculo en la clase concesionario devolviendo solo
dicha informacion **/               

                    System.out.println("Introduce la marca");
                    marca = sn.next();
//bucle para repetir peticion de dato en lo que la validacion no sea correcta
                    do {
                        System.out.println("Introduce la matricula");
                        matricula = sn.next();
                    } while (!Validacion.validarMatricula(matricula));

                    do {
                        correcto = true;

                        try {
                            System.out.println("Introduce el numero de km");
                            kms = sn.nextInt();
                        } catch (InputMismatchException e) {
                            correcto = false;
                            sn.next();
                        }

                    } while (!correcto);

                    System.out.println("Introduce la descripcion");
                    descripcion = sn.next();
//bucle para repetir peticion de dato en lo que la validacion no sea correcta
                    do {
                        System.out.println("Introduce el nombre del propietario");
                        nombreProp = sn.next();
                    } while (!Validacion.validarNombre(nombreProp));
//bucle para repetir peticion de dato en lo que la validacion no sea correcta
                    do {
                        System.out.println("Introduce el dni del propietario");
                        DNIProp = sn.next();
                    } while (!Validacion.validarDNI(DNIProp));

                    do {
                        correcto = true;

                        try {
                            System.out.println("Introduce el precio");
                            precio = sn.nextDouble();
                        } catch (InputMismatchException e) {
                            correcto = false;
                            sn.next();
                        }

                    } while (!correcto);
//Todo correcto creacion del vehiculo v y comprobacion de si concesionario esta lleno  con el metodo insertar vehiculo
                    v = new Vehiculo(marca, matricula, kms, descripcion, precio, nombreProp, DNIProp);

                    switch (concesionario.insertarVehiculo(v)) {
                        case -2:
                            System.out.println("El vehiculo existe");
                            break;
                        case -1:
                            System.out.println("El concesionario esta lleno");
                            break;
                        case 0:
                            System.out.println("Vehiculo insertado correctamente");
                            break;
                    }

                    break;
                case 2:
/*Se listaran todos los vehiculos creados con los datos definidos en el metodo toString definido
en la clase Vehiculo, y llamado desde la clase concesionario**/
                    concesionario.listarVehiculos();
                    break;
                case 3:
/*Se buscara la matricula del objeto v pidiendola si no existe se comunicara y si existe 
se mostran los datos marca, matricula y precio **/
                    System.out.println("Inserta la matricula");
                    matricula = sn.next();

                    v = concesionario.buscaVehiculo(matricula);

                    if (v != null) {
                        System.out.println("Estos son los datos del Vehiculo buscado:");
                        System.out.println("Marca: "+v.getMarca());
                        System.out.println("Matricula: "+v.getMatricula());
                        System.out.println("Precio: "+v.getPrecio());
                    } else {
                        System.out.println("No existe el vehiculo con la matricula introducida");
                    }

                    break;
                case 4:
/*Si pidiendo la matricula nuevamente coincide con alguna Se actualizaran los Kms del objeto 
v al ser nuevamente introducidos**/
                    System.out.println("Inserta la matricula");
                    matricula = sn.next();

                    System.out.println("Inserta los nuevos kms");
                    kms = sn.nextInt();

                    if (concesionario.actualizaKms(matricula, kms)) {
                        System.out.println("Los kms se ha actualizado correctamente");
                    } else {
                        System.out.println("No existe el vehiculo con la matricula introducida");
                    }

                    break;
// fin del programa y salida 
                case 5:
                    salir = true;
                    break;

case 6:

/*Se buscara la matricula del objeto v pidiendola si no existe se comunicara y si existe 
eliminara el vehiculo seleccionado **/
                    System.out.println("Inserta la matricula");
                    matricula = sn.next();

                    v = concesionario.buscaVehiculo(matricula);

                    if (v != null) {
                        System.out.println("Eliminando Vehiculo...");
                        concesionario.borraVehiculos(matricula);
                    } else {
                        System.out.println("No existe el vehiculo con la matricula introducida");
                    }

break;
            }

        }

    }

}
