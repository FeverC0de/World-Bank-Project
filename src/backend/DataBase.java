/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worldbankia;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author twumn
 */
public class DataBase {
   
    public static Object GetData(String collections,String country, String series, String year ) throws IOException, GeoIp2Exception{
        String url = "mongodb://localhost:27017";
        MongoClientURI clientURI = new MongoClientURI(url);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("WorldBank");
        MongoCollection collection = mongoDatabase.getCollection(collections);

        Document findQuery = new Document();
        findQuery.put("Country Name",country);
        findQuery.put("Series Name",series);

        Document result = (Document) collection.find(findQuery).first();


        return result.get(year);
    }
    
    public static void main(String[] args) throws IOException, GeoIp2Exception {
      System.out.print(GetData("Stuff", GetLocation.data(),"GDP per capita (constant 2015 US$)", "2020" ));
//        "GDP per capita (constant 2015 US$)"
    }

    public static Object GetCapital(String country) throws IOException, GeoIp2Exception{
        String uri = "mongodb://localhost:27017";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("WorldBank");
        MongoCollection collection = mongoDatabase.getCollection("Country List");

        Document findQuery = new Document();
        findQuery.put("country",country);


        Document result = (Document) collection.find(findQuery).first();
    
   
    return result.getString("capital");
    }
    
    public static void UndoTable(JTable table){
        int numberOfColumns = table.getModel().getColumnCount();
        DefaultTableModel tb1Model = (DefaultTableModel) table.getModel();
        if (numberOfColumns> 1){
            tb1Model.setColumnCount(numberOfColumns - 1);
        }
    }
    
    public static void ClearTable(JTable table){
        DefaultTableModel tb2Model = (DefaultTableModel) table.getModel();
        tb2Model.setColumnCount(0);
        tb2Model.setRowCount(0);

    }
    
    public static String DataString(String collection,String country, String series, String year) throws IOException, GeoIp2Exception{
        Object output = GetData(collection, country, series, year);

        if (output instanceof Number) {
            Number numericValue = (Number) output;
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
            String outputString = decimalFormat.format(numericValue);
            return outputString;

        }
        if(output == null){
            return "N/A";
        }

        if(output == ".."){
            return "N/A";
        }
        else{
            return "N/A";
        }

    }
}
    
    
//    public static void main(String[] args) throws IOException, GeoIp2Exception {
//      System.out.print(GetData("Stuff", GetLocation.data(),"GDP per capita (constant 2015 US$)", "2020" ));
////        "GDP per capita (constant 2015 US$)"
    
        
   
    
    
    
    
   
    
    
    

    
       


    
    