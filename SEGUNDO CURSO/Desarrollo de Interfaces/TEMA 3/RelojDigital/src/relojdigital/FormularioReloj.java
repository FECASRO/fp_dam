/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relojdigital;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author MCCLA
 */
public class FormularioReloj extends JFrame {
    private Reloj reloj;
    private JLabel lblHora;
    private JTextField txtHoraAlarma, txtMinutoAlarma, txtMensajeAlarma;
    private JCheckBox chkAlarma;
    private JButton btnActivarAlarma;

    public FormularioReloj() {
        reloj = new Reloj();
        initUI();
    }

    private void initUI() {
        setTitle("Reloj Digital");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblHora = new JLabel(reloj.getHoraActual());
        lblHora.setBounds(100, 20, 100, 30);

        JButton btnFormato = new JButton("Formato 12/24");
        btnFormato.setBounds(50, 60, 200, 30);
        btnFormato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloj.setFormato24Horas(!reloj.isFormato24Horas());
                lblHora.setText(reloj.getHoraActual());
            }
        });

        txtHoraAlarma = new JTextField("Hora Alarma");
        txtHoraAlarma.setBounds(50, 100, 80, 30);
        txtMinutoAlarma = new JTextField("Minuto Alarma");
        txtMinutoAlarma.setBounds(140, 100, 80, 30);

        txtMensajeAlarma = new JTextField("Mensaje Alarma");
        txtMensajeAlarma.setBounds(50, 130, 200, 30);

        chkAlarma = new JCheckBox("Activar Alarma");
        chkAlarma.setBounds(50, 160, 200, 30);

        btnActivarAlarma = new JButton("Activar");
        btnActivarAlarma.setBounds(50, 190, 200, 30);
        btnActivarAlarma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloj.setHoraAlarma(Integer.parseInt(txtHoraAlarma.getText()));
                reloj.setMinutoAlarma(Integer.parseInt(txtMinutoAlarma.getText()));
                reloj.setMensajeAlarma(txtMensajeAlarma.getText());
                reloj.setAlarmaActivada(chkAlarma.isSelected());
            }
        });

        add(lblHora);
        add(btnFormato);
        add(txtHoraAlarma);
        add(txtMinutoAlarma);
        add(txtMensajeAlarma);
        add(chkAlarma);
        add(btnActivarAlarma);
        setLayout(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioReloj().setVisible(true);
            }
        });
    }
}