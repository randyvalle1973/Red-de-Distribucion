package Interfaz;
import arbol.*;
import Grafo.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class VentanaPrincipal {
    public void mostrar() {

        Stage stage = new Stage();

        Button btnAlmacenes =
                new Button("Almacenes");

        Button btnRutas =
                new Button("Gestionar Rutas");

        Button btnDijkstra =
                new Button("Mostrar Rutas");

        Button btnArbol =
                new Button("Visualizar red");

        Button btnSalir =
                new Button("Salir");

        btnAlmacenes.setOnAction(e -> {
            new VentanaAlmacenes().mostrar();
        });

        btnRutas.setOnAction(e -> {
            new VentanaRutas().mostrar();
        });

        btnDijkstra.setOnAction(e -> {
            new VentanaDijkstra().mostrar();
        });

        btnArbol.setOnAction(e -> {

            if (!GrafoInterfaz.grafo.grafovacio()){
                new VentanaArbol().mostrar();
            }else{
                Alert alert =
                        new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);

                alert.setContentText(
                        "Grafo Vacio"
                );
                alert.show();
            }
        });

        btnSalir.setOnAction(actionEvent -> {
            javafx.application.Platform.exit();
        });

       AnchorPane root = new AnchorPane();
        btnAlmacenes.setStyle(

                "-fx-background-color: linear-gradient(to right, #ff512f, #dd2476);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 50;" +
                        "-fx-padding: 10 10 10 10;"
        );
        btnArbol.setStyle(
                "-fx-background-color: linear-gradient(to right, #ff512f, #dd2476);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 50;" +
                        "-fx-padding: 8 8 8 8;"
        );
        btnDijkstra.setStyle(
                "-fx-background-color: linear-gradient(to right, #ff512f, #dd2476);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 50;" +
                        "-fx-padding: 10 20 10 20;"
        );
        btnRutas.setStyle(
                "-fx-background-color: linear-gradient(to right, #ff512f, #dd2476);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 50;" +
                        "-fx-padding: 10 20 10 20;"
        );

        btnSalir.setStyle("-fx-background-color: linear-gradient(to right, #ff512f, #dd2476);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 50;" +
                "-fx-padding: 10 20 10 20;");


        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #232526, #414345);");
        AnchorPane.setTopAnchor(btnAlmacenes, 40.0);
        AnchorPane.setLeftAnchor(btnAlmacenes, 200.0);
        AnchorPane.setRightAnchor(btnAlmacenes, 200.0);

        AnchorPane.setBottomAnchor(btnRutas, 180.0);
        AnchorPane.setRightAnchor(btnRutas, 40.0);

        AnchorPane.setBottomAnchor(btnDijkstra, 180.0);
        AnchorPane.setLeftAnchor(btnDijkstra, 40.0);

        AnchorPane.setBottomAnchor(btnArbol, 180.0);
        AnchorPane.setLeftAnchor(btnArbol, 200.0);
        AnchorPane.setRightAnchor(btnArbol, 200.0);

        AnchorPane.setBottomAnchor(btnSalir, 40.0);
        AnchorPane.setLeftAnchor(btnSalir, 200.0);
        AnchorPane.setRightAnchor(btnSalir, 200.0);


        root.getChildren().addAll(
                btnAlmacenes,
                btnRutas,
                btnDijkstra,
                btnArbol,
                btnSalir
        );

        Scene scene = new Scene(root, 500, 350);

        stage.setScene(scene);
        stage.setTitle("Sistema de Entregas");
        stage.show();
    }
}
