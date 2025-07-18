/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package empresafamiliares;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.io.File;

/**
 *
 * @author MCCLA
 */
public class EmpresaFamiliares {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        crearBaseDatos();
        listarTodosLosJefes(); // 🔍 Verifica si se guardaron los datos
        consultarJefesMayor55(); // Consulta jefes mayores de 55 años
        modificarEdadMiguel(); // Modifica la edad de Miguel
        eliminarJefesMasDe6Anios(); // Elimina jefes con más de 6 años en la empresa
        listarTodosLosJefes(); // 🔍 Verifica los datos restantes
    }

    public static void crearBaseDatos() {
        File fichero = new File("BDJefeHijo");
        fichero.delete(); // Borra la BD si existe previamente
        
        ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo");
        
        baseDatos.store(new Jefe("Ángel", 5, 53, new Hijo("Gustavo", 7)));
        baseDatos.store(new Jefe("Nieves", 3, 45, new Hijo("Iván", 3)));
        baseDatos.store(new Jefe("Jesús", 3, 5, new Hijo("Noelia", 3)));
        baseDatos.store(new Jefe("Dolores", 5, 63, new Hijo("Sergio", 7)));
        baseDatos.store(new Jefe("Vicki", 3, 5, null));
        baseDatos.store(new Jefe("Fátima", 5, 63, new Hijo("Lidia", 27)));
        baseDatos.store(new Jefe("Juan Luís", 3, 5, null));
        baseDatos.store(new Jefe("Elena", 1, 42, new Hijo("David", 19)));
        baseDatos.store(new Jefe("Miguel", 20, 45, new Hijo("Paula", 3)));
        baseDatos.store(new Jefe("Jesús", 19, 44, new Hijo("Rubén", 12)));

        baseDatos.close();
        System.out.println(" Base de datos creada y objetos almacenados correctamente.");
    }

    public static void listarTodosLosJefes() {
        ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo");

        ObjectSet<Jefe> resultado = baseDatos.query(Jefe.class);

        System.out.println("\n Todos los jefes en la base de datos:");
        if (resultado.isEmpty()) {
            System.out.println("️ No hay datos en la base de datos.");
        } else {
            while (resultado.hasNext()) {
                Jefe jefe = resultado.next();
                System.out.println(" Nombre: " + jefe.getNombre() + ", Edad: " + jefe.getEdad() + ", Años en empresa: " + jefe.getAniosEmpresa());
            }
        }

        baseDatos.close();
    }

    public static void consultarJefesMayor55() {
        ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo");
        Query query = baseDatos.query();
        query.constrain(Jefe.class);
        query.descend("edad").constrain(55).greater();
        
        ObjectSet<Jefe> resultado = query.execute();

        System.out.println("\n Jefes con más de 55 años:");
        if (resultado.isEmpty()) {
            System.out.println("️ No hay jefes mayores de 55 años.");
        } else {
            for (Jefe j : resultado) {
                System.out.println(" " + j.getNombre() + " - " + j.getEdad() + " años.");
            }
        }

        baseDatos.close();
    }

    public static void modificarEdadMiguel() {
        ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo");
        Query query = baseDatos.query();
        query.constrain(Jefe.class);
        query.descend("nombre").constrain("Miguel");

        ObjectSet<Jefe> resultado = query.execute();
        if (!resultado.isEmpty()) {
            Jefe miguel = resultado.next();
            miguel.setEdad(miguel.getEdad() + 1);
            baseDatos.store(miguel);
            System.out.println("\n Se ha incrementado la edad de Miguel a " + miguel.getEdad() + " años.");
        } else {
            System.out.println("\n️ No se encontró a Miguel en la base de datos.");
        }

        baseDatos.close();
    }

    public static void eliminarJefesMasDe6Anios() {
        ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo");
        Query query = baseDatos.query();
        query.constrain(Jefe.class);
        query.descend("aniosEmpresa").constrain(6).greater();

        ObjectSet<Jefe> resultado = query.execute();
        if (!resultado.isEmpty()) {
            for (Jefe j : resultado) {
                baseDatos.delete(j);
                System.out.println("️ Se eliminó a " + j.getNombre() + " que llevaba " + j.getAniosEmpresa() + " años en la empresa.");
            }
        } else {
            System.out.println("\n No hay jefes con más de 6 años en la empresa.");
        }

        baseDatos.close();
    }
}