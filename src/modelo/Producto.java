package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
    private IntegerProperty cantidad;
    private StringProperty nombre;
    private IntegerProperty precio;
    private boolean nuevoEnTabla;
    
    public Producto(String nuevoNombre,int nuevoPrecio,int nuevaCantidad){
        this.cantidad=new SimpleIntegerProperty(nuevaCantidad);
        this.nombre=new SimpleStringProperty(nuevoNombre);
        this.precio=new SimpleIntegerProperty(nuevoPrecio);
        this.nuevoEnTabla=false;
    }
    public StringProperty nombreProperty() {return this.nombre;};
    public IntegerProperty precioProperty() {return this.precio;};
    public IntegerProperty cantidadProperty() {return this.cantidad;};
    
    public String getNombre() {return this.nombre.get();}
    public int getPrecio() {return this.precio.get();}
    public int getCantidad() {return this.cantidad.get();}
    
    
    public void setNombre(String nombre) {this.nombre.set(nombre);}
    public void setPrecio(int precio) {this.precio.set(precio);}
    public void setCantidad(int cantidad) {this.cantidad.set(cantidad);}
    
    
    public boolean estaVacio(){return this.cantidad.get()==0;}
    public boolean estaEnTabla(){return this.nuevoEnTabla;}
    public String infoProducto(){return (String.valueOf(this.cantidad.get())+" - "
            +this.nombre.get()+" - "+String.valueOf(this.precio.get())); }
    public void enTabla(){this.nuevoEnTabla=true;}
    public void aumentarCantidad(){
        this.setCantidad(this.getCantidad()+1);
    }
    public void disminuirCantidad(){
        if(!this.estaVacio()){
            this.setCantidad(this.getCantidad()-1);
        }
    }
}
