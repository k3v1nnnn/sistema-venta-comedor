package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
    public IntegerProperty cantidad;
    public StringProperty nombre;
    public IntegerProperty precio;
    
    public Producto(String nuevoNombre,int nuevoPrecio,int nuevaCantidad){
        this.cantidad=new SimpleIntegerProperty(nuevaCantidad);
        this.nombre=new SimpleStringProperty(nuevoNombre);
        this.precio=new SimpleIntegerProperty(nuevoPrecio);
    }
}
