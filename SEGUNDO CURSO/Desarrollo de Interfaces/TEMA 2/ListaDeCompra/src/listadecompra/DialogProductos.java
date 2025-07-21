package listadecompra;

/********************************************************************************
 ** Form generated from reading ui file 'productos.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class DialogProductos implements com.trolltech.qt.QUiForm<QDialog> {
    public QPlainTextEdit plainTextEdit_Cantidad;
    public QPlainTextEdit plainTextEdit_Nombre;
    public QCheckBox checkBox_Urgente;
    public QComboBox comboBox_Seccion;
    public QLabel labelCANTIDAD;
    public QLabel label_Nombre;
    public QLabel label_Seccion;
    public QPushButton pushButton_Ok;
    public QLabel label_Aceptar;
    public QPushButton pushButton_Atras;

    public DialogProductos() {
        super();
    }

    public void setupUi(QDialog DialogProductos) {
        DialogProductos.setObjectName("DialogProductos");
        DialogProductos.resize(new QSize(482, 397).expandedTo(DialogProductos.minimumSizeHint()));
        plainTextEdit_Cantidad = new QPlainTextEdit(DialogProductos);
        plainTextEdit_Cantidad.setObjectName("plainTextEdit_Cantidad");
        plainTextEdit_Cantidad.setGeometry(new QRect(150, 20, 71, 31));
        plainTextEdit_Nombre = new QPlainTextEdit(DialogProductos);
        plainTextEdit_Nombre.setObjectName("plainTextEdit_Nombre");
        plainTextEdit_Nombre.setGeometry(new QRect(150, 60, 101, 31));
        checkBox_Urgente = new QCheckBox(DialogProductos);
        checkBox_Urgente.setObjectName("checkBox_Urgente");
        checkBox_Urgente.setGeometry(new QRect(20, 180, 81, 20));
        comboBox_Seccion = new QComboBox(DialogProductos);
        comboBox_Seccion.setObjectName("comboBox_Seccion");
        comboBox_Seccion.setGeometry(new QRect(150, 110, 101, 22));
        labelCANTIDAD = new QLabel(DialogProductos);
        labelCANTIDAD.setObjectName("labelCANTIDAD");
        labelCANTIDAD.setGeometry(new QRect(20, 20, 91, 31));
        label_Nombre = new QLabel(DialogProductos);
        label_Nombre.setObjectName("label_Nombre");
        label_Nombre.setGeometry(new QRect(20, 60, 91, 41));
        label_Seccion = new QLabel(DialogProductos);
        label_Seccion.setObjectName("label_Seccion");
        label_Seccion.setGeometry(new QRect(20, 110, 131, 31));
        pushButton_Ok = new QPushButton(DialogProductos);
        pushButton_Ok.setObjectName("pushButton_Ok");
        pushButton_Ok.setGeometry(new QRect(190, 300, 93, 51));
        label_Aceptar = new QLabel(DialogProductos);
        label_Aceptar.setObjectName("label_Aceptar");
        label_Aceptar.setGeometry(new QRect(210, 270, 61, 31));
        pushButton_Atras = new QPushButton(DialogProductos);
        pushButton_Atras.setObjectName("pushButton_Atras");
        pushButton_Atras.setGeometry(new QRect(380, 30, 93, 28));
        retranslateUi(DialogProductos);

        DialogProductos.connectSlotsByName();

        pushButton_Ok.clicked.connect(DialogProductos, "accept()");
        pushButton_Atras.clicked.connect(DialogProductos, "reject()");
    }

    void retranslateUi(QDialog DialogProductos) {
        DialogProductos.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "Productos", null));
        plainTextEdit_Cantidad.setPlainText("");
        plainTextEdit_Nombre.setPlainText("");
        checkBox_Urgente.setText(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "URGENTE", null));
        comboBox_Seccion.clear();
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "PANADERIA", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "PESCADERIA", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "FRUTERIA", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "CARNICERIA", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "CHARCUTERIA", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "CONSERVAS", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "PERFUMERÍA", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "GENERAL", null));
        labelCANTIDAD.setText(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "CANTIDAD", null));
        label_Nombre.setText(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "NOMBRE", null));
        label_Seccion.setText(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "SECCION DEL SUPER", null));
        pushButton_Ok.setText(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "OK", null));
        label_Aceptar.setText(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "ACEPTAR", null));
        pushButton_Atras.setText(com.trolltech.qt.core.QCoreApplication.translate("DialogProductos", "ATRAS", null));
    }

    public String getCantidad() {
        String cantidad = plainTextEdit_Cantidad.toPlainText();
    try {
        int cantidadInt = Integer.parseInt(cantidad);
        if (cantidadInt <= 0) {
            return "1"; // Valor predeterminado en caso de cantidad no válida
        }
        return String.valueOf(cantidadInt);
    } catch (NumberFormatException e) {
        return "1"; // Valor predeterminado en caso de error
    }
    }

    public String getNombre() {
        return plainTextEdit_Nombre.toPlainText();
    }

    public String getSeccion() {
        return comboBox_Seccion.currentText();
    }

    public boolean isUrgente() {
        return checkBox_Urgente.isChecked();
    }

}