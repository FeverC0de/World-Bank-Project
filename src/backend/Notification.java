/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.worldbankia;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import static com.mycompany.worldbankia.FactGenerator.MakeFact;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author twumn
 */
public class Notification {
    
        private Timer timer;
    /**
     * @param country
     * @param args the command line arguments
     * @throws java.awt.AWTException
     * @throws java.io.IOException
     * @throws com.maxmind.geoip2.exception.GeoIp2Exception
     */
    public static void main(String country ,ArrayList<String> args) throws AWTException, IOException, GeoIp2Exception {
        
    
        if (SystemTray.isSupported()) {
        Notification td = new Notification();
        td.timeNotification(MakeFact( country ,args), 10000);
        } else {
            System.err.println("System tray not supported!");
        }
    }
    
    
    public void timeNotification(String text, int time){
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                try{
                    displayTray(text);
                }
                catch (AWTException e){
                    
                }
            }
        }, time);
        
    }
    public void cancelNotification() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    public void displayTray(String text) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Information", text, MessageType.INFO);
    }
}
