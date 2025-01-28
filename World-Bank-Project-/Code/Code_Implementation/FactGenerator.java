/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.worldbankia;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import java.util.ArrayList;
import java.util.Random;
import static com.mycompany.worldbankia.DataBase.DataString;
import java.io.IOException;
/**
 *
 * @author twumn
 */
public class FactGenerator {
    
    public static String MakeFact(String country, ArrayList<String> indicators) throws IOException, GeoIp2Exception{
        
        Random random = new Random();
        int min = 2013;
        int max = 2022;
        
        int year = random.nextInt(max-min+1) + min; //Picks a randomyear between 2013 and 2022
        
        String yearstr = Integer.toString(year); //Converts randomly picked integer into string

        int size = indicators.size(); //Gets the size of the indicators arraylist
        int indicatorIndex = random.nextInt(size); //Randomizes the indicators  Index
        String indicator = indicators.get(indicatorIndex); //Gets the Indicator
        String value = DataString("Indicators",country, indicator, yearstr);
        String fact = "Did you know that The " +indicator +" of " +country+ " in the Year " + year +  " was " + value; //Generates Fact
        
        return fact;
        
        
        
               
                
               
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, GeoIp2Exception {
        ArrayList<String> Strings = new ArrayList<>();
        Strings.add("Total Imports");
        Strings.add("Total Exports");
        Strings.add("Yearly Inflation");
        // TODO code application logic here
        MakeFact("Ghana", Strings);
    }
}
