package Interfaz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class VentanaAlmacenes {
    private ObservableList<String> lista =
            FXCollections.observableArrayList();

    public void mostrar() {

        Stage stage = new Stage();

        TextField txtNombre = new TextField();

        ListView<String> listView =
                new ListView<>(lista);

        Button btnAgregar =
                new Button("Agregar");

        Button btnSalir =
                new Button("Salir");

        btnAgregar.setOnAction(e -> {

            String nombre = txtNombre.getText();
                boolean existe = GrafoInterfaz.grafo
                        .agregarAlmacen(nombre);
            if (nombre == null || nombre.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("El nombre no puede estar vacío");
                alert.show();
                return;
            }
                if (existe){
                    lista.add(nombre);
                }
                if (!existe){
                    Alert alert =
                            new Alert(Alert.AlertType.ERROR);

                    alert.setHeaderText(null);

                    alert.setContentText(
                            "Ese almacén ya existe"
                    );

                    alert.show();
                }

                txtNombre.clear();

        });

        btnSalir.setOnAction(e -> {

            Stage ventana = (Stage)
            btnSalir.getScene().getWindow();
            ventana.close();
        });

        VBox root = new VBox(10,
                new Label("Nombre:"),
                txtNombre,
                btnAgregar,
                btnSalir,
                listView
        );

        root.setPadding(new Insets(15));

        Scene scene =
                new Scene(root, 300, 400);

        stage.setScene(scene);
        stage.setTitle("Almacenes");
        stage.show();
    }
}
