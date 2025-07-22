/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PROG05_Ejerc1;

import PROG05_Ejerc1_util.clase;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author FELIPE
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * AQUI CREAMOS UN MENU QUE GESTIONARA TODAS ESTAS OPCIONES Nuevo
         * Vehículo. Ver Matrícula. Ver Número de Kilómetros. Actualizar
         * Kilómetros. Ver años de antigüedad. Mostrar propietario. Mostrar
         * descripción. Mostrar Precio. Salir.
         */
// Declaracion de variables

        Scanner sn = new Scanner(System.in);
//esto tiene el mismo efecto que Scanner nextLine,pero con menos probabilidad de fallo
        sn.useDelimiter("\n");
//esto da a aentender que usamos el idioma USA que acepta el punto en los decimales evitando fallos
        sn.useLocale(Locale.US);

        boolean salir = false;
        int opcion;
        Vehiculo v = null;

        String marca, matricula, descripcion, nombreProp, DNIProp;
        int km, dia, mes, anio;
        double precio;
        LocalDate fechaMatriculacion;
//Aqui comienza el bucle
        while (!salir) {

            try {
                System.out.println("1. Nuevo Vehiculo.");
                System.out.println("2. Ver Matrícula.");
                System.out.println("3. Ver Número de Kilómetros.");
                System.out.println("4. Actualizar Kilómetros.");
                System.out.println("5. Ver años de antigüedad.");
                System.out.println("6. Mostrar propietario.");
                System.out.println("7. Mostrar descripción.");
                System.out.println("8. Mostrar Precio.");
                System.out.println("9. Salir.");
                System.out.println("Elige una opcion");
                opcion = sn.nextInt();
//eleccion de opcion que da diferentes casos
                switch (opcion) {
                    case 1:

                        System.out.println("Introduce la marca");
                        marca = sn.next();

                        System.out.println("Introduce la matricula");
                        matricula = sn.next();

                        System.out.println("Introduce el numero de km");
                        km = sn.nextInt();
//validacion de uno de los datos con lo realizado en la clase Clase solo kms positivos
                        if (!clase.esPositivo(km)) {
                            throw new Exception("km no es positivo");
                        }

                        System.out.println("Introduce el dia de la fecha de matriculacion");
                        dia = sn.nextInt();

                        System.out.println("Introduce el mes de la fecha de matriculacion");
                        mes = sn.nextInt();

                        System.out.println("Introduce el año de la fecha de matriculacion");
                        anio = sn.nextInt();

                        fechaMatriculacion = LocalDate.of(anio, mes, dia);
//validacion nuevamnte hecha en la clase Clase para evitar introducir mal la fecha
                        if (clase.fechaMayorHoy(fechaMatriculacion)) {
                            throw new Exception("fecha matriculacion es mayor que hoy");
                        }

                        System.out.println("Introduce la descripcion");
                        descripcion = sn.next();

                        System.out.println("Introduce el nombre del propietario");
                        nombreProp = sn.next();

                        System.out.println("Introduce el dni del propietario");
                        DNIProp = sn.next();
//validacion del DNI
                        if (!clase.validarDNI(DNIProp)) {
                            throw new Exception("DNI no valido");
                        }

                        System.out.println("Introduce el precio");
                        precio = sn.nextDouble();
//una vez concluida la fase de peticion de datos se crea el objeto con todos los datos recogidos pasados por parametro
                        v = new Vehiculo(marca, matricula, km, fechaMatriculacion, descripcion, precio, nombreProp, DNIProp);
                        System.out.println("Vehiculo creado");

                        break;

                    case 2:
//si el objeto ha sido creado devuelve matricula, sino pide que crees el objeto
                        if (v != null) {
                            System.out.println("Matricula: " + v.getMatricula());
                        } else {
                            System.out.println("Debes crear un vehiculo");
                        }
                        break;

                    case 3:
//si el objeto ha sido creado devuelve kms, sino pide que crees el objeto
                        if (v != null) {
                            System.out.println("KM: " + v.getNumKM());
                        } else {
                            System.out.println("Debes crear un vehiculo");
                        }
                        break;

                    case 4:
//si el objeto ha sido creado pide Km y los valida lanzando una excepcion si no es positivo,
// sino pide que crees el objeto
                        if (v != null) {

                            System.out.println("Introduce el numero de km");
                            km = sn.nextInt();

                            if (!clase.esPositivo(km)) {
                                throw new Exception("km no es positivo");
                            }

                            v.setNumKM(km);
                            System.out.println("KM actualizados");

                        } else {
                            System.out.println("Debes crear un vehiculo");
                        }

                        break;

                    case 5:
//si el objeto ha sido creado devuelve antiguedad, sino pide que crees el objeto
                        if (v != null) {
                            System.out.println("Antiguedad: " + v.get_Anios() + " años");
                        } else {
                            System.out.println("Debes crear un vehiculo");
                        }

                        break;

                    case 6:
//si el objeto ha sido creado devuelve dni y nombre del propietario, sino pide que crees el objeto
                        if (v != null) {
                            System.out.println("Propietario: " + v.getDniPropietario() + " " + v.getNombrePropietario());
                        } else {
                            System.out.println("Debes crear un vehiculo");
                        }
                        break;
//si el objeto ha sido creado devuelve descripcion,matricula,numero de Kms, sino pide que crees el objeto
                    case 7:

                        if (v != null) {
                            System.out.println("Descripcion: " + v.getDescripcion());
                            System.out.println("Matricula: " + v.getMatricula());
                            System.out.println("KM: " + v.getNumKM());
                        } else {
                            System.out.println("Debes crear un vehiculo");
                        }

                        break;
//si el objeto ha sido creado devuelve precio, sino pide que crees el objeto
                    case 8:

                        if (v != null) {
                            System.out.println("Precio: " + v.getPrecio());
                        } else {
                            System.out.println("Debes crear un vehiculo");
                        }
                        break;
//salir
                    case 9:
                        salir = true;
                        break;
//cubrimos si se escoge una opcion que no esta entre el rango ofrecido
                    default:
                        System.out.println("Elige una opcion entre 1 y 9");
                }
//capturadas las excepciones nos devuelve un mensaje de error 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
