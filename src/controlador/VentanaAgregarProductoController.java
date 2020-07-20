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
    private BaseDatosCsv baseDeDatos;
    private Stage stage;
    private Compra compra;
    private ArrayList<Producto> filtrarProductos;
    private VentanaCompraController ventanaCompra;
    private ArrayList<Producto> productos;
    private ArrayList<Producto> otros;
    private ArrayList<Producto> entrada;
    private ArrayList<Producto> sopa;
    private ArrayList<Producto> res;
    private ArrayList<Producto> pescado;
    private ArrayList<Producto> pollo;
    private ArrayList<Producto> cerdo;
    private ArrayList<Producto> cAlcohol;
    private ArrayList<Producto> sAlcohol;
    
    
    public void VentanaAgregarProductoControler(Stage stage,BaseDatosCsv baseDeDatos,Compra compra,VentanaCompraController ventanaCompra){
        this.baseDeDatos=baseDeDatos;
        this.stage=stage;
        this.productos=this.baseDeDatos.obtenerProductos();
        for(Producto unProducto:this.productos){
            switch(unProducto.getTipoProducto()){
                case "Entrada":
                    this.entrada.add(unProducto); 
                    break; 
                case "Sopa": 
                    this.sopa.add(unProducto);
                    break; 
                case "Plato con Cerdo": 
                    this.cerdo.add(unProducto);
                    break;
                case "Plato con Res": 
                    this.res.add(unProducto);
                    break;
                case "Plato con Pescado": 
                    this.pescado.add(unProducto);
                    break;
                case "Plato con Pollo":
                    this.pollo.add(unProducto);
                    break;
                case "Bebidas Alcoholicas": 
                    this.cAlcohol.add(unProducto);
                    break;
                case "Bebidas No Alcoholicas": 
                    this.sAlcohol.add(unProducto);
                    break;
                default: 
                    this.otros.add(unProducto);
            }
        }
        this.filtrarProductos=new ArrayList<Producto>();
        this.compra=compra;
        this.ventanaCompra=ventanaCompra;
        this.pTodos.setSelected(true);
        this.todosElegidos(new ActionEvent());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        this.columnaPrecioProducto.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("precio"));
        this.columnaCantidadProducto.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("cantidad"));
        this.columnaTipoProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("tipoProducto"));
        this.otros=new ArrayList<>();
        this.entrada=new ArrayList<>();
        this.sopa=new ArrayList<>();
        this.res=new ArrayList<>();
        this.pescado=new ArrayList<>();
        this.pollo=new ArrayList<>();
        this.cerdo=new ArrayList<>();
        this.cAlcohol=new ArrayList<>();
        this.sAlcohol=new ArrayList<>();
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
            this.sacarSeleccion();
            this.tablaDeProductos.getItems().addAll(this.productos);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.productos);
        }
    }
    public void sacarSeleccionDeTodos(){
        if(this.pTodos.isSelected()){
            this.pTodos.setSelected(false);
            this.tablaDeProductos.getItems().removeAll(this.productos);
        }
    }
    public void sacarSeleccion(){
        this.pOtros.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.otros);
        this.pEntrada.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.entrada);
        this.pSopa.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.sopa);
        this.pPescado.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.pescado);
        this.pRes.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.res);
        this.pPollo.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.pollo);
        this.pCerdo.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.cerdo);
        this.pAlcohol.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.cAlcohol);
        this.pSinAlcohol.setSelected(false);
        this.tablaDeProductos.getItems().removeAll(this.sAlcohol);
    }
    @FXML
    private void otrosElegidos(ActionEvent event) {
        if(this.pOtros.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.otros);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.otros);
        }
    }

    @FXML
    private void entradaElegidos(ActionEvent event) {
        if(this.pEntrada.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.entrada);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.entrada);
        }
    }

    @FXML
    private void sopaElegidos(ActionEvent event) {
        if(this.pSopa.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.sopa);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.sopa);
        }
    }

    @FXML
    private void pescadoElegidos(ActionEvent event) {
        if(this.pPescado.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.pescado);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.pescado);
        }
    }

    @FXML
    private void resElegidos(ActionEvent event) {
        if(this.pRes.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.res);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.res);
        }
    }

    @FXML
    private void polloElegidos(ActionEvent event) {
        if(this.pPollo.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.pollo);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.pollo);
        }
    }

    @FXML
    private void cerdoElegidos(ActionEvent event) {
        if(this.pCerdo.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.cerdo);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.cerdo);
        }
    }

    @FXML
    private void alcoholElegidos(ActionEvent event) {
        if(this.pAlcohol.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.cAlcohol);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.cAlcohol);
        }
    }

    @FXML
    private void sinAlcoholElegidos(ActionEvent event) {
        if(this.pSinAlcohol.isSelected()){
            this.sacarSeleccionDeTodos();
            this.tablaDeProductos.getItems().addAll(this.sAlcohol);
        }else{
            this.tablaDeProductos.getItems().removeAll(this.sAlcohol);
        }
    }
}
