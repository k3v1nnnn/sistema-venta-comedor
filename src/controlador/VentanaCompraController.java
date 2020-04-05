package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelo.BaseDatos;
import modelo.Compra;


public class VentanaCompraController implements Initializable {

    @FXML
    private Label subTotal;
    @FXML
    private Label descuento;
    @FXML
    private Label total;
    @FXML
    private TableView<?> tablaProductos;
    @FXML
    private TableColumn<?, ?> cantidadColumna;
    @FXML
    private TableColumn<?, ?> productoColumna;
    @FXML
    private TableColumn<?, ?> precioColumna;
    @FXML
    private Button agregarProducto;
    @FXML
    private Button sacarProducto;
    @FXML
    private Button aceptarPedido;
    @FXML
    private Button cancelarPedido;
    private BaseDatos baseDatos;
    private Compra compra;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.subTotal.setText(null);
        this.descuento.setText(null);
        this.total.setText(null);
    }
    public void VentasCompraControler(BaseDatos baseDeDatos){
        this.baseDatos=baseDeDatos;
        this.compra=new Compra();
    }

    @FXML
    private void agregarProductoBoton(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Agregar Producto");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaAgregarProducto.fxml"));
        Parent parent = loader.load();
        VentanaAgregarProductoController ventanaAgregarProducto = loader.getController();
        ventanaAgregarProducto.VentanaAgregarProductoControler(stage,this.baseDatos,this.compra);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sacarProductoBoton(ActionEvent event) {
    }

    @FXML
    private void aceptarPedidoBoton(ActionEvent event) {
    }

    @FXML
    private void cancelarPedidoBoton(ActionEvent event) {
    }
    
}
