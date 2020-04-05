package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.BaseDatos;
import modelo.Compra;
import modelo.Producto;


public class Aplicacion extends Application {
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BaseDatos baseDeDatos = new BaseDatos();
        this.cargarInicio(baseDeDatos,stage);
    }
    
    public void cargarInicio(BaseDatos baseDeDatos,Stage stage) throws Exception{
        stage.setTitle("Inicio");
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
