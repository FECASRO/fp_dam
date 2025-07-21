/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio6p1;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author MCCLA
 */
public class LoggerUtil {
    private static final String LOG_FILE = "log.txt"; 

    // Registra eventos en el archivo log
    public static void registrarLog(String mensaje) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            fw.write("[" + timestamp + "] " + mensaje + "\n");
        } catch (IOException e) {
            System.out.println("Error escribiendo en el log: " + e.getMessage());
        }
    }
}
