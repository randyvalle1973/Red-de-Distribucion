package Interfaz;
import Grafo.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
public class VentanaDijkstra {
    public void mostrar() {

        Stage stage = new Stage();

        TextField txtOrigen =
                new TextField();

        Button btnEjecutar =
                new Button("Ejecutar");
        Button btnSalir =
                new Button("Salir");

        TableView<resultado> table =
                new TableView<>();

        TableColumn<resultado, String>
                colDestino =
                new TableColumn<>("Destino");

        colDestino.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getDestino()
                )
        );

        TableColumn<resultado, Number>
                colDistancia =
                new TableColumn<>("Distancia");

        colDistancia.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getDistancia()
                )
        );

        TableColumn<resultado, String>
                colRuta =
                new TableColumn<>("Ruta");

        colRuta.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getRuta()
                )
        );

        table.getColumns().addAll(
                colDestino,
                colDistancia,
                colRuta
        );

        btnEjecutar.setOnAction(e -> {
            String origen = txtOrigen.getText();

            List<resultado>
                    resultados =
                    GrafoInterfaz.grafo
                            .dijkstra(
                                    txtOrigen.getText()
                            );

            table.setItems(
                    FXCollections
                            .observableArrayList(
                                    resultados
                            )
            );
            if (origen.isEmpty()){
                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText(null);

                alert.setContentText(
                        "Debe rellenar el campo"
                );

                alert.show();
            }

            if (GrafoInterfaz.grafo.obtenerIndice(origen) == -1){
                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText(null);

                alert.setContentText(
                        "No existe ese almacen"
                );

                alert.show();
            }
        });
        btnSalir.setOnAction( actionEvent -> {
            Stage ventana = (Stage)
                    btnSalir.getScene().getWindow();
            ventana.close();
        });

        VBox root = new VBox(10,
                new Label("Origen"),
                txtOrigen,
                btnEjecutar,
                btnSalir,
                table
        );

        Scene scene =
                new Scene(root, 700, 400);

        stage.setScene(scene);
        stage.setTitle("Dijkstra");
        stage.show();
    }
}
