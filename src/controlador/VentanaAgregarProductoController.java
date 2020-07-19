package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.BaseDatosCsv;
import modelo.Compra;
import modelo.Producto;
import vista.ProductoElegido;


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
    private TableColumn<Producto,Integer> columnaPrecioProducto;
    @FXML
    private TableColumn<Producto,Integer> columnaCantidadProducto;
    @FXML
    private TableColumn<Producto, String> columnaTipoProducto;
    private BaseDatosCsv baseDeDatos;
    private Stage stage;
    private Compra compra;
    private ArrayList<Producto> filtrarProductos;
    private VentanaCompraController ventanaCompra;
    @FXML
    private CheckBox pTodos;
    @FXML
    private CheckBox pOtros;
    @FXML
    private CheckBox pEntrada;
    @FXML
    private CheckBox pSopa;
    @FXML
    private CheckBox pPescado;
    @FXML
    private CheckBox pRes;
    @FXML
    private CheckBox pPollo;
    @FXML
    private CheckBox pCerdo;
    @FXML
    private CheckBox pAlcohol;
    @FXML
    private CheckBox pSinAlcohol;

    public void VentanaAgregarProductoControler(Stage stage,BaseDatosCsv baseDeDatos,Compra compra,VentanaCompraController ventanaCompra){
        this.baseDeDatos=baseDeDatos;
        this.stage=stage;
        ArrayList<Producto> productos=this.baseDeDatos.obtenerProductos();
        for (Producto unProducto:productos){
            this.tablaDeProductos.getItems().add(unProducto);
        }
        this.filtrarProductos=new ArrayList<Producto>();
        this.compra=compra;
        this.ventanaCompra=ventanaCompra;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        this.columnaPrecioProducto.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("precio"));
        this.columnaCantidadProducto.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("cantidad"));
        this.columnaTipoProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("tipoProducto"));
    }    

    @FXML
    private void aceptarPedidoBoton(ActionEvent event) {
        this.compra.filtrarProductosElegidos(this.filtrarProductos);
        this.stage.close();
        this.ventanaCompra.actualizarVentana();
    }

    @FXML
    private void cancelarPedidoBoton(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    private void obtenerProducto(MouseEvent event) {
        //event.getButton().equals(MouseButton.PRIMARY)
        int index = this.tablaDeProductos.getSelectionModel().getSelectedIndex();
        this.tablaDeProductos.getSelectionModel().clearSelection();
        if(index!=-1){
            Producto producto = this.tablaDeProductos.getItems().get(index);
            producto.aumentarCantidad();
            if(!producto.estaEnTabla()){
                this.productosElegidos.getChildren().add(new ProductoElegido(producto));
                this.filtrarProductos.add(producto);
                producto.enTabla();
            }else{
                for (Node node : this.productosElegidos.getChildren()) {
                    ((ProductoElegido) node).actualizarProductoElegido();}
            }
        }
    }

    @FXML
    private void todosElegidos(ActionEvent event) {
        if(this.pTodos.isSelected()){
            System.out.println("Correcto");
        }else{
            System.out.println("Incorrecto");
        }
    }

    @FXML
    private void otrosElegidos(ActionEvent event) {
    }

    @FXML
    private void entradaElegidos(ActionEvent event) {
    }

    @FXML
    private void sopaElegidos(ActionEvent event) {
    }

    @FXML
    private void pescadoElegidos(ActionEvent event) {
    }

    @FXML
    private void resElegidos(ActionEvent event) {
    }

    @FXML
    private void polloElegidos(ActionEvent event) {
    }

    @FXML
    private void cerdoElegidos(ActionEvent event) {
    }

    @FXML
    private void alcoholElegidos(ActionEvent event) {
    }

    @FXML
    private void sinAlcoholElegidos(ActionEvent event) {
    }
}
