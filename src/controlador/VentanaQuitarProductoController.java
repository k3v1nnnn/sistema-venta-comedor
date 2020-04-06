/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Compra;
import modelo.Producto;
import vista.ProductoElegido;

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
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto,Integer> columnaCantidad;
    @FXML
    private TableColumn<Producto,String> columnaNombre;
    @FXML
    private TableColumn<Producto,Double> columnaPrecio;
    private Stage stage;
    private Compra compra;
    private VentanaCompraController ventanaCompra;

    /**
     * Initializes the controller class.
     */
    
    public void VentanaQuitarProductoControler(Stage stage,Compra compra,VentanaCompraController ventanaCompra){
        this.stage=stage;
        this.compra=compra;
        ArrayList<Producto> p=this.compra.productos();
        for (Producto prod:p){
            this.tablaProductos.getItems().add(prod);
        }
        this.ventanaCompra=ventanaCompra;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<Producto,Double>("precio"));
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("cantidad"));
    }    

    @FXML
    private void cancelarAceptarProductoBoton(ActionEvent event) {
    }

    @FXML
    private void cancelarQuitarProductoBoton(ActionEvent event) {
    }

    @FXML
    private void sacarProducto(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
        int index = this.tablaProductos.getSelectionModel().getSelectedIndex();
        Producto producto = this.tablaProductos.getItems().get(index);
        if(producto.getCantidad()==0){
            producto.setEstado(false);
        }else{
            producto.disminuirCantidad();
            /*for (Node node : this.productosElegidos.getChildren()) {
                ((ProductoElegido) node).actualizarProductoElegido();}*/
        }
        }
    }
    }
