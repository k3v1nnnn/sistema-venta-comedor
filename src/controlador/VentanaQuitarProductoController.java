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

/**
 * FXML Controller class
 *
 * @author RetailAdmin
 */
public class VentanaQuitarProductoController implements Initializable {

    @FXML
    private Button cancelarAceptarProducto;
    @FXML
    private Button cancelarQuitarProducto;
    @FXML
    private TableView<?> tablaProductos;
    @FXML
    private TableColumn<?, ?> columnaCantidad;
    @FXML
    private TableColumn<?, ?> columnaNombre;
    @FXML
    private TableColumn<?, ?> columnaEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelarAceptarProductoBoton(ActionEvent event) {
    }

    @FXML
    private void cancelarQuitarProductoBoton(ActionEvent event) {
    }
    
}
