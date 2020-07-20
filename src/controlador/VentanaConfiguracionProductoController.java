package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.BaseDatosCsv;
import modelo.Producto;

public class VentanaConfiguracionProductoController implements Initializable {

    @FXML
    private Button agregarProducto;
    @FXML
    private Button quitarProducto;
    @FXML
    private TableView<Producto> tablaDeProductos;
    private TableColumn<Producto,Integer> columnaIdProducto;
    @FXML
    private TableColumn<Producto,String> columnaNombreProducto;
    @FXML
    private TableColumn<Producto,Integer> columnaPrecioProducto;
    @FXML
    private TableColumn<Producto, String> columnaTipoProducto;
    @FXML
    private TextField nombreProducto;
    @FXML
    private TextField precioProducto;
    @FXML
    private Button guardarProducto;
    @FXML
    private Button nuevoProducto;
    private BaseDatosCsv baseDeDatos;
    private Stage stage;
    private ArrayList<Producto> productos;
    private Producto productoElegido;
    @FXML
    private ChoiceBox<String> tipoDeProducto;

    public void VentanaConfiguracionProductoController(Stage stage,BaseDatosCsv baseDeDatos){
        this.baseDeDatos=baseDeDatos;
        this.stage=stage;
        this.productos=this.baseDeDatos.obtenerProductos();
        for (Producto unProducto:this.productos){
            this.tablaDeProductos.getItems().add(unProducto);
        }
        this.productoElegido=null;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> tipos=FXCollections.observableArrayList();
        tipos.addAll("Entrada","Sopa","Plato con Cerdo","Plato con Res","Plato con Pescado",
                "Plato con Pollo","Bebidas Alcoholicas","Bebidas No Alcoholicas","Otros");
        this.tipoDeProducto.setItems(tipos);
        this.columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        this.columnaPrecioProducto.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("precio"));
        this.columnaTipoProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("tipoProducto"));
        this.configurarBotonesProductoNuevo();
    }    

    @FXML
    private void agregarProductoBoton(ActionEvent event) {
        if(!this.datosEstanVacios()){
            String nombre=this.nombreProducto.getText();
            int precio;
            try{
                precio=Integer.parseInt(this.precioProducto.getText());
            }catch(NumberFormatException e){
                precio=0;
            }
            String tipo=this.tipoDeProducto.getSelectionModel().getSelectedItem();
            Producto productoNuevo=new Producto(nombre,precio,0,tipo);
            this.productos.add(productoNuevo);
            this.tablaDeProductos.getItems().add(productoNuevo);
            this.baseDeDatos.agregarProducto(nombre, precio,tipo);
            this.limpiarCamposTexto();
        }
    }
        

    @FXML
    private void quitarProductoBoton(ActionEvent event) {
        this.tablaDeProductos.getItems().remove(this.productoElegido);
        this.productos.remove(this.productoElegido);
        this.baseDeDatos.guardarProductos(this.productos);
        this.limpiarCamposTexto();
        this.configurarBotonesProductoNuevo();
    }


    @FXML
    private void ProductoElegido(MouseEvent event) {
        int index = this.tablaDeProductos.getSelectionModel().getSelectedIndex();
        this.tablaDeProductos.getSelectionModel().clearSelection();
        if(index!=-1){
            this.productoElegido = this.tablaDeProductos.getItems().get(index);
            this.nombreProducto.setText(this.productoElegido.getNombre());
            this.precioProducto.setText(String.valueOf(this.productoElegido.getPrecio()));
            this.guardarProducto.setDisable(false);
            this.agregarProducto.setDisable(true);
            this.quitarProducto.setDisable(false);
        }
    }
    
    @FXML
    private void guardarProductoBoton(ActionEvent event) {
        if(!this.datosEstanVacios()){
            String nombre=this.nombreProducto.getText();
            int precio=Integer.parseInt(this.precioProducto.getText());
            String tipo=this.tipoDeProducto.getSelectionModel().getSelectedItem();
            this.productoElegido.setNombre(nombre);
            this.productoElegido.setPrecio(precio);
            this.productoElegido.setTipoProducto(tipo);
            this.baseDeDatos.guardarProductos(this.productos);
            this.productoElegido=null;
            this.limpiarCamposTexto();
            this.configurarBotonesProductoNuevo();
        }
    }

    @FXML
    private void nuevoProductoBoton(ActionEvent event) {
        this.configurarBotonesProductoNuevo();
    }
    
    private void configurarBotonesProductoNuevo(){
        this.nombreProducto.setText(null);
        this.precioProducto.setText(null);
        this.quitarProducto.setDisable(true);
        this.guardarProducto.setDisable(true);
        this.agregarProducto.setDisable(false);
    }
    
    private void limpiarCamposTexto(){
        this.nombreProducto.setText(null);
        this.precioProducto.setText(null);
    }
    
    private boolean datosEstanVacios(){
        return (this.precioProducto.getText()==null)||(this.nombreProducto.getText()==null)||(this.tipoDeProducto.getSelectionModel().getSelectedItem()==null);
    }
}
