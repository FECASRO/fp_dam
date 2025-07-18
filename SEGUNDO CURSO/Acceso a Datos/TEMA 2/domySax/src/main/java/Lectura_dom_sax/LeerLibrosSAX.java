/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Lectura_dom_sax;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/**
 *
 * @author MCCLA
 * descripcion: Visualizar todas las etiquetas del fichero LIBROS.XML utilizando
 * la técnica SAX 
 */
public class LeerLibrosSAX {

    public static void main(String[] args) {
        try {
            // Creo el parser 
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // contenido del archivo XML
            DefaultHandler handler = new DefaultHandler() {
                private String etiquetaActual = "";
                private String anio = "";
                private String titulo = "";
                private String editorial = "";
                private String precio = "";
                private String apellido = "";
                private String nombre = "";
                private boolean dentroAutor = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    etiquetaActual = qName;

                    if (qName.equals("libro")) {
                        anio = attributes.getValue("año");
                    } else if (qName.equals("autor")) {
                        dentroAutor = true; // procesando un autor
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    String contenido = new String(ch, start, length).trim();
                    if (contenido.isEmpty()) {
                        return;
                    }

                    switch (etiquetaActual) {
                        case "titulo":
                            titulo = contenido;
                            break;
                        case "editorial":
                            editorial = contenido;
                            break;
                        case "precio":
                            precio = contenido;
                            break;
                        case "apellido":
                            apellido = contenido;
                            break;
                        case "nombre":
                            nombre = contenido;
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (qName.equals("libro")) {
                        // imprimo los datos del libro 
                        System.out.println("Año: " + anio);
                        System.out.println("Título: " + titulo);
                        System.out.println("Editorial: " + editorial);
                        System.out.println("Precio: " + precio);
                        System.out.println("---------------------------");
                    } else if (qName.equals("autor")) {
                        // Imprimo el autor
                        System.out.println("Autor: " + nombre + " " + apellido);
                        apellido = "";
                        nombre = "";
                        dentroAutor = false;
                    }
                }
            };

            // Parseo el archivo XML
            parser.parse("LIBROS.XML", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
