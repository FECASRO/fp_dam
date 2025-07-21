package ejercicio4;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MCCLA
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/fabrica";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Cargar el informe principal compilado (.jasper)
            String rutaInforme = "src/Ejercicio4/facturas.jasper";
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(rutaInforme);

            // Parámetros del informe
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID_Cliente", 1);

            // Ruta del subinforme (solo el directorio que contiene el subinforme)
            String rutaSubinforme = "C:/Users/MCCLA/Documents/netbeans 8.2/Ejercicio4/src/ejercicio4/";
            parametros.put("SUBREPORT_DIR", rutaSubinforme);

            // Llenar el informe con datos
            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, conexion);

            // Exportar a PDF
            JasperExportManager.exportReportToPdfFile(print, "facturas_cliente.pdf");

            // Mostrar el informe en pantalla
            JasperViewer.viewReport(print, false);

            System.out.println("Informe generado correctamente.");

            // Cerrar la conexión
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error al generar el informe: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
