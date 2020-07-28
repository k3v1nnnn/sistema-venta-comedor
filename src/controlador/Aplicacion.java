package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Aplicacion extends Application {
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.cargarInicio(stage);
    }
    
    public void cargarInicio(Stage stage) throws Exception{
        stage.setTitle("Inicio");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaCompra.fxml"));
        Parent parent = loader.load();
        VentanaCompraController ventanaCompra = loader.getController();
        ventanaCompra.VentasCompraControler(this);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
