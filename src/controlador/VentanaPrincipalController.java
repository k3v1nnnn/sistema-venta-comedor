
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class VentanaPrincipalController implements Initializable {

    @FXML
    private Button agregarProductoBoton;
    @FXML
    private Label subTotalLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Label descuentoLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.subTotalLabel.setText(null);
        this.descuentoLabel.setText(null);
        this.totalLabel.setText(null);
    }    

    @FXML
    private void agregarProductoControlador(ActionEvent event) {
        System.out.print("Press ");
    }
    
}
