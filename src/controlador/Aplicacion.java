package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.BaseDatos;


public class Aplicacion extends Application {
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BaseDatos baseDeDatos = new BaseDatos();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaCompra.fxml"));
        Parent parent = loader.load();
        VentanaCompraController ventanaCompra = loader.getController();
        ventanaCompra.VentasCompraControler(baseDeDatos);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    
}
