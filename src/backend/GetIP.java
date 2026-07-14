/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worldbankia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;


/**
 *
 * @author twumn
 */
public class GetIP {
    
    public static String IPAddress;
    
    public static String IP() throws MalformedURLException, IOException{
      String urlString = "http://checkip.amazonaws.com/";
      URL url = new URL(urlString);
      try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){
          return br.readLine();
      }
       catch (IOException ex) {
           
        
        String errorMessage = "Error: No Internet Connection.";
        JOptionPane.showMessageDialog(null, errorMessage, "No Internet Connection", JOptionPane.ERROR_MESSAGE);
        return null; 
    }
    
    }
    public static void main(String[] args) throws IOException {
      GetIP.IPAddress = IP();
      System.out.println(GetIP.IPAddress);
      }
   
    
    
        
        
    }


