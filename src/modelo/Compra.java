package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compra {
    private ArrayList<Producto> listaDeProductos;
    
    public Compra(){
        this.listaDeProductos=new ArrayList<>();
    }
    public void agregarProducto(Producto nuevoProducto){
        this.listaDeProductos.add(nuevoProducto);
    }
    
    public void filtrarProductosElegidos(ArrayList<Producto> nuevosProductos){
        if(!nuevosProductos.isEmpty()){
            for(Producto unProducto:nuevosProductos){
                if(!unProducto.estaVacio()){
                    this.listaDeProductos.add(unProducto);
                }
            }
        }
    }
    public int subTotalCompra(){
        int subTotal=0;
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
    public void actualizarProductos(){
        ArrayList<Producto> nuevaLista=new ArrayList<>();
        for(Producto unProducto:this.listaDeProductos){
                if(!unProducto.estaVacio()){
                    nuevaLista.add(unProducto);
                }
            }
        this.listaDeProductos=nuevaLista;
    }
    
    public void reemplazarProductos(ArrayList<Producto> nuevo){
        this.listaDeProductos=nuevo;
    }
    
    public Map impresionDeLaCompra(){
        Map param=new HashMap<String,Object>();
        List<String> cantidad=new ArrayList<>();
        List<String> precio=new ArrayList<>();
        List<String> productos=new ArrayList<>();
        for(Producto producto:this.listaDeProductos){
            cantidad.add(Integer.toString(producto.getCantidad()));
            precio.add(Integer.toString(producto.getPrecio()));
            productos.add(producto.getNombre());
        }
        param.put("precio",precio);
        param.put("producto",productos);
        param.put("cantidad",cantidad);
        param.put("total", this.subTotalCompra()+"");
        return param;
    }
}
