/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relojdigital;

/**
 *
 * @author MCCLA
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Reloj {
    // Propiedades del reloj
    private boolean formato24Horas = true;
    private boolean alarmaActivada = false;
    private int horaAlarma;
    private int minutoAlarma;
    private String mensajeAlarma;

    // Hora actual del reloj (simulada)
    private int horaActual;
    private int minutoActual;

    // Timer para actualizar el reloj
    private Timer timer;

    public Reloj() {
        // Inicializamos la hora
        this.horaActual = 12;
        this.minutoActual = 0;

        // Configuramos el temporizador para actualizar la hora cada minuto
        timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHora();
            }
        });
        timer.start();
    }

    // Getter y Setter para las propiedades
    public boolean isFormato24Horas() {
        return formato24Horas;
    }

    public void setFormato24Horas(boolean formato24Horas) {
        this.formato24Horas = formato24Horas;
    }

    public boolean isAlarmaActivada() {
        return alarmaActivada;
    }

    public void setAlarmaActivada(boolean alarmaActivada) {
        this.alarmaActivada = alarmaActivada;
    }

    public int getHoraAlarma() {
        return horaAlarma;
    }

    public void setHoraAlarma(int horaAlarma) {
        this.horaAlarma = horaAlarma;
    }

    public int getMinutoAlarma() {
        return minutoAlarma;
    }

    public void setMinutoAlarma(int minutoAlarma) {
        this.minutoAlarma = minutoAlarma;
    }

    public String getMensajeAlarma() {
        return mensajeAlarma;
    }

    public void setMensajeAlarma(String mensajeAlarma) {
        this.mensajeAlarma = mensajeAlarma;
    }

    // Actualiza la hora del reloj
    public void actualizarHora() {
        minutoActual++;
        if (minutoActual == 60) {
            minutoActual = 0;
            horaActual++;
            if (horaActual == 24) {
                horaActual = 0;
            }
        }
        verificarAlarma();
    }

    // Verifica si la alarma debe sonar
    private void verificarAlarma() {
        if (alarmaActivada && horaActual == horaAlarma && minutoActual == minutoAlarma) {
            mostrarMensajeAlarma();
        }
    }

    // Muestra el mensaje de la alarma
    private void mostrarMensajeAlarma() {
        System.out.println("¡ALARMA! " + mensajeAlarma);
    }

    // Método para representar la hora como un string
    public String getHoraActual() {
        return formato24Horas ? String.format("%02d:%02d", horaActual, minutoActual) 
                               : String.format("%02d:%02d %s", horaActual % 12, minutoActual, horaActual < 12 ? "AM" : "PM");
    }
}
