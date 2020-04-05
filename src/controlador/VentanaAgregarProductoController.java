/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Consumer;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.BaseDatos;
import modelo.Compra;
import modelo.Producto;
import vista.ProductoElegido;

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
    private TableView<Producto> tablaDeProductos;
    @FXML
    private TableColumn<Producto,String> columnaNombreProducto;
    @FXML
    private TableColumn<Producto,Double> columnaPrecioProducto;
    @FXML
    private TableColumn<Producto,Integer> columnaCantidadProducto;
    private BaseDatos baseDeDatos;
    private Stage stage;
    private Compra compra;
    private ArrayList<Producto> filtrarProductos;
    private VentanaCompraController ventanaCompra;

    /**
     * Initializes the controller class.
     */
    public void VentanaAgregarProductoControler(Stage stage,BaseDatos baseDeDatos,Compra compra,VentanaCompraController ventanaCompra){
        this.baseDeDatos=baseDeDatos;
        this.stage=stage;
        ArrayList<Producto> p=this.baseDeDatos.obtenerProductos();
        for (Producto prod:p){
            this.tablaDeProductos.getItems().add(prod);
        }
        this.filtrarProductos=new ArrayList<Producto>();
        this.compra=compra;
        this.ventanaCompra=ventanaCompra;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        this.columnaPrecioProducto.setCellValueFactory(new PropertyValueFactory<Producto,Double>("precio"));
        this.columnaCantidadProducto.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("cantidad"));
    }    

    @FXML
    private void aceptarPedidoBoton(ActionEvent event) {
        this.compra.filtrarProductosElegidos(this.filtrarProductos);
        this.stage.close();
        this.ventanaCompra.actualizarPrecio();
    }

    @FXML
    private void cancelarPedidoBoton(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    private void obtenerProducto(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
        int index = this.tablaDeProductos.getSelectionModel().getSelectedIndex();
        Producto producto = this.tablaDeProductos.getItems().get(index);
        if(producto.getCantidad()==0){
            producto.aumentarCantidad();
            this.productosElegidos.getChildren().add(new ProductoElegido(producto));
            this.filtrarProductos.add(producto);
        }else{
            producto.aumentarCantidad();
            for (Node node : this.productosElegidos.getChildren()) {
                ((ProductoElegido) node).actualizarProductoElegido();}
        }
        }
    }
}
