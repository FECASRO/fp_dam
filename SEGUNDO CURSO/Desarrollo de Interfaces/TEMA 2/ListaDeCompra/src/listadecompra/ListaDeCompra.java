/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package listadecompra;

import com.trolltech.qt.gui.QApplication;
/**
 *
 * @author MCCLA
 */
public class ListaDeCompra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

 // Inicializar QApplication para Qt
        QApplication app = new QApplication(args);

        // Mostrar la ventana principal en el hilo de la GUI
        QApplication.invokeLater(() -> new Ui_MainWindow().setVisible(true));

        // Ejecutar el bucle principal de Qt
        app.exec();
    }
}
