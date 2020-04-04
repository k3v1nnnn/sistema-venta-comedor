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
        this.cargarInicio(this,baseDeDatos,stage);
    }
    
    public void cargarInicio(Aplicacion app,BaseDatos baseDeDatos,Stage stage) throws Exception{
        stage.setTitle("Inicio");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaCompra.fxml"));
        Parent parent = loader.load();
        VentanaCompraController ventanaCompra = loader.getController();
        ventanaCompra.VentasCompraControler(app,baseDeDatos);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    
    public void cargarAgregarProducto(BaseDatos baseDeDatos) throws Exception{
        Stage stage = new Stage();
        stage.setTitle("Agregar Producto");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaAgregarProducto.fxml"));
        Parent parent = loader.load();
        VentanaAgregarProductoController ventanaAgregarProducto = loader.getController();
        ventanaAgregarProducto.VentanaAgregarProductoControler(stage,baseDeDatos);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
