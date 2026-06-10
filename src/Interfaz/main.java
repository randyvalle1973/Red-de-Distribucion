//Clase donde se ejecuta la interfaz
package Interfaz;

import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage stage) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.mostrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
