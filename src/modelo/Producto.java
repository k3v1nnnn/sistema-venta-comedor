package modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
    private IntegerProperty cantidad;
    private StringProperty nombre;
    private DoubleProperty precio;
    
    public Producto(String nuevoNombre,double nuevoPrecio,int nuevaCantidad){
        this.cantidad=new SimpleIntegerProperty(nuevaCantidad);
        this.nombre=new SimpleStringProperty(nuevoNombre);
        this.precio=new SimpleDoubleProperty(nuevoPrecio);
    }
    public StringProperty nombreProperty() {return this.nombre;};
    public DoubleProperty precioProperty() {return this.precio;};
    public IntegerProperty cantidadProperty() {return this.cantidad;};
    
    public String getNombre() {return this.nombre.get();}
    public double getPrecio() {return this.precio.get();}
    public int getCantidad() {return this.cantidad.get();}
    
    public void setNombre(String nombre) {this.nombre.set(nombre);}
    public void setPrecio(double precio) {this.precio.set(precio);}
    public void setCantidad(int cantidad) {this.cantidad.set(cantidad);}
}
