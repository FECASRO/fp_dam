/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package domXMLTarea;

/**
 *
 * @author MCCLA
 * Descripcion:A partir de los datos del fichero EMPLEADOS.DAT crear un fichero
 * llamado EMPLEADOS.XML usando DOM.
 */
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.RandomAccessFile;

public class ConvertirEmpleadosXML {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("EMPLEADOS.DAT", "r")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("empleados");
            doc.appendChild(root);

            while (raf.getFilePointer() < raf.length()) {
                Element empleado = doc.createElement("empleado");
                root.appendChild(empleado);

                Element codigo = doc.createElement("codigo");
                codigo.setTextContent(String.valueOf(raf.readInt()));
                empleado.appendChild(codigo);

                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(raf.readUTF().trim());
                empleado.appendChild(nombre);

                Element direccion = doc.createElement("direccion");
                direccion.setTextContent(raf.readUTF().trim());
                empleado.appendChild(direccion);

                Element salario = doc.createElement("salario");
                salario.setTextContent(String.valueOf(raf.readFloat()));
                empleado.appendChild(salario);

                Element comision = doc.createElement("comision");
                comision.setTextContent(String.valueOf(raf.readFloat()));
                empleado.appendChild(comision);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("EMPLEADOS.XML");
            transformer.transform(source, result);

            System.out.println("Archivo EMPLEADOS.XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
