/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import org.bson.Document;

public class BaseDatos {
    private MongoClient cliente;
    private MongoDatabase bdCliente;
    private MongoCollection<Document> productos;
    private MongoCollection<Document> ventas;
    
    public BaseDatos(){
        this.cliente=MongoClients.create();
        this.bdCliente=this.cliente.getDatabase("comedor");
        this.productos=this.bdCliente.getCollection("productos");
        this.ventas=this.bdCliente.getCollection("ventas");
    }
    
    public void modificarProducto(String nombre,int precio) {
		Document filterDoc = new Document("nombre", nombre);
		filterDoc.append("precio", precio);
		MongoCursor<Document> iter = this.productos.find(filterDoc).iterator();
		if(iter.hasNext()){
                    System.out.print(iter.next().getString("nombre"));
                }else{
                    System.out.print("nada");
                }
    }
    public void agregarProducto(String nombre,int precio) {
		Document newDoc = new Document();
		newDoc.put("nombre", nombre);
		newDoc.append("precio", precio);
		this.productos.insertOne(newDoc);
    }
    public void agregarVenta(int dia,int mes,int annio,double precio){
        Document newDocu=new Document();
        newDocu.put("dia",dia);
        newDocu.put("mes",mes);
        newDocu.put("ano",annio);
        newDocu.append("precio",precio);
        this.ventas.insertOne(newDocu);
    }
    public ArrayList<Producto> obtenerProductos(){
        ArrayList<Producto> productosDatos = new ArrayList<>();
        for (Document docu:this.productos.find()){
            productosDatos.add(new Producto(docu.getString("nombre"),docu.getDouble("precio"),0));
        }
        return productosDatos;
    }
}
