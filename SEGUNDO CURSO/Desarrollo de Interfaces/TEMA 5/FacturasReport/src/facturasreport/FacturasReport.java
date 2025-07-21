/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package facturasreport;

import java.io.InputStream;
import net.sf.jasperreports.engine.*;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MCCLA
 */
public class FacturasReport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Por favor, ingrese el ID del cliente como parámetro.");
            return;
        }

        String idCliente = args[0]; // El ID del cliente pasado como argumento

        // Conexión a la base de datos
        Connection conn = ConexionBD.conectar();

        if (conn != null) {
            try {
                // Cargar el archivo .jasper desde el classpath
                InputStream jasperStream = FacturasReport.class.getResourceAsStream("/facturasreport/facturas.jasper");
                if (jasperStream == null) {
                    throw new RuntimeException("No se encontró el archivo facturas.jasper en el classpath");
                }
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

                // Crear parámetros
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("ID_Cliente", idCliente);

                // Llenar el reporte
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
                JasperViewer.viewReport(jasperPrint, false);

                // Exportar a PDF
                JasperExportManager.exportReportToPdfFile(jasperPrint, "factura_cliente_" + idCliente + ".pdf");

                System.out.println("El informe PDF ha sido generado correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al generar el informe: " + e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
