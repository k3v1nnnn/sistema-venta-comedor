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
import org.bson.Document;

/**
 *
 * @author RetailAdmin
 */
public class BaseDatos {
    private MongoClient cliente;
    private MongoDatabase bdCliente;
    private MongoCollection<Document> productos;
    
    public BaseDatos(){
        this.cliente=MongoClients.create();
        this.bdCliente=this.cliente.getDatabase("comedor");
        this.productos=this.bdCliente.getCollection("productos");
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
}
