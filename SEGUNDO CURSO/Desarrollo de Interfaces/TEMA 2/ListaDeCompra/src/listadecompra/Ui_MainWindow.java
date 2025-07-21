package listadecompra;

/********************************************************************************
 ** Form generated from reading ui file 'VentanaPrincipal.ui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.gui.QApplication;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import com.trolltech.qt.gui.QDialog;

public class Ui_MainWindow extends JFrame {

    private JTable tableWidget;
    private JButton imprimirButton;
    private JButton borrarTodoButton;
    private JButton borrarSeleccionButton;
    private JButton añadirButton;

    public Ui_MainWindow() {
        configurarVentana();
        configurarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        setTitle("Lista de la Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(825, 668);
        setLocationRelativeTo(null);
    }

    private void configurarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Cantidad", "Nombre", "Sección", "Urgente"}, 0
        );
        tableWidget = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableWidget);
        scrollPane.setBounds(0, 0, 821, 571);
        panel.add(scrollPane);

        imprimirButton = new JButton("IMPRIMIR");
        imprimirButton.setBounds(10, 580, 93, 28);
        panel.add(imprimirButton);

        borrarTodoButton = new JButton("BORRAR TODO");
        borrarTodoButton.setBounds(140, 580, 93, 28);
        panel.add(borrarTodoButton);

        borrarSeleccionButton = new JButton("BORRAR SELE");
        borrarSeleccionButton.setBounds(250, 580, 93, 28);
        panel.add(borrarSeleccionButton);

        añadirButton = new JButton("AÑADIR");
        añadirButton.setBounds(370, 580, 93, 28);
        panel.add(añadirButton);
    }

    private void configurarEventos() {
        añadirButton.addActionListener(e -> {
            QApplication.invokeLater(() -> {
                QDialog qDialog = new QDialog();
                DialogProductos dialog = new DialogProductos();
                dialog.setupUi(qDialog);

                if (qDialog.exec() == 1) { // Aceptado
                    String cantidad = dialog.getCantidad();
                    String nombre = dialog.getNombre();
                    String seccion = dialog.getSeccion();
                    boolean urgente = dialog.isUrgente();

                    SwingUtilities.invokeLater(() -> {
                        DefaultTableModel model = (DefaultTableModel) tableWidget.getModel();
                        model.addRow(new Object[]{cantidad, nombre, seccion, urgente});
                    });
                }

                qDialog.dispose();
            });
        });

        borrarTodoButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = (DefaultTableModel) tableWidget.getModel();
            model.setRowCount(0);
        }));

        borrarSeleccionButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            int[] selectedRows = tableWidget.getSelectedRows();
            DefaultTableModel model = (DefaultTableModel) tableWidget.getModel();
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                model.removeRow(selectedRows[i]);
            }
        }));

        imprimirButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = (DefaultTableModel) tableWidget.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            StringBuilder output = new StringBuilder("Lista de la Compra:\n");
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    output.append(model.getColumnName(j))
                          .append(": ")
                          .append(model.getValueAt(i, j))
                          .append(" | ");
                }
                output.append("\n");
            }

            JOptionPane.showMessageDialog(this, output.toString(), "Imprimir", JOptionPane.INFORMATION_MESSAGE);
        }));
    }
}