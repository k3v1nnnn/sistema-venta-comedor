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
    public int precioFinal(){
        int precio=0;
        for(Producto i : this.listaDeProductos){
            precio=precio+((i.cantidad.get())*(i.precio.get()));
        }
        return precio;
    }
    public void compraFinalizada(){
        System.out.print(this.precioFinal());
    }
}
