package modelo;

import java.util.ArrayList;

public class Compra {
    private ArrayList<Producto> listaDeProductos;
    
    public Compra(){
        this.listaDeProductos=new ArrayList<Producto>();
    }
    public void agregarProducto(Producto nuevoProducto){
        this.listaDeProductos.add(nuevoProducto);
    }
    public void filtrarProductosElegidos(ArrayList<Producto> nuevosProductos){
        if(!nuevosProductos.isEmpty()){
            for(Producto prod:nuevosProductos){
                if(prod.getEstado()){
                    this.listaDeProductos.add(prod);
                }
            }
        }
    }
    public double subTotalCompra(){
        double subTotal=0;
        if(!this.listaDeProductos.isEmpty()){
            for (Producto prod:this.listaDeProductos){
                subTotal=subTotal+(prod.getCantidad()*prod.getPrecio());
            }
        }
        return subTotal;
    }
    public ArrayList<Producto> productos(){
        return this.listaDeProductos;
    }
}
