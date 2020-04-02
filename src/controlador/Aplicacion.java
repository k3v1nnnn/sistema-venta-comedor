
package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Aplicacion extends Application {
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaCompra.fxml"));
        //Pane panel = (Pane) loader.load();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    
}
