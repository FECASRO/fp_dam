/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Lectura_dom_sax;

import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;
/**
 *
 * @author MCCLA
 * descripcion: Visualizar todas las etiquetas del fichero LIBROS.XML utilizando
 * la técnica DOM 
 */
public class LeerLibrosDOM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      try {
            // Creo el objeto Document a partir del archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("LIBROS.XML"));

            // Normalizo el documento
            doc.getDocumentElement().normalize();

            // Obtengo la lista de nodos 
            NodeList listaLibros = doc.getElementsByTagName("libro");

            // Recorro cada libro
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Node libro = listaLibros.item(i);

                if (libro.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoLibro = (Element) libro;

                    // Obtengo atributos y elementos de cada libro
                    String anio = elementoLibro.getAttribute("año");
                    String titulo = elementoLibro.getElementsByTagName("titulo").item(0).getTextContent();
                    String editorial = elementoLibro.getElementsByTagName("editorial").item(0).getTextContent();
                    String precio = elementoLibro.getElementsByTagName("precio").item(0).getTextContent();

                    // Imprimo los datos del libro
                    System.out.println("Año: " + anio);
                    System.out.println("Título: " + titulo);
                    System.out.println("Editorial: " + editorial);
                    System.out.println("Precio: " + precio);

                    // Obtengo el o los autores (puede haber más de uno)
                    NodeList autores = elementoLibro.getElementsByTagName("autor");
                    System.out.println("Autores:");
                    for (int j = 0; j < autores.getLength(); j++) {
                        Element autor = (Element) autores.item(j);
                        String apellido = autor.getElementsByTagName("apellido").item(0).getTextContent();
                        String nombre = autor.getElementsByTagName("nombre").item(0).getTextContent();
                        System.out.println("  - " + nombre + " " + apellido);
                    }
                    System.out.println("---------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}