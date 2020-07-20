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
    private final String nombreArchivoFecha;
    private final String nombreArchivoConfiguracion;
    public BaseDatosCsv(){
        this.nombreArchivoComida="datos/comidas.csv";
        this.nombreArchivoFecha="datos/fecha.csv";
        this.nombreArchivoConfiguracion="datos/config.csv";
    }
    
    public ArrayList<String> obtenerUltimaConfiguracion(){
        ArrayList<ArrayList<String>> configs=new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(this.nombreArchivoConfiguracion));
            csvReader.skip(1);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                ArrayList<String> config=new ArrayList<>();
                config.add(nextRecord[0]);
                config.add(nextRecord[1]);
                configs.add(config);
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return configs.get(configs.size()-1);
    }
    
    public void agregarConfiguracion(String nombre, int valor) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(this.nombreArchivoConfiguracion,true));
            String[] productoNuevo = {nombre,String.valueOf(valor)}; 
            csvWriter.writeNext(productoNuevo);
            csvWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void agregarProducto(String nombre, int precio,String tipo) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(this.nombreArchivoComida,true));
            String[] productoNuevo = {nombre,String.valueOf(precio),tipo}; 
            csvWriter.writeNext(productoNuevo);
            csvWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarProductos(ArrayList<Producto> productos){
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(this.nombreArchivoComida));
            List<String[]> data = new ArrayList<>(); 
            data.add(new String[] { "nombre", "precio","tipo"});
            for (Producto producto:productos){
                data.add(new String[] {producto.getNombre(),String.valueOf(producto.getPrecio()),producto.getTipoProducto()});
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
                productosDatos.add(new Producto(nextRecord[0],Integer.parseInt(nextRecord[1]),0,nextRecord[2]));
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productosDatos;
    }
    public ArrayList<String> obtenerUltimaFecha(){
        ArrayList<ArrayList<String>> fechas=new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(this.nombreArchivoFecha));
            csvReader.skip(1);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                ArrayList<String> fecha=new ArrayList<>();
                fecha.add(nextRecord[0]);
                fecha.add(nextRecord[1]);
                fechas.add(fecha);
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechas.get(fechas.size()-1);
    }
    
    public void agregarFecha(String fecha, int numero) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(this.nombreArchivoFecha,true));
            String[] nuevaFecha = {fecha,String.valueOf(numero)}; 
            csvWriter.writeNext(nuevaFecha);
            csvWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseDatosCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
