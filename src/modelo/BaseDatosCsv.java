package modelo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatosCsv{
    private final String nombreArchivoComida;
    public BaseDatosCsv(){
        this.nombreArchivoComida="datos/comidas.csv";
    }
    
    public void agregarProducto(String nombre, int precio) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(this.nombreArchivoComida,true));
            String[] productoNuevo = {nombre,String.valueOf(precio)}; 
            csvWriter.writeNext(productoNuevo);
            csvWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarVenta(int dia, int mes, int annio, double precio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void guardarProductos(ArrayList<Producto> productos){
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(this.nombreArchivoComida));
            List<String[]> data = new ArrayList<>(); 
            data.add(new String[] { "nombre", "precio"});
            for (Producto producto:productos){
                data.add(new String[] {producto.getNombre(),String.valueOf(producto.getPrecio())});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productosDatos = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(this.nombreArchivoComida));
            csvReader.skip(1);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                productosDatos.add(new Producto(nextRecord[0],Integer.parseInt(nextRecord[1]),0));
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productosDatos;
    }
    
}
