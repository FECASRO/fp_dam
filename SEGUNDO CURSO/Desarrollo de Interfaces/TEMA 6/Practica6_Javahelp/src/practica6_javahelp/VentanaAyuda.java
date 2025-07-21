/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica6_javahelp;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
/**
 *
 * @author MCCLA
 */
public class VentanaAyuda {
    private WebView webView;
    private WebEngine webEngine;

    public void mostrar(String paginaInicial) {
        Stage stage = new Stage();
        webView = new WebView();
        webEngine = webView.getEngine();

        File file = new File("src/help/" + paginaInicial);
        webEngine.load(file.toURI().toString());

        // 🔎 Barra de búsqueda
        TextField txtBuscar = new TextField();
        txtBuscar.setPromptText("Buscar en la ayuda...");
        Button btnBuscar = new Button("Buscar");

        btnBuscar.setOnAction(e -> {
            String palabra = txtBuscar.getText();
            if (!palabra.isEmpty()) {
                webEngine.executeScript("buscarTexto('" + palabra + "')");
            }
        });

        // 🔄 Botón de índice
        Button btnIndice = new Button("Índice");
        btnIndice.setOnAction(e -> webEngine.load(new File("src/help/indice.html").toURI().toString()));

        // 🔙 Botón de volver atrás
        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> webEngine.executeScript("history.back()"));

        // 📌 Diseño
        HBox barraBusqueda = new HBox(5, txtBuscar, btnBuscar, btnIndice, btnVolver);
        BorderPane root = new BorderPane();
        root.setTop(barraBusqueda);
        root.setCenter(webView);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Ayuda del sistema");
        stage.setScene(scene);
        stage.show();
    }
}