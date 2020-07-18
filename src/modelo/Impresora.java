package modelo;
public class Impresora {
    private final String nombre;
    private final int posicion;
    public Impresora(String unNombre, int unaPosicion){
        this.nombre=unNombre;
        this.posicion=unaPosicion;
    }
    @Override
    public String toString(){
        return this.nombre;
    }
    public int obtenerValor(){
        return this.posicion;
    }
    public String obtenerNombre(){
        return this.nombre;
    }
}
