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
        if(nuevosProductos.isEmpty()){
            System.out.println("vacio");
        }else{
            System.out.println("lleno");
        }
    }
}
