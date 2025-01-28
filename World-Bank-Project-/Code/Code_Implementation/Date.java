/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.worldbankia;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author twumn
 */
public class Date {

    public static void main(String[] args) {
        
        String userTimeZone = "America/New_York";

        ZoneId zoneId = ZoneId.of(userTimeZone);
        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        String formattedTime = currentTime.format(formatter);
        System.out.println("Current time in " + userTimeZone + ": " + formattedTime);
    }
}
  
