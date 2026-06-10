package Interfaz;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Grafo.*;
public class VentanaRutas {
    public void mostrar() {

        Stage stage = new Stage();

        TextField txtOrigen =
                new TextField();

        TextField txtDestino =
                new TextField();
        TextField txtPeso =
                new TextField();
        txtPeso.setTextFormatter(
                new TextFormatter<>(change -> {

                    if(change.getControlNewText().matches("\\d*")) {
                        return change;
                    }

                    return null;
                })
        );

        Button btnAgregar =
                new Button("Agregar Ruta");
        Button btnSalir =
                new Button("Salir");
        btnAgregar.setOnAction(e -> {

            String o = txtOrigen.getText();
            String d = txtDestino.getText();
            String p = txtPeso.getText();


            int origen =
                    GrafoInterfaz.grafo
                            .obtenerIndice(o);
            int destino =
                    GrafoInterfaz.grafo
                            .obtenerIndice(d);
            if(origen == -1 || destino == -1){
                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText(null);

                alert.setContentText(
                        "Uno de los almacenes no existe"
                );

                alert.show();

                return;
            }

            if (o.isEmpty() || d.isEmpty() || p.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setContentText(
                        "Debe llenar todos los campos"
                );

                alert.show();
            }else{
                int peso =
                        Integer.parseInt(
                                txtPeso.getText()
                        );
                if (peso > 0) {
                    GrafoInterfaz.grafo
                            .agregarRuta(o, d, peso);
                    txtOrigen.clear();
                    txtDestino.clear();
                    txtPeso.clear();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setContentText(
                            "El peso debe ser mayor que 0"
                    );

                    alert.show();

                }

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

                new Label("Destino"),
                txtDestino,

                new Label("Peso"),
                txtPeso,

                btnAgregar,
                btnSalir
        );

        root.setPadding(new Insets(15));

        Scene scene =
                new Scene(root, 300, 300);

        stage.setScene(scene);
        stage.setTitle("Rutas");
        stage.show();
    }
}
