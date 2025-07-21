/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio3;

import Ejercicio3.ConexionBD;
import java.io.InputStream;
import net.sf.jasperreports.engine.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MCCLA
 */
public class VentasReport {

    public static void main(String[] args) {
        // Conexión a la base de datos
        Connection conn = ConexionBD.conectar();

        if (conn != null) {
            try {
                // Cargar el archivo .jasper desde el classpath
                InputStream jasperStream = VentasReport.class.getResourceAsStream("/Ejercicio3/ventas.jasper");
                if (jasperStream == null) {
                    throw new RuntimeException("No se encontró el archivo ventas.jasper en el classpath");
                }
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

                // Parámetros (en este caso, no necesitamos ninguno)
                Map<String, Object> parameters = new HashMap<>();

                // Llenar el reporte con los datos de la base de datos
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

                // Mostrar reporte en Viewer (opcional)
                JasperViewer.viewReport(jasperPrint, false);

                // Exportar a PDF
                JasperExportManager.exportReportToPdfFile(jasperPrint, "ventas_totales.pdf");

                System.out.println("El informe de ventas ha sido generado correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al generar el informe: " + e.getMessage());
            } finally {
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
