package Interfaz;
import arbol.ArbolRuta;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaArbol {
    public void mostrar() {

        Stage stage = new Stage();

        ArbolRuta arbol =
                GrafoInterfaz.grafo.prim();
        if (arbol == null || arbol.getRaiz() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No se pudo generar la red de distribución");
            alert.show();
            return;
        }


        ListView<String> list =
                new ListView<>();

            list.setItems(

                FXCollections.observableArrayList(
                        arbol.preorden(arbol.getRaiz())
                )
        );

        VBox root = new VBox(list);

        Scene scene =
                new Scene(root, 300, 400);

        stage.setScene(scene);
        stage.setTitle("Red");
        stage.show();

    }
}
