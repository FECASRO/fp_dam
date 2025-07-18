/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.existdbconnectionmaven;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class ExistDBQuery {
    
     // Datos de conexi�n a eXist-db
    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String COLLECTION_PATH = "/db/ejercicios"; // Ruta de la colecci�n en eXist-db
    private static final String USER = "admin";  
    private static final String PASSWORD = "";  

    public static void main(String[] args) {
        try {
            // 1. Cargar el driver de eXist-db
            String driver = "org.exist.xmldb.DatabaseImpl";
            Class<?> cl = Class.forName(driver);
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);

            // 2. Obtener la colecci�n
            Collection col = DatabaseManager.getCollection(URI + COLLECTION_PATH, USER, PASSWORD);
            if (col == null) {
                System.out.println("No se encontr� la colecci�n: " + COLLECTION_PATH);
                return;
            }

            // 3. Crear el servicio de consulta XPath
            XPathQueryService queryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");

            // 4. Consulta XPath para obtener los t�tulos de los libros
            String query = "//libro/titulo/text()";  

            // 5. Ejecutar la consulta
            ResourceSet result = queryService.query(query);

            // 6. Mostrar los resultados
            ResourceIterator iterator = result.getIterator();
            System.out.println("Lista de t�tulos de libros:");
            while (iterator.hasMoreResources()) {
                Resource res = iterator.nextResource();
                System.out.println("- " + res.getContent());
            }

            // 7. Cerrar la colecci�n
            col.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}