/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.Producto;
import vista.ProductoElegido;

/**
 *
 * @author RetailAdmin
 */
public class ProductoElegidoController implements EventHandler<ActionEvent> {
   private ProductoElegido elegido;

    public ProductoElegidoController(ProductoElegido nuevoElegido,Producto producto){
        this.elegido=nuevoElegido;
        producto.setEstado(false);
    }
    @Override
    public void handle(ActionEvent event) {
        this.elegido.setDisable(true);
    }
    
}
