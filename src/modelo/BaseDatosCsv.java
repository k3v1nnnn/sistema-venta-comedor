package modelo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatosCsv implements BaseDatos{
    private final String nombreArchivoComida;
    public BaseDatosCsv(){
        this.nombreArchivoComida="datos/comidas.csv";
    }

    @Override
    public void modificarProducto(String nombre, int precio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregarProducto(String nombre, int precio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregarVenta(int dia, int mes, int annio, double precio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
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
