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
}
