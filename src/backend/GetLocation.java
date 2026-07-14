/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worldbankia;
import com.maxmind.geoip2.DatabaseReader;
import java.io.IOException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author twumn    
 */
public class GetLocation {
    
    public static String data() throws IOException, GeoIp2Exception{
        
        try{
        
        String IP = GetIP.IP();
        
        //Loads DataBase of IP and corressponding Country
        String dblocation = "C:\\Users\\twumn\\OneDrive\\Documents\\GeoLite2-City_20230801\\GeoLite2-City.mmdb";
        File database = new File(dblocation);
        DatabaseReader dbr = new DatabaseReader.Builder(database).build();
        
        
        InetAddress ipA =InetAddress.getByName(IP);
        CityResponse response = dbr.city(ipA);
        
        return response.getCountry().getName();
        } catch (IOException ex) {
             JFrame frame = new JFrame("Error cannot Obtain Country");
        
     
         
            
        } catch (GeoIp2Exception ex) {
             JFrame frame = new JFrame("Error No Internet Connection, Please Connect and Try Again");
        
      

            
        }
        return null;
        
 
    }
     public static void main(String[] args)throws IOException, GeoIp2Exception{
        
      
    
}   
    private static void showMessageDialog(String title, String message) {

  


    
    

                
        
        
        
    }
}
