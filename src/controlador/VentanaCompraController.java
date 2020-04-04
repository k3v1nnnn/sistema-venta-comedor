package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.BaseDatos;


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
    private Aplicacion aplicacion;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.subTotal.setText(null);
        this.descuento.setText(null);
        this.total.setText(null);
    }
    public void VentasCompraControler(Aplicacion app ,BaseDatos baseDeDatos){
        this.baseDatos=baseDeDatos;
        this.aplicacion=app;
    }

    @FXML
    private void agregarProductoBoton(ActionEvent event) throws Exception {
        this.aplicacion.cargarAgregarProducto(this.baseDatos);
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
