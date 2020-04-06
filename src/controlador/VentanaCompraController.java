package controlador;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.BaseDatos;
import modelo.Compra;
import modelo.Producto;


public class VentanaCompraController implements Initializable {

    @FXML
    private Label subTotal;
    @FXML
    private Label descuento;
    @FXML
    private Label total;
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto,Integer> cantidadColumna;
    @FXML
    private TableColumn<Producto,String> productoColumna;
    @FXML
    private TableColumn<Producto,Double> precioColumna;
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
    private double montoDescuento;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productoColumna.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        this.precioColumna.setCellValueFactory(new PropertyValueFactory<Producto,Double>("precio"));
        this.cantidadColumna.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("cantidad"));
        this.montoDescuento=0.0;
        this.compra=new Compra();
        this.actualizarVentana();
        
    }
    public void VentasCompraControler(BaseDatos baseDeDatos){
        this.baseDatos=baseDeDatos;
    }

    @FXML
    private void agregarProductoBoton(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Agregar Producto");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaAgregarProducto.fxml"));
        Parent parent = loader.load();
        VentanaAgregarProductoController ventanaAgregarProducto = loader.getController();
        ventanaAgregarProducto.VentanaAgregarProductoControler(stage,this.baseDatos,this.compra,this);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sacarProductoBoton(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Sacar Producto");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaQuitarProducto.fxml"));
        Parent parent = loader.load();
        VentanaQuitarProductoController ventanaQuitarProducto = loader.getController();
        ventanaQuitarProducto.VentanaQuitarProductoControler(stage,this.compra);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aceptarPedidoBoton(ActionEvent event) {
        Calendar hoy = Calendar.getInstance();
        int dia = hoy.get(Calendar.DATE);
        int mes = hoy.get(Calendar.MONTH)+1;
        int ano = hoy.get(Calendar.YEAR);
        double precioTotal=this.compra.subTotalCompra()-this.montoDescuento;
        this.baseDatos.agregarVenta(dia, mes, ano, precioTotal);
        this.compra=new Compra();
        this.actualizarVentana();
    }

    @FXML
    private void cancelarPedidoBoton(ActionEvent event) {
        this.compra=new Compra();
        this.actualizarVentana();
    }
    public void actualizarVentana(){
        double subPrecio=this.compra.subTotalCompra();
        this.subTotal.setText(subPrecio+"");
        this.descuento.setText(this.montoDescuento+"");
        this.total.setText(subPrecio-this.montoDescuento+"");
        this.tablaProductos.getItems().clear();
        for (Producto prod:this.compra.productos()){
            this.tablaProductos.getItems().add(prod);
        }
        if(subPrecio==0 && this.montoDescuento==0){
            this.aceptarPedido.setDisable(true);
        }else{
            this.aceptarPedido.setDisable(false);
        }
        if(this.compra.productos().isEmpty()){
            this.sacarProducto.setDisable(true);
        }else{
            this.sacarProducto.setDisable(false);
        }
    }
}
