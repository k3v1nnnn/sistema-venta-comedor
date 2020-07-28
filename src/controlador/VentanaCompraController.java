package controlador;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.BaseDatosCsv;
import modelo.Compra;
import modelo.Producto;
import modelo.Reporte;

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
    private TableColumn<Producto,Integer> precioColumna;
    @FXML
    private Button agregarProducto;
    @FXML
    private Button sacarProducto;
    @FXML
    private Button aceptarPedido;
    @FXML
    private Button cancelarPedido;
    @FXML
    private Button configProducto;
    @FXML
    private Label fecha;
    @FXML
    private TextField numeroTicket;
    private BaseDatosCsv baseDatos;
    private Compra compra;
    private int montoDescuento;
    private Aplicacion aplicacion;
    private int numeroDeTicket;
    @FXML
    private Button impresora;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productoColumna.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        this.precioColumna.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("precio"));
        this.cantidadColumna.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("cantidad"));
        this.baseDatos=new BaseDatosCsv();
        this.montoDescuento=0;
        this.fecha.setText(this.fecha());
        this.informacionDeTicket(this.baseDatos.obtenerUltimaFecha());
        this.numeroTicket.setText(Integer.toString(this.numeroDeTicket));
        this.compra=new Compra();
        this.actualizarVentana();
    }
    public void informacionDeTicket(ArrayList<String> informacion){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date primeraFecha = sdf.parse(this.fecha());
            Date segundaFecha = sdf.parse(informacion.get(0));
            if(primeraFecha.compareTo(segundaFecha)==0){
                this.numeroDeTicket=Integer.parseInt(informacion.get(1))+1;
            }else{
                this.numeroDeTicket=1;
            }
        }catch(ParseException e){
            this.numeroDeTicket=1;
        }
        
        
    }
    public void VentasCompraControler(Aplicacion app){
        this.aplicacion=app;
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
        stage.setResizable(false);
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
        ventanaQuitarProducto.VentanaQuitarProductoControler(stage,this.compra,this);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void aceptarPedidoBoton(ActionEvent event) {
        String fecha=this.fechaDeLaCompra();
        int precioTotal=this.compra.subTotalCompra()-this.montoDescuento;
        int numero=0;
        try{
            numero=Integer.parseInt(this.numeroTicket.getText());
        }catch(NumberFormatException e){
            numero=0;
        }
        Reporte report=new Reporte();
        Map param=this.compra.impresionDeLaCompra();
        param.put("fecha", fecha);
        param.put("numTicket", Integer.toString(numero));
        report.lanzarReporte(param,this.baseDatos.obtenerUltimaConfiguracion());
        if(this.noCambioElNumeroDeTicket(numero)){
            this.baseDatos.agregarFecha(this.fecha.getText(), numero);
            this.numeroDeTicket=this.numeroDeTicket+1;
        }
        this.compra=new Compra();
        this.numeroTicket.setText(Integer.toString(this.numeroDeTicket));
        this.actualizarVentana();
    }

    @FXML
    private void cancelarPedidoBoton(ActionEvent event) {
        this.compra=new Compra();
        this.actualizarVentana();
    }
    public void actualizarVentana(){
        int subPrecio=this.compra.subTotalCompra();
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

    @FXML
    private void configProductoBoton(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Configuracion Producto");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaConfiguracionProducto.fxml"));
        Parent parent = loader.load();
        VentanaConfiguracionProductoController ventanaConfiguracionProducto = loader.getController();
        ventanaConfiguracionProducto.VentanaConfiguracionProductoController(stage,this.baseDatos);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public String fechaDeLaCompra(){
        Calendar hoy = Calendar.getInstance();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hoy.getTime());
    }
    
    public String fecha(){
        Calendar hoy = Calendar.getInstance();
        return new SimpleDateFormat("yyyy-MM-dd").format(hoy.getTime());
    }
    public boolean noCambioElNumeroDeTicket(int unNumero){
        return this.numeroDeTicket==unNumero;
    }

    @FXML
    private void impresoraImpresion(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Impresoras");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Aplicacion.class.getResource("/vista/VentanaConfiguracionImpresora.fxml"));
        Parent parent = loader.load();
        VentanaConfiguracionImpresoraController ventanaConfiguracionImpresora = loader.getController();
        ventanaConfiguracionImpresora.VentanaConfiguracionImpresoraController(stage,this.baseDatos);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
