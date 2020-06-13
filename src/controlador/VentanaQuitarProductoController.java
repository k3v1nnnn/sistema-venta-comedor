package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Compra;
import modelo.Producto;

public class VentanaQuitarProductoController implements Initializable {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto,Integer> columnaCantidad;
    @FXML
    private TableColumn<Producto,String> columnaNombre;
    @FXML
    private TableColumn<Producto,Integer> columnaPrecio;
    @FXML
    private Button aceptarProductos;
    @FXML
    private Button cancelarProducto;
    private ArrayList<Producto> aux;
    private Stage stage;
    private Compra compra;
    private VentanaCompraController ventanaCompra;


    public void VentanaQuitarProductoControler(Stage stage,Compra compra,VentanaCompraController ventanaCompra){
        this.stage=stage;
        this.compra=compra;
        this.aux=new ArrayList<Producto>();
        ArrayList<Producto> listaProductos=this.compra.productos();
        for (Producto prod:listaProductos){
            this.aux.add(new Producto(10,prod.getNombre(),prod.getPrecio(),prod.getCantidad()));
            this.tablaProductos.getItems().add(prod);
        }
        this.ventanaCompra=ventanaCompra;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("precio"));
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("cantidad"));
    }    


    @FXML
    private void sacarProducto(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
        int index = this.tablaProductos.getSelectionModel().getSelectedIndex();
        Producto producto = this.tablaProductos.getItems().get(index);
        producto.disminuirCantidad();
        }
    }

    @FXML
    private void aceptarProductoBoton(ActionEvent event) {
        this.aux=null;
        this.stage.close();
        this.compra.actualizarProductos();
        this.ventanaCompra.actualizarVentana();
    }

    @FXML
    private void cancelarProductoBoton(ActionEvent event) {
        this.compra.reemplazarProductos(this.aux);
        this.stage.close();
        this.ventanaCompra.actualizarVentana();
    }
    }
