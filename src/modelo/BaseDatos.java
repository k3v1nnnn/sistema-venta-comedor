package modelo;
import java.util.ArrayList;
public interface BaseDatos {
    public void modificarProducto(String nombre,int precio);
    public void agregarProducto(String nombre,int precio);
    public void agregarVenta(int dia,int mes,int annio,double precio);
    public ArrayList<Producto> obtenerProductos();
}
