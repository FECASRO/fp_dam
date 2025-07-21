/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package practica6_javahelp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author MCCLA
 */
public class Practica6_Javahelp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        Button btnAyuda = new Button("Abrir Ayuda");
        btnAyuda.setOnAction(e -> new VentanaAyuda().mostrar("indice.html"));

        root.getChildren().add(btnAyuda);
        primaryStage.setTitle("Sistema de Ayuda JavaFX");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}