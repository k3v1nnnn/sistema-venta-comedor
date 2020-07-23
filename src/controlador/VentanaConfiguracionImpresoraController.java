package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import modelo.BaseDatosCsv;
import modelo.Impresora;


public class VentanaConfiguracionImpresoraController implements Initializable {

    @FXML
    private Button aceptar;
    @FXML
    private ChoiceBox<Impresora> impresoras;
    @FXML
    private Label nombreDeImpresora;
    private Stage stage;
    private BaseDatosCsv baseDatos;
    @FXML
    private Button cerrar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Impresora> lista=FXCollections.observableArrayList();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for(int i = 0; i < services.length;i++){
            lista.add(new Impresora(services[i].getName(),i));
        }
        this.impresoras.setItems(lista);
        

    }
    public void VentanaConfiguracionImpresoraController(Stage stage,BaseDatosCsv baseDeDatos){
        this.stage=stage;
        this.baseDatos=baseDeDatos;
    }

    @FXML
    private void aceptarBoton(ActionEvent event) {
        Impresora elegido=this.impresoras.getSelectionModel().getSelectedItem();
        if(elegido!=null){
            String nombre=elegido.obtenerNombre();
            int valor=elegido.obtenerValor();
            this.baseDatos.agregarConfiguracion(nombre, valor);
            this.nombreDeImpresora.setText(nombre);
            this.cerrar.setText("Cerrar");
            this.aceptar.setDisable(true);
        }
    }

    @FXML
    private void cerrarBoton(ActionEvent event) {
        this.stage.close();
    }
    
}
