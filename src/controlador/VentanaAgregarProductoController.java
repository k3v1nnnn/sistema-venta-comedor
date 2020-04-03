/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author RetailAdmin
 */
public class VentanaAgregarProductoController implements Initializable {

    @FXML
    private Button aceptarPedido;
    @FXML
    private Button cancelarPedido;
    @FXML
    private VBox productosElegidos;
    @FXML
    private TableView<?> tablaDeProductos;
    @FXML
    private TableColumn<?, ?> columnaNombreProducto;
    @FXML
    private TableColumn<?, ?> columnaPrecioProducto;
    @FXML
    private TableColumn<?, ?> columnaCantidadProducto;
    @FXML
    private TableColumn<?, ?> columnaAgregarProducto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aceptarPedidoBoton(ActionEvent event) {
    }

    @FXML
    private void cancelarPedidoBoton(ActionEvent event) {
    }
    
}
