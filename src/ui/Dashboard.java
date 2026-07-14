/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.worldbankia;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.mycompany.worldbankia.ChangeCountry.ChangeCountryCallback;
import static com.mycompany.worldbankia.DataBase.ClearTable;
import static com.mycompany.worldbankia.DataBase.DataString;
import static com.mycompany.worldbankia.DataBase.GetCapital;
import static com.mycompany.worldbankia.DataBase.GetData;
import static com.mycompany.worldbankia.DataBase.UndoTable;
import static com.mycompany.worldbankia.FactGenerator.MakeFact;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import static java.awt.Color.GRAY;
import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;
import java.util.Random;
import javax.swing.border.LineBorder; 

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

/**
 *
 * @author twumn
 */
public class Dashboard extends javax.swing.JFrame implements ChangeCountryCallback {
    private String country = GetLocation.data();
    private String ncountry;
    public String NotifCountry;
    private boolean detectcountry  = true;
    private boolean detectedcountry;
    public boolean setNotif;
    public boolean tableused = false;
    private int selectedyear;
    private Timer animationTimer;
    
    private  boolean isDrawerOpen = true;

    private Timer drawerTimer = null;
    private int drawerX;
    private Color originalTextColor;
    private ImageIcon originalIconD;
    private ImageIcon hoverIconD;
    private ImageIcon originalIconE;
    private ImageIcon hoverIconE;
    private ImageIcon originalIconN;
    private ImageIcon hoverIconN;
    private ImageIcon originalIconS;
    private ImageIcon hoverIconS;
    private ImageIcon hoverIconM;
    private ImageIcon originalIconM;
    private ImageIcon originalIconC;
    private ImageIcon hoverIconC;
    private ImageIcon hoverIconCC;
    private Popup tooltipPopup = null;
    public Notification td;
    public int notiftimer;

    public static boolean changecountry;

    
    
    public Dashboard() throws IOException, GeoIp2Exception {
       
       
        ArrayList<String> Strings = new ArrayList<>();
        Strings.add("Total Imports");
        Strings.add("Total Exports");
        Strings.add("Yearly Inflation");
        
        
    

        
        
        
    
        
        
        initComponents();
        originalTextColor = DL.getForeground();

        
        scaleIcon(DashboardL, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-home-500(1).png");
        scaleIcon(SettingsL, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-exit-100(1).png");
        scaleIcon(NotificationsL, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-notification-50(1).png");
        scaleIcon(EconomyL, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-economy-100(1).png");
        scaleIcon(CapII, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-capital-64.png");
        scaleIcon(Capl, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-country-100.png");
        scaleIcon(PopI, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-population-64.png");
        scaleIcon(InflationI, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-inflation-64.png");
        scaleIcon(GDPI, "C:\\Users\\twumn\\OneDrive\\Desktop\\School\\WORLDBANKIA\\src\\main\\java\\img\\icons8-currency-100.png");
      


        
        drawerTimer = new Timer(10, (ActionEvent e) -> {
        int step = 10; // Adjust this value for animation speed
        int stepB = 10;

        if (isDrawerOpen) {
            if (drawerX > -SlideMenu.getWidth()) {
                drawerX -= step;
                MenuButton.setLocation(MenuButton.getX() - step, MenuButton.getY());
                jPanel3.setLocation(jPanel3.getX() - step, jPanel3.getY());
            } else {
                drawerTimer.stop(); // Animation finished
            }
        } else {
            if (drawerX < 0) {
                drawerX += stepB;
                MenuButton.setLocation(MenuButton.getX() + stepB, MenuButton.getY());
                jPanel3.setLocation(jPanel3.getX() + stepB, jPanel3.getY());

            } else {
                drawerTimer.stop(); // Animation finished
                SlideMenu.setVisible(!isDrawerOpen);
            }
        }

        SlideMenu.setLocation(drawerX, SlideMenu.getY());
    });
        
    drawerX = -SlideMenu.getWidth();
    SlideMenu.setLocation(drawerX, SlideMenu.getY());
    SlideMenu.setVisible(!isDrawerOpen); // Initial visibility set to false
    MenuButton.setVisible(true); // Make the button initially visible
    
    //tool tip customization
    
    checkCountry();
    
    
    

}

    

   
    @SuppressWarnings("unchecked")
    
public static void scaleIcon(JLabel label, String link){
    ImageIcon icon = new ImageIcon(link);

    Image img = icon.getImage();
    Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(imgScale);    
    label.setIcon(scaledIcon);

}

public static void scaleIconB(JButton button, String link){
    ImageIcon icon = new ImageIcon(link);

    Image img = icon.getImage();
    Image imgScale = img.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(imgScale);    
    button.setIcon(scaledIcon);
    
}
private ImageIcon changeIconColor(ImageIcon originalIcon, Color newColor) {

    Image originalImage = originalIcon.getImage();
    BufferedImage bufferedImage = new BufferedImage(originalImage.getWidth(null), 
            originalImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = bufferedImage.createGraphics();
    graphics.drawImage(originalImage, 0, 0, null);

    graphics.setComposite(AlphaComposite.SrcAtop);
    graphics.setColor(newColor);
    graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
    graphics.dispose();

    return new ImageIcon(bufferedImage);
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        generate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        SlideMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        SettingsL = new javax.swing.JLabel();
        EL = new javax.swing.JLabel();
        NL = new javax.swing.JLabel();
        SL = new javax.swing.JLabel();
        DL = new javax.swing.JLabel();
        DashboardL = new javax.swing.JLabel();
        EconomyL = new javax.swing.JLabel();
        NotificationsL = new javax.swing.JLabel();
        SettingsL1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        DashBP = new javax.swing.JPanel();
        DashP = new javax.swing.JPanel();
        DL1 = new javax.swing.JLabel();
        PopVar = new javax.swing.JLabel();
        CountryLabel = new javax.swing.JLabel();
        DL4 = new javax.swing.JLabel();
        DL5 = new javax.swing.JLabel();
        InfVar = new javax.swing.JLabel();
        CapVar = new javax.swing.JLabel();
        DL8 = new javax.swing.JLabel();
        GDPVar = new javax.swing.JLabel();
        DL11 = new javax.swing.JLabel();
        InflationI = new javax.swing.JLabel();
        Capl = new javax.swing.JLabel();
        CapII = new javax.swing.JLabel();
        PopI = new javax.swing.JLabel();
        DashboardL5 = new javax.swing.JLabel();
        GDPI = new javax.swing.JLabel();
        CapI1 = new javax.swing.JLabel();
        SetP = new javax.swing.JPanel();
        NotifP = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        EL1 = new javax.swing.JLabel();
        EL2 = new javax.swing.JLabel();
        EL3 = new javax.swing.JLabel();
        OnNotifications = new com.mycompany.worldbankia.JCheckBoxCustom();
        OnLocalized = new com.mycompany.worldbankia.JCheckBoxCustom();
        EL4 = new javax.swing.JLabel();
        CountryBox = new com.mycompany.worldbankia.ComboBox();
        NotifBox = new com.mycompany.worldbankia.ComboBox();
        EL5 = new javax.swing.JLabel();
        EconP = new javax.swing.JPanel();
        year = new com.mycompany.worldbankia.ComboBoxSuggestion();
        Indicators = new com.mycompany.worldbankia.ComboBoxSuggestion();
        Output = new javax.swing.JLabel();
        Output1 = new javax.swing.JLabel();
        Output2 = new javax.swing.JLabel();
        Output3 = new javax.swing.JLabel();
        Indicators2 = new com.mycompany.worldbankia.ComboBoxSuggestion();
        Output4 = new javax.swing.JLabel();
        Output5 = new javax.swing.JLabel();
        Output6 = new javax.swing.JLabel();
        Year1 = new com.mycompany.worldbankia.ComboBoxSuggestion();
        year2 = new com.mycompany.worldbankia.ComboBoxSuggestion();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        MenuButton = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        SL1 = new javax.swing.JLabel();
        SL2 = new javax.swing.JLabel();
        SL3 = new javax.swing.JLabel();
        MenuB = new javax.swing.JLabel();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("New Country Detected! Would you like to change location to " + ncountry);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel2)
                .addContainerGap(489, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel2)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel11MouseExited(evt);
            }
        });

        jLabel7.setText("Button");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        generate.setText("Add Data");
        generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateActionPerformed(evt);
            }
        });

        jButton1.setText("Undo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ClearTable");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Generate Line Chart");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Generate Bar Chart");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setText("Download Line Chart");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setText("Download Bar Chart");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(102, 0, 100));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("World Bank Database");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(428, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(335, 335, 335))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, "card4");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SlideMenu.setBackground(new java.awt.Color(29, 43, 75));
        SlideMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(102, 0, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        SettingsL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        SettingsL.setForeground(new java.awt.Color(119, 129, 149));
        SettingsL.setText("     ");

        EL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        EL.setForeground(new java.awt.Color(119, 129, 149));
        EL.setText("   Economic Data");
        EL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ELMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ELMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ELMouseExited(evt);
            }
        });

        NL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        NL.setForeground(new java.awt.Color(119, 129, 149));
        NL.setText("   Notifications");
        NL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NLMouseExited(evt);
            }
        });

        SL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        SL.setForeground(new java.awt.Color(119, 129, 149));
        SL.setText("Exit");
        SL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SLMouseExited(evt);
            }
        });

        DL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        DL.setForeground(new java.awt.Color(119, 129, 149));
        DL.setText("   Dashboard");
        DL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DLMouseExited(evt);
            }
        });

        DashboardL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        DashboardL.setForeground(new java.awt.Color(119, 129, 149));
        DashboardL.setText("     ");

        EconomyL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        EconomyL.setForeground(new java.awt.Color(119, 129, 149));
        EconomyL.setText("     ");

        NotificationsL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        NotificationsL.setForeground(new java.awt.Color(119, 129, 149));
        NotificationsL.setText("     ");

        SettingsL1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        SettingsL1.setForeground(new java.awt.Color(119, 129, 149));
        SettingsL1.setText("     ");

        javax.swing.GroupLayout SlideMenuLayout = new javax.swing.GroupLayout(SlideMenu);
        SlideMenu.setLayout(SlideMenuLayout);
        SlideMenuLayout.setHorizontalGroup(
            SlideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SlideMenuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(SlideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EconomyL, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(SettingsL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NotificationsL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DashboardL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SettingsL1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(SlideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NL)
                    .addComponent(EL)
                    .addComponent(DL, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SlideMenuLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(SL)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        SlideMenuLayout.setVerticalGroup(
            SlideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SlideMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addGroup(SlideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DashboardL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(SlideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EconomyL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(SlideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NotificationsL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(SlideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SettingsL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SettingsL1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel3.setLayout(new java.awt.CardLayout());

        DashP.setBackground(new java.awt.Color(255, 255, 255));

        DL1.setBackground(new java.awt.Color(0, 0, 0));
        DL1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        DL1.setForeground(new java.awt.Color(51, 0, 0));
        DL1.setText("Country");
        DL1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DL1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DL1MouseExited(evt);
            }
        });

        PopVar.setBackground(new java.awt.Color(0, 0, 0));
        PopVar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        PopVar.setForeground(new java.awt.Color(51, 0, 0));
        PopVar.setText("Population");
        PopVar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PopVarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PopVarMouseExited(evt);
            }
        });

        CountryLabel.setBackground(new java.awt.Color(0, 0, 0));
        CountryLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        CountryLabel.setForeground(new java.awt.Color(51, 0, 0));
        CountryLabel.setText("Country");
        CountryLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CountryLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CountryLabelMouseExited(evt);
            }
        });

        DL4.setBackground(new java.awt.Color(0, 0, 0));
        DL4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        DL4.setForeground(new java.awt.Color(51, 0, 0));
        DL4.setText("Population");
        DL4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DL4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DL4MouseExited(evt);
            }
        });

        DL5.setBackground(new java.awt.Color(0, 0, 0));
        DL5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        DL5.setForeground(new java.awt.Color(51, 0, 0));
        DL5.setText("Inflation");
        DL5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DL5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DL5MouseExited(evt);
            }
        });

        InfVar.setBackground(new java.awt.Color(0, 0, 0));
        InfVar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        InfVar.setForeground(new java.awt.Color(51, 0, 0));
        InfVar.setText("Inflation");
        InfVar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InfVarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InfVarMouseExited(evt);
            }
        });

        CapVar.setBackground(new java.awt.Color(0, 0, 0));
        CapVar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        CapVar.setForeground(new java.awt.Color(51, 0, 0));
        CapVar.setText("Capital");
        CapVar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CapVarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CapVarMouseExited(evt);
            }
        });

        DL8.setBackground(new java.awt.Color(0, 0, 0));
        DL8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        DL8.setForeground(new java.awt.Color(51, 0, 0));
        DL8.setText("Capital");
        DL8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DL8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DL8MouseExited(evt);
            }
        });

        GDPVar.setBackground(new java.awt.Color(0, 0, 0));
        GDPVar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        GDPVar.setForeground(new java.awt.Color(51, 0, 0));
        GDPVar.setText("GDP");
        GDPVar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GDPVarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GDPVarMouseExited(evt);
            }
        });

        DL11.setBackground(new java.awt.Color(0, 0, 0));
        DL11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        DL11.setForeground(new java.awt.Color(51, 0, 0));
        DL11.setText("GDP");
        DL11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DL11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DL11MouseExited(evt);
            }
        });

        InflationI.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        InflationI.setForeground(new java.awt.Color(119, 129, 149));
        InflationI.setText("     ");

        Capl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        Capl.setForeground(new java.awt.Color(119, 129, 149));
        Capl.setText("     ");
        Capl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CaplMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CaplMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CaplMouseExited(evt);
            }
        });

        CapII.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        CapII.setForeground(new java.awt.Color(119, 129, 149));
        CapII.setText("     ");

        PopI.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        PopI.setForeground(new java.awt.Color(119, 129, 149));
        PopI.setText("     ");

        DashboardL5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        DashboardL5.setForeground(new java.awt.Color(119, 129, 149));
        DashboardL5.setText("     ");

        GDPI.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        GDPI.setForeground(new java.awt.Color(119, 129, 149));
        GDPI.setText("     ");

        CapI1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        CapI1.setForeground(new java.awt.Color(119, 129, 149));
        CapI1.setText("     ");

        javax.swing.GroupLayout DashPLayout = new javax.swing.GroupLayout(DashP);
        DashP.setLayout(DashPLayout);
        DashPLayout.setHorizontalGroup(
            DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashPLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(InflationI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PopI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Capl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CapI1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DL1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PopVar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CountryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InfVar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DL5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DL4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GDPI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CapII, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DashboardL5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CapVar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DL8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DL11, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GDPVar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        DashPLayout.setVerticalGroup(
            DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashPLayout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashPLayout.createSequentialGroup()
                        .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DL8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CapII, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DashPLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(GDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(DashPLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(CapVar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(105, 105, 105)
                        .addComponent(DashboardL5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DashPLayout.createSequentialGroup()
                        .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DL1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Capl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DashPLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(CountryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CapI1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(DashPLayout.createSequentialGroup()
                                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(DashPLayout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(DL4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(PopI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(15, 15, 15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashPLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DL11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PopVar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GDPVar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(DashPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(DL5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InflationI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(15, 15, 15)
                        .addComponent(InfVar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout DashBPLayout = new javax.swing.GroupLayout(DashBP);
        DashBP.setLayout(DashBPLayout);
        DashBPLayout.setHorizontalGroup(
            DashBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DashP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DashBPLayout.setVerticalGroup(
            DashBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DashP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.add(DashBP, "card3");

        SetP.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout SetPLayout = new javax.swing.GroupLayout(SetP);
        SetP.setLayout(SetPLayout);
        SetPLayout.setHorizontalGroup(
            SetPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 838, Short.MAX_VALUE)
        );
        SetPLayout.setVerticalGroup(
            SetPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        jPanel3.add(SetP, "card5");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        EL1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        EL1.setForeground(new java.awt.Color(119, 129, 149));
        EL1.setText("Localize country");
        EL1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EL1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EL1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EL1MouseExited(evt);
            }
        });

        EL2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        EL2.setForeground(new java.awt.Color(119, 129, 149));
        EL2.setText("Enable Notifications");
        EL2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EL2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EL2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EL2MouseExited(evt);
            }
        });

        EL3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        EL3.setForeground(new java.awt.Color(119, 129, 149));
        EL3.setText("Notifications Timer");
        EL3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EL3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EL3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EL3MouseExited(evt);
            }
        });

        OnNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnNotificationsActionPerformed(evt);
            }
        });

        OnLocalized.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnLocalizedActionPerformed(evt);
            }
        });

        EL4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        EL4.setForeground(new java.awt.Color(119, 129, 149));
        EL4.setText("Hour");
        EL4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EL4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EL4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EL4MouseExited(evt);
            }
        });

        CountryBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia, Plurinational State of", "Bonaire, Sint Eustatius and Saba", "Bosnia and Herzegovina", "Botswana", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "CuraÃ§ao", "Cyprus", "Czech Republic", "CÃ´te d'Ivoire", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran, Islamic Republic of", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia, the Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestine, State of", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Romania", "Russian Federation", "Rwanda", "RÃ©union", "Saint BarthÃ©lemy", "Saint Helena, Ascension and Tristan da Cunha", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin (French part)", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Maarten (Dutch part)", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela, Bolivarian Republic of", "Viet Nam", "Virgin Islands, British", "Virgin Islands, U.S.", "Wallis and Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe", " " }));
        CountryBox.setSelectedIndex(-1);
        CountryBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CountryBoxActionPerformed(evt);
            }
        });

        NotifBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", " " }));
        NotifBox.setSelectedIndex(-1);
        NotifBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifBoxActionPerformed(evt);
            }
        });

        EL5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        EL5.setForeground(new java.awt.Color(119, 129, 149));
        EL5.setText("Set Country");
        EL5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EL5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EL5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EL5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(EL3)
                        .addComponent(EL2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EL1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(EL5))
                .addGap(62, 62, 62)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(OnNotifications, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OnLocalized, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CountryBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(NotifBox, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EL4)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(EL2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(OnNotifications, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(EL1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(OnLocalized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(EL4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EL5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CountryBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EL3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NotifBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(167, 167, 167))
        );

        javax.swing.GroupLayout NotifPLayout = new javax.swing.GroupLayout(NotifP);
        NotifP.setLayout(NotifPLayout);
        NotifPLayout.setHorizontalGroup(
            NotifPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        NotifPLayout.setVerticalGroup(
            NotifPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.add(NotifP, "card4");

        EconP.setBackground(new java.awt.Color(255, 255, 255));

        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008" }));
        year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearActionPerformed(evt);
            }
        });

        Indicators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Total Population", "Yearly Inflation", "GDP per capita", "Total Exports", "Total Imports", "Maternal mortality per 100000 births", "Tax revenue as a percent of GDP", "Percentage of People who have access to basic sanitation services", "Urban Population Growth", "Percentage of Labor Force With Basic Education" }));
        Indicators.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IndicatorsActionPerformed(evt);
            }
        });

        Output.setBackground(new java.awt.Color(204, 255, 255));
        Output.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Output.setForeground(new java.awt.Color(51, 0, 0));
        Output.setText("Output");
        Output.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OutputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutputMouseExited(evt);
            }
        });

        Output1.setBackground(new java.awt.Color(0, 0, 0));
        Output1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Output1.setForeground(new java.awt.Color(51, 0, 0));
        Output1.setText("Select end Year");
        Output1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Output1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Output1MouseExited(evt);
            }
        });

        Output2.setBackground(new java.awt.Color(0, 0, 0));
        Output2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Output2.setForeground(new java.awt.Color(51, 0, 0));
        Output2.setText("Select Indicator");
        Output2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Output2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Output2MouseExited(evt);
            }
        });

        Output3.setBackground(new java.awt.Color(204, 255, 255));
        Output3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Output3.setForeground(new java.awt.Color(51, 0, 0));
        Output3.setText("Table and Graph");
        Output3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Output3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Output3MouseExited(evt);
            }
        });

        Indicators2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Total Population", "Yearly Inflation", "GDP per capita", "Total Exports", "Total Imports", "Tax revenue as a percent of GDP", "Percentage of People who have access to basic sanitation services", "Urban Population Growth", "Percentage of Labor Force With Basic Education" }));
        Indicators2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Indicators2ActionPerformed(evt);
            }
        });

        Output4.setBackground(new java.awt.Color(0, 0, 0));
        Output4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Output4.setForeground(new java.awt.Color(51, 0, 0));
        Output4.setText("Select Indicator");
        Output4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Output4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Output4MouseExited(evt);
            }
        });

        Output5.setBackground(new java.awt.Color(0, 0, 0));
        Output5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Output5.setForeground(new java.awt.Color(51, 0, 0));
        Output5.setText("Select Year");
        Output5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Output5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Output5MouseExited(evt);
            }
        });

        Output6.setBackground(new java.awt.Color(0, 0, 0));
        Output6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Output6.setForeground(new java.awt.Color(51, 0, 0));
        Output6.setText("Select Start Year");
        Output6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Output6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Output6MouseExited(evt);
            }
        });

        Year1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008" }));
        Year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Year1ActionPerformed(evt);
            }
        });

        year2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008" }));
        year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                year2ActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTable);

        jLabel3.setText("jLabel3");

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(new LineBorder(new Color(206, 212, 218)));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel16MouseExited(evt);
            }
        });

        jLabel12.setText("Add Data");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(new LineBorder(new Color(206, 212, 218)));
        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel17MouseExited(evt);
            }
        });

        jLabel13.setText("Undo");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jPanel18.setBorder(new LineBorder(new Color(206, 212, 218)));
        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel18MouseExited(evt);
            }
        });

        jLabel14.setText("Clear");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel19.setBorder(new LineBorder(new Color(206, 212, 218)));
        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel19MouseExited(evt);
            }
        });

        jLabel15.setText("Generate Line Chart");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(new LineBorder(new Color(206, 212, 218)));
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel20MouseExited(evt);
            }
        });

        jLabel16.setText("Generate Bar Chart");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(new LineBorder(new Color(206, 212, 218))
        );
        jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel24MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel24MouseExited(evt);
            }
        });

        jLabel20.setText("Download Line Chart");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(14, 14, 14))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(new LineBorder(new Color(206, 212, 218)));
        jPanel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel25MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel25MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel25MouseExited(evt);
            }
        });

        jLabel21.setText("Download Bar Chart");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(14, 14, 14))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(new LineBorder(new Color(206, 212, 218)));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel21MouseExited(evt);
            }
        });

        jLabel17.setText("Change Country");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        MenuButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        MenuButton.setForeground(new java.awt.Color(119, 129, 149));
        MenuButton.setText("     ");
        MenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MenuButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MenuButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout EconPLayout = new javax.swing.GroupLayout(EconP);
        EconP.setLayout(EconPLayout);
        EconPLayout.setHorizontalGroup(
            EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EconPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EconPLayout.createSequentialGroup()
                        .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EconPLayout.createSequentialGroup()
                                .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Output5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Output2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Output6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Output4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Indicators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(EconPLayout.createSequentialGroup()
                                        .addGap(170, 170, 170)
                                        .addComponent(Output1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addGroup(EconPLayout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(Output3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(EconPLayout.createSequentialGroup()
                                        .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(year2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Year1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(48, 48, 48)
                                        .addComponent(Output, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Indicators2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1))
                        .addGap(42, 42, 42))
                    .addGroup(EconPLayout.createSequentialGroup()
                        .addComponent(MenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(EconPLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        EconPLayout.setVerticalGroup(
            EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EconPLayout.createSequentialGroup()
                .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EconPLayout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addComponent(MenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Indicators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Output4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Output, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Output5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Year1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(Output3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Indicators2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Output2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EconPLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EconPLayout.createSequentialGroup()
                        .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(year2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Output6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Output1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EconPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EconPLayout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3.add(EconP, "card5");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        SL1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        SL1.setForeground(new java.awt.Color(119, 129, 149));
        SL1.setText("Keep logged in");
        SL1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SL1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SL1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SL1MouseExited(evt);
            }
        });

        SL2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        SL2.setForeground(new java.awt.Color(119, 129, 149));
        SL2.setText("Open on start");
        SL2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SL2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SL2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SL2MouseExited(evt);
            }
        });

        SL3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        SL3.setForeground(new java.awt.Color(119, 129, 149));
        SL3.setText("Sign out");
        SL3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SL3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SL3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SL3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SL1)
                    .addComponent(SL3))
                .addContainerGap(606, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(SL2)
                    .addContainerGap(615, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(SL1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(SL3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(126, 126, 126)
                    .addComponent(SL2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(474, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel9, "card6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(SlideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SlideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(52, 52, 52))))
        );

        getContentPane().add(jPanel2, "card3");
        getContentPane().add(MenuB, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void DLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DLMouseEntered
        DL.setForeground(Color.WHITE); 
        DashboardL.setForeground(Color.WHITE); 
        originalIconD = (ImageIcon) DashboardL.getIcon(); // Store the original icon
        hoverIconD = changeIconColor(originalIconD, Color.WHITE); // Create a new icon with the desired color
        DashboardL.setIcon(hoverIconD);

            }//GEN-LAST:event_DLMouseEntered

    private void DLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DLMouseExited
        DL.setForeground(originalTextColor); 
        DashboardL.setForeground(Color.WHITE); 
        DashboardL.setIcon(originalIconD);

              }//GEN-LAST:event_DLMouseExited

    private void ELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ELMouseClicked
         EconP.setVisible(true);
        NotifP.setVisible(false);
        DashBP.setVisible(false);
        SetP.setVisible(false);        
        detectcountry = false;
        
    }//GEN-LAST:event_ELMouseClicked

    private void ELMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ELMouseEntered
        // TODO add your handling code here:
         EL.setForeground(Color.WHITE); 
        EconomyL.setForeground(Color.WHITE); 
        originalIconE = (ImageIcon) EconomyL.getIcon(); // Store the original icon
        hoverIconE = changeIconColor(originalIconE, Color.WHITE); // Create a new icon with the desired color
        EconomyL.setIcon(hoverIconE);
        
        
    
    }//GEN-LAST:event_ELMouseEntered

    private void ELMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ELMouseExited
        EL.setForeground(originalTextColor); 
        EconomyL.setForeground(Color.WHITE); 
        EconomyL.setIcon(originalIconE);    }//GEN-LAST:event_ELMouseExited

    private void NLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NLMouseEntered
        // TODO add your handling code here:
        NL.setForeground(Color.WHITE); 
        NotificationsL.setForeground(Color.WHITE); 
        originalIconN = (ImageIcon) NotificationsL.getIcon(); // Store the original icon
        hoverIconN = changeIconColor(originalIconN, Color.WHITE); // Create a new icon with the desired color
        NotificationsL.setIcon(hoverIconN);
        
        
        
    }//GEN-LAST:event_NLMouseEntered

    private void NLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NLMouseExited
        // TODO add your handling code here:
        NL.setForeground(originalTextColor); 
        NotificationsL.setForeground(Color.WHITE); 
        NotificationsL.setIcon(originalIconN);
        
    }//GEN-LAST:event_NLMouseExited

    private void DL11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL11MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DL11MouseExited

    private void DL11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DL11MouseEntered

    private void GDPVarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GDPVarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_GDPVarMouseExited

    private void GDPVarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GDPVarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_GDPVarMouseEntered

    private void DL8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DL8MouseExited

    private void DL8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DL8MouseEntered

    private void CapVarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CapVarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_CapVarMouseExited

    private void CapVarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CapVarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_CapVarMouseEntered

    private void InfVarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InfVarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InfVarMouseExited

    private void InfVarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InfVarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InfVarMouseEntered

    private void DL5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DL5MouseExited

    private void DL5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DL5MouseEntered

    private void DL4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DL4MouseExited

    private void DL4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DL4MouseEntered

    private void CountryLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CountryLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_CountryLabelMouseExited

    private void CountryLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CountryLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_CountryLabelMouseEntered

    private void PopVarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopVarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PopVarMouseExited

    private void PopVarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopVarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PopVarMouseEntered

    private void DL1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DL1MouseExited

    private void DL1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DL1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DL1MouseEntered

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            // TODO add your handling code here:
            
            
            setText();
        } catch (GeoIp2Exception ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        

        jPanel4.setVisible(false);
        jPanel2.setVisible(true);
        MenuButton.dispatchEvent(new MouseEvent(MenuButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), InputEvent.BUTTON1_DOWN_MASK, 0, 0, 1, false));
        

    }//GEN-LAST:event_formWindowOpened

    private void NLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NLMouseClicked
        EconP.setVisible(false);
        NotifP.setVisible(true);
        DashBP.setVisible(false);
        SetP.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_NLMouseClicked

    private void DLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DLMouseClicked
        // TODO add your handling code here:
        EconP.setVisible(false);
        NotifP.setVisible(false);
        DashBP.setVisible(true);
        SetP.setVisible(false);
    }//GEN-LAST:event_DLMouseClicked

    private void CaplMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CaplMouseEntered
        ToolTipManager.sharedInstance().setReshowDelay(100);

        originalIconC = (ImageIcon) Capl.getIcon(); // Store the original icon

        hoverIconCC = changeIconColor(originalIconC, Color.GRAY); // Create a new icon with the desired color
        Capl.setIcon(hoverIconCC);
        
        
        if(detectcountry == true){
        showCustomToolTip(Capl, evt.getXOnScreen(), evt.getYOnScreen() + 20, "Change country?");
        
     
        }
        detectedcountry = false;
        if(detectcountry == false){
        showCustomToolTip(Capl, evt.getXOnScreen(), evt.getYOnScreen() + 20, "New Country Detected!");
        
     
        }
    
        
        
        
    }//GEN-LAST:event_CaplMouseEntered

    private void CaplMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CaplMouseExited
        // TODO add your handling code here:
        
        
        if(detectedcountry == true){
            Capl.setIcon(changeIconColor(originalIconC, Color.GREEN));
            
        }
        
        else{
            
                Capl.setIcon(originalIconC);
}
        if (tooltipPopup != null) {
                   tooltipPopup.hide(); // Hide the tooltip when mouse exits the component
                   tooltipPopup = null;
               }
        
    }//GEN-LAST:event_CaplMouseExited

    private void CaplMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CaplMouseClicked
        // TODO add your handling code here:
        if(detectedcountry){
            try{
                String ncountry = GetLocation.data();
                if(!country.equals(ncountry)){
                    int response = JOptionPane.showConfirmDialog(null, "Do you want to change country to " + ncountry + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if ( response == JOptionPane.YES_OPTION){


                    country = GetLocation.data();
                    detectcountry = true;
                    detectedcountry = false;
                    setText();

                    Capl.setIcon(originalIconC);

                    
                    }

                    else{
                        originalIconC = (ImageIcon) Capl.getIcon(); // Store the original icon

                        detectcountry = false;                            
                    }



                }
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeoIp2Exception ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else{
        
        ChangeCountry changeCountryFrame = new ChangeCountry(this);
        changeCountryFrame.setVisible(true);
        
        
        
        }
    }//GEN-LAST:event_CaplMouseClicked

    private void EL1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EL1MouseClicked

    private void EL1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_EL1MouseEntered

    private void EL1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_EL1MouseExited

    private void EL2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EL2MouseClicked

    private void EL2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_EL2MouseEntered

    private void EL2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_EL2MouseExited

    private void EL3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EL3MouseClicked

    private void EL3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_EL3MouseEntered

    private void EL3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_EL3MouseExited

    private void SL1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SL1MouseClicked

    private void SL1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SL1MouseEntered

    private void SL1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_SL1MouseExited

    private void SL2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SL2MouseClicked

    private void SL2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SL2MouseEntered

    private void SL2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_SL2MouseExited

    private void SL3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SL3MouseClicked

    private void SL3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SL3MouseEntered

    private void SL3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SL3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_SL3MouseExited

    private void yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearActionPerformed
    
           
    }//GEN-LAST:event_yearActionPerformed

    private void IndicatorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IndicatorsActionPerformed
             try {
            String year = Year1.getSelectedItem().toString();
            String series = Indicators.getSelectedItem().toString(); 
            Object output = GetData("Indicators", country, series, year);
            if (output instanceof Double){
                Double output1 = (Double) output;
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String sOutput = decimalFormat.format(output1);
                Output.setText(sOutput);
             
            }
           
            
            
            
//            Output.setText(output);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeoIp2Exception ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IndicatorsActionPerformed

    private void OutputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OutputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_OutputMouseEntered

    private void OutputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OutputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_OutputMouseExited

    private void Output1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Output1MouseEntered

    private void Output1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Output1MouseExited

    private void Output2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Output2MouseEntered

    private void Output2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Output2MouseExited

    private void Output3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Output3MouseEntered

    private void Output3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Output3MouseExited

    private void Indicators2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Indicators2ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_Indicators2ActionPerformed

    private void Output4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Output4MouseEntered

    private void Output4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Output4MouseExited

    private void Output5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Output5MouseEntered

    private void Output5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Output5MouseExited

    private void Output6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Output6MouseEntered

    private void Output6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Output6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Output6MouseExited

    private void Year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Year1ActionPerformed
        // TODO add your handling code here:
            // TODO add your handling code here:
          try {
            String year11 = Year1.getSelectedItem().toString();
            String series = Indicators.getSelectedItem().toString(); 
            Object output = GetData("Indicators", country, series, year11);
            if (output instanceof Double){
                Double output1 = (Double) output;
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String sOutput = decimalFormat.format(output1);
                Output.setText(sOutput);
             
            }
            
            
            
//            Output.setText(output);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeoIp2Exception ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Year1ActionPerformed

    private void year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_year2ActionPerformed

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
               
        try {
            String selectedStartYear = year2.getSelectedItem().toString();          // Obtains the Starting Year, from the combobox
            String selectedEndYear = year.getSelectedItem().toString();             // Obtains the End Year, from the combobox
            String selectedIndicator = Indicators2.getSelectedItem().toString();    // Obtains the Indicator, from the combobox
            String yearstr;                                                         // Initializes, the values for the year column
            int startyear = Integer.parseInt(selectedStartYear);                  // Convert the selected years into Integers to be iterated upon
            int endyear  =  Integer.parseInt(selectedEndYear);
            int i = 0;
            int yeardifference = endyear- startyear;                                //Used to handle errors with table generation
            boolean hasduplicate = false;                                           //Used to handle duplicate columns
            boolean endYearError = false;                                           //Used to handle Errors

            
            ArrayList<String> newColumnData = new ArrayList<>();                    //Creates an ArrayList which will be transformed into table colums
            String newColumnName = selectedIndicator + "(" + country +")";          //Creates the column name from the Selected Indicator
            
            
            DefaultTableModel tb1Model = (DefaultTableModel) jTable.getModel();     //Retrieves the current model of the table
            int numberOfColumns = tb1Model.getColumnCount();                        //Retrieves the columncount
            TableColumnModel columnModel = jTable.getColumnModel();                 //Retrieves the column model of the table
           
            
            //Error Handling with If Statement
            if(startyear > endyear){
                JOptionPane.showMessageDialog(null, "End year must be greater than the start year!");
            }
            
            
            //Error Handling with If Statement
            if(tb1Model.getRowCount() != yeardifference + 1 && tb1Model.getColumnCount() > 0){
                JOptionPane.showMessageDialog(null, "End Year Must be the Same, in order to add different indicators ");
                endYearError = true;
            }
            
            
            //Checks if indicator exists in table
            if(tb1Model.getColumnCount() > 0){
                for (int l = 0; l < columnModel.getColumnCount(); l++) {
                TableColumn column = columnModel.getColumn(l);
                String columnName = (String) column.getHeaderValue();
                    if(newColumnName.equals(columnName)){
                        JOptionPane.showMessageDialog(null, "Indicator Already Present In Table");
                        hasduplicate = true;

                    }
                }
                }
            
            //Checks if error states are activated
             if(!hasduplicate && !endYearError) {
                
                //Initializes Time column if it doesn't exist
                if (numberOfColumns == 0){
                tb1Model.addColumn("Time");
                selectedyear = endyear;
                //Initializes Row Count
                for (int rowIndex = 0; rowIndex <= yeardifference; rowIndex++) {
                    tb1Model.addRow(new Object[] {"", ""});
                    String newData = Integer.toString(endyear - i);
                    tb1Model.setValueAt(newData, rowIndex, tb1Model.getColumnCount()-1);
                    i = i + 1;
                }                
            }
                
                // Obtains the data, and  stores it in an array
                while( startyear <= endyear){
                    yearstr = Integer.toString(endyear);
                    String newData = DataString("Indicators", country, selectedIndicator, yearstr);
                    newColumnData.add(newData);
                    endyear = endyear - 1;
                }
                //Loops through the array list, and rows, and sets the corresponding index value of the rows as that of the array list
                tb1Model.addColumn(newColumnName);
                for (int rowIndex = 0; rowIndex < tb1Model.getRowCount(); rowIndex++) {
                    String newData = newColumnData.get(rowIndex);
                    tb1Model.setValueAt(newData, rowIndex, tb1Model.getColumnCount() - 1);
                }
            }
            hasduplicate = false;
            
        }catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeoIp2Exception ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_generateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        UndoTable(jTable);
        tableused = false;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ClearTable(jTable);
        tableused = false;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
//        int response = JOptionPane.showConfirmDialog(null, "Would you also like to save the chart?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        
//        if(response == JOptionPane.YES_OPTION){
//        
//       
//            DefaultCategoryDataset dataset = convertTableBar(jTable);
//
//            JFreeChart chart = ChartFactory.createBarChart(
//            "Data Bar Chart",
//            jTable.getColumnName(0), // Use the first column as the x-axis label
//            "Value",
//            dataset,
//            PlotOrientation.VERTICAL,
//            true, // include legend
//            true,
//            false);
//
//            System.out.println("Work");
//            ChartPanel chartPanel = new ChartPanel(chart);
//            JFrame popupFrame = new JFrame("Chart");
//            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the close operation as per your requirements
//            popupFrame.setSize(800, 600); // Set the size according to your preferences
//
//            // Add the ChartPanel to the popup frame
//            popupFrame.add(chartPanel);
//
//            // Make the frame visible
//            popupFrame.setVisible(true);
//            chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
//            
//            try {
//                ChartUtilities.saveChartAsPNG(new File("C:/Users/twumn/OneDrive/Desktop/School/WORLDBANKIA/src/main/java/Chart/chart.png"), chart, 800, 600);          } catch (IOException e) {
//                e.printStackTrace();
//        }
//            
//            
//        }
//        
//        else{

            DefaultCategoryDataset dataset = convertTableBar(jTable);

            JFreeChart chart = ChartFactory.createBarChart(
            "Data Bar Chart",
            jTable.getColumnName(0), // Use the first column as the x-axis label
            "Value",
            dataset,
            PlotOrientation.VERTICAL,
            true, // include legend
            true,
            false);

            System.out.println("Work");
            ChartPanel chartPanel = new ChartPanel(chart);
            JFrame popupFrame = new JFrame("Chart");
            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the close operation as per your requirements
            popupFrame.setSize(800, 600); // Set the size according to your preferences

            // Add the ChartPanel to the popup frame
            popupFrame.add(chartPanel);

            // Make the frame visible
            popupFrame.setVisible(true);            

        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     
         TimeSeriesCollection dataset = convertTableLine(jTable);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
        "Data Line Chart",
        jTable.getColumnName(0), // Use the first column as the x-axis label
        "Value",
        dataset,
        true, // include legend
        true,
        false);
        
        System.out.println("Work");
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame popupFrame = new JFrame("Chart");
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the close operation as per your requirements
        popupFrame.setSize(800, 600); // Set the size according to your preferences

        // Add the ChartPanel to the popup frame
        popupFrame.add(chartPanel);

        // Make the frame visible
        popupFrame.setLocationRelativeTo(null);

        // Print the location for debugging purposes
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

if (jTable.getColumnCount() >= 2) {
        DefaultCategoryDataset dataset = convertTableBar(jTable);

        JFreeChart chart = ChartFactory.createBarChart(
        "Data Bar Chart",
        jTable.getColumnName(0), // Use the first column as the x-axis label
        "Value",
        dataset,
        PlotOrientation.VERTICAL,
        true, // include legend
        true,
        false);

        boolean saved = false;

        while (!saved) {
            String fileName = JOptionPane.showInputDialog(null, "Enter The Name You Would Like To Save The:");

            if (fileName == null) {
                // User canceled the operation
                break;
            }

            if (fileName != null && !fileName.trim().isEmpty()) {
                // Ensure that the file name has the correct extension (e.g., .png)
                if (!fileName.endsWith(".png")) {
                    fileName += ".png";
                }

                // Create a file
                File file = new File("C:/Users/twumn/OneDrive/Desktop/School/WORLDBANKIA/src/main/java/Chart/" + fileName);

                if (file.exists()) {
                    // File with the same name already exists
                    int option = JOptionPane.showConfirmDialog(null, "File already exists. Do you want to rename it?", "File Exists", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        // User wants to rename the file
                        continue; // Continue the loop to prompt for a new name
                    } else {
                        // User chose not to rename, so cancel the operation
                        break;
                    }
                } else {
                    try {
                        ChartUtilities.saveChartAsPNG(file, chart, 800, 600);
                        saved = true; // Set the flag to indicate successful save
                    } catch (IOException ex) {
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Error Saving File");
                    }
                }
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Data Cannot be Converted to Chart");
    }

        
    
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
   if (jTable.getColumnCount() >= 2) {
        TimeSeriesCollection dataset = convertTableLine(jTable);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Data Line Chart",
            jTable.getColumnName(0), 
            "Value",
            dataset,
            true, 
            true,
            false);

        boolean saved = false;

        while (!saved) {
            String fileName = JOptionPane.showInputDialog(null, "Enter A Name To Save The Chart As");

            if (fileName == null) {
                // User canceled the operation
                break;
            }

            if (fileName != null && !fileName.trim().isEmpty()) {
                
                if (!fileName.endsWith(".png")) {
                    fileName += ".png";
                }
                // Create a file
                File file = new File("C:/Users/twumn/OneDrive/Desktop/School/WORLDBANKIA/src/main/java/Chart/" + fileName);

                if (file.exists()) {
                    // Checks if file with the same name already exists
                    int option = JOptionPane.showConfirmDialog(null, "File already exists. Do you want to rename it?", 
                            "File Exists", JOptionPane.YES_NO_OPTION);
                    

                    if (option == JOptionPane.YES_OPTION) {
                        // User wants to rename
                        continue; // Continue the loop to ask for a new name
                    } else {
                        // User chose not to rename, operation is cancelled.
                        break;
                    }
                } else {
                    try {
                        ChartUtilities.saveChartAsPNG(file, chart, 800, 600);
                        saved = true; // Sets saved state to true
                    } catch (IOException ex) {
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Error Saving File");
                    }
                }
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Data Cannot be Converted to Chart"); // If data is lacking the right number of columns, it fails to be converted to chart
    }

        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        ClickAnimation(jPanel11);
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        HoverEnter(jPanel11);

    }//GEN-LAST:event_jPanel11MouseEntered

    private void jPanel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseExited
        HoverExit(jPanel11);
    }//GEN-LAST:event_jPanel11MouseExited

    private void jPanel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseEntered
            HoverEnter(jPanel16);

    }//GEN-LAST:event_jPanel16MouseEntered

    private void jPanel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseExited
            HoverExit(jPanel16);
    }//GEN-LAST:event_jPanel16MouseExited

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        ClickAnimation(jPanel16);
                          
        try {
            String selectedStartYear = year2.getSelectedItem().toString();          // Obtains the Starting Year, from the combobox
            String selectedEndYear = year.getSelectedItem().toString();             // Obtains the End Year, from the combobox
            String selectedIndicator = Indicators2.getSelectedItem().toString();    // Obtains the Indicator, from the combobox
            String yearstr;                                                         // Initializes, the values for the year column
            int startyear = Integer.parseInt(selectedStartYear);                  // Convert the selected years into Integers to be iterated upon
            int endyear  =  Integer.parseInt(selectedEndYear);
            int i = 0;
            int yeardifference = endyear- startyear;                                //Used to handle errors with table generation
            boolean hasduplicate = false;                                           //Used to handle duplicate columns
            boolean endYearError = false;                                           //Used to handle Errors

            
            ArrayList<String> newColumnData = new ArrayList<>();                    //Creates an ArrayList which will be transformed into table colums
            String newColumnName = selectedIndicator + "(" + country +")";          //Creates the column name from the Selected Indicator
            
            
            DefaultTableModel tb1Model = (DefaultTableModel) jTable.getModel();     //Retrieves the current model of the table
            int numberOfColumns = tb1Model.getColumnCount();                        //Retrieves the columncount
            TableColumnModel columnModel = jTable.getColumnModel();                 //Retrieves the column model of the table
           
            
            //Error Handling with If Statement
            if(startyear > endyear){
                JOptionPane.showMessageDialog(null, "End year must be greater than the start year!");
            }
            
            
            //Error Handling with If Statement
            if(tb1Model.getRowCount() != yeardifference + 1 && tb1Model.getColumnCount() > 0){
                JOptionPane.showMessageDialog(null, "End Year Must be the Same, in order to add different indicators ");
                endYearError = true;
            }
            
            
            //Checks if indicator exists in table
            if(tb1Model.getColumnCount() > 0){
                for (int l = 0; l < columnModel.getColumnCount(); l++) {
                TableColumn column = columnModel.getColumn(l);
                String columnName = (String) column.getHeaderValue();
                    if(newColumnName.equals(columnName)){
                        JOptionPane.showMessageDialog(null, "Indicator Already Present In Table");
                        hasduplicate = true;

                    }
                }
                }
            
            //Checks if error states are activated
             if(!hasduplicate && !endYearError) {
                
                //Initializes Time column if it doesn't exist
                if (numberOfColumns == 0){
                tb1Model.addColumn("Time");
                selectedyear = endyear;
                //Initializes Row Count
                for (int rowIndex = 0; rowIndex <= yeardifference; rowIndex++) {
                    tb1Model.addRow(new Object[] {"", ""});
                    String newData = Integer.toString(endyear - i);
                    tb1Model.setValueAt(newData, rowIndex, tb1Model.getColumnCount()-1);
                    i = i + 1;
                }                
            }
                
                // Obtains the data, and  stores it in an array
                while( startyear <= endyear){
                    yearstr = Integer.toString(endyear);
                    String newData = DataString("Indicators", country, selectedIndicator, yearstr);
                    newColumnData.add(newData);
                    endyear = endyear - 1;
                }
                //Loops through the array list, and rows, and sets the corresponding index value of the rows as that of the array list
                tb1Model.addColumn(newColumnName);
                for (int rowIndex = 0; rowIndex < tb1Model.getRowCount(); rowIndex++) {
                    String newData = newColumnData.get(rowIndex);
                    tb1Model.setValueAt(newData, rowIndex, tb1Model.getColumnCount() - 1);
                }
            }
            hasduplicate = false;
            
        }catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeoIp2Exception ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
      ClickAnimation(jPanel17);
       UndoTable(jTable);
       tableused = false;

    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseEntered
        // TODO add your handling code here:
        HoverEnter(jPanel17);
    }//GEN-LAST:event_jPanel17MouseEntered

    private void jPanel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseExited
        // TODO add your handling code here:
        HoverExit(jPanel17);
    }//GEN-LAST:event_jPanel17MouseExited

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        // TODO add your handling code here:
        
        
        ClickAnimation(jPanel18);
         ClearTable(jTable);
         tableused = false;
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseEntered
        // TODO add your handling code here:
        HoverEnter(jPanel18);
    }//GEN-LAST:event_jPanel18MouseEntered

    private void jPanel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseExited
        // TODO add your handling code here:
        HoverExit(jPanel18);
    }//GEN-LAST:event_jPanel18MouseExited

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
          NotifCountry = (String) CountryBox.getItemAt(33);
        System.out.println(NotifCountry);
        ClickAnimation(jPanel19);
        
         
         TimeSeriesCollection dataset = convertTableLine(jTable);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
        "Data Line Chart",
        jTable.getColumnName(0), 
        "Value",
        dataset,
        true, // include legend
        true,
        false);
        
        System.out.println("Work");
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame popupFrame = new JFrame("Chart");
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        popupFrame.setSize(800, 600); 

        popupFrame.add(chartPanel);

        popupFrame.setVisible(true);
        popupFrame.setLocationRelativeTo(null);        
        
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jPanel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseEntered
        // TODO add your handling code here:
        HoverEnter(jPanel19);
    }//GEN-LAST:event_jPanel19MouseEntered

    private void jPanel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseExited
        // TODO add your handling code here:
        HoverExit(jPanel19);
    }//GEN-LAST:event_jPanel19MouseExited

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        
        ClickAnimation(jPanel20);
        DefaultCategoryDataset dataset = convertTableBar(jTable);

          JFreeChart chart = ChartFactory.createBarChart(
          "Data Bar Chart",
          jTable.getColumnName(0), 
          "Value",
          dataset,
          PlotOrientation.VERTICAL,
          true, 
          true,
          false);

          System.out.println("Work");
          ChartPanel chartPanel = new ChartPanel(chart);
          JFrame popupFrame = new JFrame("Chart");
          popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          popupFrame.setSize(800, 600); // 

          popupFrame.add(chartPanel);

          popupFrame.setVisible(true);            
          popupFrame.setLocationRelativeTo(null);        





    }//GEN-LAST:event_jPanel20MouseClicked

    private void jPanel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseEntered
        // TODO add your handling code here:
        HoverEnter(jPanel20);
    }//GEN-LAST:event_jPanel20MouseEntered

    private void jPanel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseExited
        // TODO add your handling code here:
        HoverExit(jPanel20);
    }//GEN-LAST:event_jPanel20MouseExited

    private void jPanel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseClicked
        // TODO add your handling code here:
        
        ClickAnimation(jPanel24);
        if (jTable.getColumnCount() >= 2) {
        TimeSeriesCollection dataset = convertTableLine(jTable);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Data Line Chart",
            jTable.getColumnName(0), 
            "Value",
            dataset,
            true, 
            true,
            false);

        boolean saved = false;

        while (!saved) {
            String fileName = JOptionPane.showInputDialog(null, "Enter A Name To Save The Chart As");

            if (fileName == null) {
                // User canceled the operation
                break;
            }

            if (fileName != null && !fileName.trim().isEmpty()) {
                
                if (!fileName.endsWith(".png")) {
                    fileName += ".png";
                }
                // Create a file
                File file = new File("C:/Users/twumn/OneDrive/Desktop/School/WORLDBANKIA/src/main/java/Chart/" + fileName);

                if (file.exists()) {
                    // Checks if file with the same name already exists
                    int option = JOptionPane.showConfirmDialog(null, "File already exists. Do you want to rename it?", 
                            "File Exists", JOptionPane.YES_NO_OPTION);
                    

                    if (option == JOptionPane.YES_OPTION) {
                        // User wants to rename
                        continue; // Continue the loop to ask for a new name
                    } else {
                        // User chose not to rename, operation is cancelled.
                        break;
                    }
                } else {
                    try {
                        ChartUtilities.saveChartAsPNG(file, chart, 800, 600);
                        saved = true; // Sets saved state to true
                    } catch (IOException ex) {
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Error Saving File");
                    }
                }
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Data Cannot be Converted to Chart"); // If data is lacking the right number of columns, it fails to be converted to chart
    }

    }//GEN-LAST:event_jPanel24MouseClicked

    private void jPanel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseEntered
        // TODO add your handling code here:
        HoverEnter(jPanel24);
    }//GEN-LAST:event_jPanel24MouseEntered

    private void jPanel24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseExited
        // TODO add your handling code here:
        HoverExit(jPanel24);
    }//GEN-LAST:event_jPanel24MouseExited

    private void jPanel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MouseClicked
        // TODO add your handling code here:
        ClickAnimation(jPanel25);
        if (jTable.getColumnCount() >= 2) {
        DefaultCategoryDataset dataset = convertTableBar(jTable);

        JFreeChart chart = ChartFactory.createBarChart(
        "Data Bar Chart",
        jTable.getColumnName(0), // Use the first column as the x-axis label
        "Value",
        dataset,
        PlotOrientation.VERTICAL,
        true, // include legend
        true,
        false);

        boolean saved = false;

        while (!saved) {
            String fileName = JOptionPane.showInputDialog(null, "Enter The Name You Would Like To Save The:");

            if (fileName == null) {
                // User canceled the operation
                break;
            }

            if (fileName != null && !fileName.trim().isEmpty()) {
                // Ensure that the file name has the correct extension (e.g., .png)
                if (!fileName.endsWith(".png")) {
                    fileName += ".png";
                }

                // Create a file
                File file = new File("C:/Users/twumn/OneDrive/Desktop/School/WORLDBANKIA/src/main/java/Chart/" + fileName);

                if (file.exists()) {
                    // File with the same name already exists
                    int option = JOptionPane.showConfirmDialog(null, "File already exists. Do you want to rename it?", "File Exists", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        // User wants to rename the file
                        continue; // Continue the loop to prompt for a new name
                    } else {
                        // User chose not to rename, so cancel the operation
                        break;
                    }
                } else {
                    try {
                        ChartUtilities.saveChartAsPNG(file, chart, 800, 600);
                        saved = true; // Set the flag to indicate successful save
                    } catch (IOException ex) {
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Error Saving File");
                    }
                }
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Data Cannot be Converted to Chart");
    }

        
    
        
    }//GEN-LAST:event_jPanel25MouseClicked

    private void jPanel25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MouseEntered
        // TODO add your handling code here:
        HoverEnter(jPanel25);
    }//GEN-LAST:event_jPanel25MouseEntered

    private void jPanel25MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MouseExited
        // TODO add your handling code here:
        HoverExit(jPanel25);
    }//GEN-LAST:event_jPanel25MouseExited

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
        // TODO add your handling code here:
         
        ChangeCountry changeCountryFrame = new ChangeCountry(this);
        changeCountryFrame.setVisible(true);
        
        
    }//GEN-LAST:event_jPanel21MouseClicked

    private void jPanel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseEntered
        // TODO add your handling code here:
        HoverEnter(jPanel21);
    }//GEN-LAST:event_jPanel21MouseEntered

    private void jPanel21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseExited
        // TODO add your handling code here:
        HoverExit(jPanel21);
    }//GEN-LAST:event_jPanel21MouseExited

    private void EL4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EL4MouseClicked

    private void EL4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_EL4MouseEntered

    private void EL4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_EL4MouseExited

    private void OnNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnNotificationsActionPerformed
        // TODO add your handling code here:
        setNotif = true;
        Random random = new Random();
        int randomNumber = random.nextInt(160) + 1;
        
        NotifCountry = (String) CountryBox.getItemAt(randomNumber);
        
        ArrayList<String> Strings = new ArrayList<>();
        Strings.add("Total Imports");
        Strings.add("Total Exports");
        Strings.add("Yearly Inflation");
        
        String TimerNumber = (String) NotifBox.getSelectedItem();
        notiftimer =Integer.parseInt(TimerNumber)* 2000;  
        
        if (SystemTray.isSupported()) {
        Notification td = new Notification();
            try {
                td.timeNotification(MakeFact(NotifCountry,Strings), notiftimer );
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeoIp2Exception ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("System tray not supported!");
        }
            this.NotifCountry = NotifCountry;
                     
        
        
        
    }//GEN-LAST:event_OnNotificationsActionPerformed

    private void OnLocalizedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnLocalizedActionPerformed
        // TODO add your handling code here:
        

        
        if(OnNotifications.isSelected()){
            
                setNotif = true;
        Random random = new Random();
        int randomNumber = random.nextInt(160) + 1;
        
        NotifCountry = country;
        
        ArrayList<String> Strings = new ArrayList<>();
        Strings.add("Total Imports");
        Strings.add("Total Exports");
        Strings.add("Yearly Inflation");
        
        String TimerNumber = (String) NotifBox.getSelectedItem();
        notiftimer =Integer.parseInt(TimerNumber)* 2000;  
        
        if (SystemTray.isSupported()) {
        Notification td = new Notification();
            try {
                td.timeNotification(MakeFact(country,Strings), notiftimer );
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeoIp2Exception ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("System tray not supported!");
        }

            try {       
                this.country = GetLocation.data();
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeoIp2Exception ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            
        
    }
        else{
            JOptionPane.showMessageDialog(null, "Please make sure you have enabled notifications");
            OnLocalized.setSelected(false);
            
        }
        
        
    }//GEN-LAST:event_OnLocalizedActionPerformed

    private void NotifBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifBoxActionPerformed
    td.cancelNotification();

    if(OnNotifications.isSelected()){
        

            
            String TimerNumber = (String) NotifBox.getSelectedItem();
            int time=Integer.parseInt(TimerNumber);  
                
            
            
        
        }
        
        else{
        JOptionPane.showMessageDialog(null, "Please make sure you have enabled notifications");
           
        
        }
        
    }//GEN-LAST:event_NotifBoxActionPerformed

    private void EL5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EL5MouseClicked

    private void EL5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_EL5MouseEntered

    private void EL5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EL5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_EL5MouseExited

    private void CountryBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CountryBoxActionPerformed
        // TODO add your handling code here:
        if(OnLocalized.isSelected()){
            
        NotifCountry = (String) CountryBox.getSelectedItem();
        
        }
        
        else{
           
        JOptionPane.showMessageDialog(null, "You can't set a country if you have localized your location");
        }
        
        
    }//GEN-LAST:event_CountryBoxActionPerformed

    private void SLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SLMouseExited
        // TODO add your handling code here:
        SL.setForeground(originalTextColor);
        SettingsL.setForeground(Color.WHITE);
        SettingsL.setIcon(originalIconS);

    }//GEN-LAST:event_SLMouseExited

    private void SLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SLMouseEntered
        // TODO add your handling code here:

        SL.setForeground(Color.WHITE);
        SettingsL.setForeground(Color.WHITE);
        originalIconS = (ImageIcon) SettingsL.getIcon(); // Store the original icon
        hoverIconS = changeIconColor(originalIconS, Color.WHITE); // Create a new icon with the desired color
        SettingsL.setIcon(hoverIconS);

    }//GEN-LAST:event_SLMouseEntered

    private void SLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SLMouseClicked
     System.exit(0);
    }//GEN-LAST:event_SLMouseClicked

    private void MenuButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuButtonMouseExited
        // TODO add your handling code here:
        MenuButton.setForeground(Color.WHITE);
        MenuButton.setIcon(originalIconM);
    }//GEN-LAST:event_MenuButtonMouseExited

    private void MenuButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuButtonMouseEntered
        // TODO add your handling code here:

        originalIconM = (ImageIcon) MenuButton.getIcon(); // Store the original icon
        hoverIconM = changeIconColor(originalIconM, Color.WHITE); // Create a new icon with the desired color
        MenuButton.setIcon(hoverIconM);
    }//GEN-LAST:event_MenuButtonMouseEntered

    private void MenuButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuButtonMouseClicked
        if (!drawerTimer.isRunning()) {
            isDrawerOpen = !isDrawerOpen;
            drawerTimer.start();
        }
    }//GEN-LAST:event_MenuButtonMouseClicked
    
 
    
    public void setText() throws GeoIp2Exception, IOException{
        CountryLabel.setText(country);
        
        setLabel(PopVar, "Total Population","", "");
        
        setLabel(InfVar, "Yearly Inflation", "","%");
        
        setLabel(CapVar, "Total Population", "","");
        
        setLabel(GDPVar, "GDP per capita", "$","");         
            
        Object Capital = GetCapital(country);
        CapVar.setText(Capital.toString());
        
        jLabel3.setText(country);
        

    }
    
    public void checkCountry(){
        

        java.util.Timer timer = new java.util.Timer();
        int delay = 0;
        int period = 15000;
        if(detectcountry == true){
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(detectcountry == true){
                try{
                    String ncountry = GetLocation.data();
                    if(!country.equals(ncountry)){
                        int response = JOptionPane.showConfirmDialog(null, "Do you want to change country to " + ncountry + 
                                "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if ( response == JOptionPane.YES_OPTION){

                        detectedcountry = true;
                        country = GetLocation.data();
                        setText();
                        }

                        else{
                            originalIconC = (ImageIcon) Capl.getIcon();
                            hoverIconC = changeIconColor(originalIconC, Color.GREEN); 
                            Capl.setIcon(hoverIconC);
                            detectcountry = false;
                            detectedcountry = true;
                        }



                    }
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                } catch (GeoIp2Exception ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
        }, delay, period);
        }
        
}
    
        public void timeNotification(String text, int time){
        java.util.Timer timer = new java.util.Timer();
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
        
    class WhiteToolTip extends JToolTip{
    
        @Override
        public void paintComponent(Graphics g) {
        // Set the background color to white
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        // Set the foreground color to black
        g.setColor(Color.BLACK);

        // Customize the appearance of the tooltip text here
        g.drawString(getTipText(), 5, 15);
    }
}
        
        public void showCustomToolTip(JComponent component, int x, int y, String text) {
            JToolTip tooltip = new WhiteToolTip();
            tooltip.setComponent(component);
            tooltip.setTipText(text);
            
            if (tooltipPopup != null) {
            tooltipPopup.hide(); // Hide the previous tooltip if it exists
        }


            PopupFactory factory = PopupFactory.getSharedInstance();
            tooltipPopup = factory.getPopup(component, tooltip, x, y);
            tooltipPopup.show();
        }
        
    

    

    public void displayTray(String text) throws AWTException {
        
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Information", text, TrayIcon.MessageType.INFO);
    }

    @Override
    public void countryChanged(String selectedCountry){
        
        country = selectedCountry;
        try {
            setText();
        } catch (GeoIp2Exception ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void setLabel(JLabel label, String series, String unitFront, String unitBack){
            
           
        try {
            label.setText(unitFront + DataString("Indicators",country,series, "2022") + unitBack );
            
          
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeoIp2Exception ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
public TimeSeriesCollection convertTableLine(JTable table){
    
    TimeSeriesCollection dataset = new TimeSeriesCollection(); //Initializes time datast
    //Gets table two and column count
    int rowCount = table.getRowCount();     
    int columnCount = table.getColumnCount();

    for (int col = 1; col < columnCount; col++) {
        String category = table.getColumnName(col);
        TimeSeries timeSeries = new TimeSeries(category);

        for (int row = 0; row < rowCount; row++) {
            // Gets the year from the first coloum as a string
            String yearString = (String) table.getValueAt(row, 0);

            if (yearString.equals("N/A")) {
                // Skip rows with "N/A" in the year column
                continue;
            }
            int year;
            try {
                year = Integer.parseInt(yearString);
            } catch (NumberFormatException e) {
                // Handle or skip the row with invalid year value
                continue;
            }
            // Looks at each row, and removes commas after the time column 
            String valueString = (String) table.getValueAt(row, col);
            valueString = valueString.replaceAll(",", ""); // Remove commas
            if (valueString.equals("N/A")) {
                // Skip rows with "N/A" in the value column
                continue;
            }
            double value;
            try {
                value = Double.parseDouble(valueString);
            } catch (NumberFormatException e) {
                // Handle or skip the row with non-numeric value
                continue;
            }
            // Adds year, value pair to the time series
            timeSeries.addOrUpdate(new Year(year), value);
        }
        dataset.addSeries(timeSeries);
        }
    return dataset;}
    
    
public DefaultCategoryDataset convertTableBar(JTable table) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    int rowCount = table.getRowCount();
    int columnCount = table.getColumnCount();

    //Extracts Data From Columns
    for (int col = 1; col < columnCount; col++) {
        String category = table.getColumnName(col);
        for (int row = 0; row < rowCount; row++) {
            // Gets the year from the first coloum as a string
            String timeString = (String) table.getValueAt(row, 0);
            double time;

            try {
                time = Double.parseDouble(timeString);
            } catch (NumberFormatException e) {
                // If there's an invalid time value, it's automatically set to zero
                time = 0.0;
            }

            // Looks at each row, and removes commas after the time column 

            String valueString = (String) table.getValueAt(row, col);
            valueString = valueString.replaceAll(",", ""); // Remove commas
            double value;

            try {
                // Converts string to double
                value = Double.parseDouble(valueString);
            } catch (NumberFormatException e) {
                // If there is an invalid indicator value, it's automatically set to zero
                value = 0.0;
            }

            dataset.addValue(value, category, Double.toString(time));
        }
    }
    return dataset;
    }
    public void ClickAnimation(JPanel panel){
    
         Color customColor = new Color(111, 123, 212);

                panel.setBackground(customColor);
                
                // Create a Timer to revert the background color after a short delay
                animationTimer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Change the background color back to its original color
                        panel.setBackground(WHITE);
                        animationTimer.stop(); // Stop the timer after one execution
                    }
                });
                animationTimer.start();
            
    
    
    }
    
    public void HoverEnter(JPanel panel){
        
        Color customColor = new Color(227,221,238);
        panel.setBackground(customColor);        
     
    }
    
    public void HoverExit(JPanel panel){
    
        panel.setBackground(WHITE);        

    }
    
    public static void main(String args[]) {
        
        
       
        
        
        
                
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Dashboard().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                } catch (GeoIp2Exception ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CapI1;
    private javax.swing.JLabel CapII;
    private javax.swing.JLabel CapVar;
    private javax.swing.JLabel Capl;
    private com.mycompany.worldbankia.ComboBox CountryBox;
    private javax.swing.JLabel CountryLabel;
    private javax.swing.JLabel DL;
    private javax.swing.JLabel DL1;
    private javax.swing.JLabel DL11;
    private javax.swing.JLabel DL4;
    private javax.swing.JLabel DL5;
    private javax.swing.JLabel DL8;
    private javax.swing.JPanel DashBP;
    private javax.swing.JPanel DashP;
    private javax.swing.JLabel DashboardL;
    private javax.swing.JLabel DashboardL5;
    private javax.swing.JLabel EL;
    private javax.swing.JLabel EL1;
    private javax.swing.JLabel EL2;
    private javax.swing.JLabel EL3;
    private javax.swing.JLabel EL4;
    private javax.swing.JLabel EL5;
    private javax.swing.JPanel EconP;
    private javax.swing.JLabel EconomyL;
    private javax.swing.JLabel GDPI;
    private javax.swing.JLabel GDPVar;
    private com.mycompany.worldbankia.ComboBoxSuggestion Indicators;
    private com.mycompany.worldbankia.ComboBoxSuggestion Indicators2;
    private javax.swing.JLabel InfVar;
    private javax.swing.JLabel InflationI;
    private javax.swing.JLabel MenuB;
    private javax.swing.JLabel MenuButton;
    private javax.swing.JLabel NL;
    private com.mycompany.worldbankia.ComboBox NotifBox;
    private javax.swing.JPanel NotifP;
    private javax.swing.JLabel NotificationsL;
    private com.mycompany.worldbankia.JCheckBoxCustom OnLocalized;
    private com.mycompany.worldbankia.JCheckBoxCustom OnNotifications;
    private javax.swing.JLabel Output;
    private javax.swing.JLabel Output1;
    private javax.swing.JLabel Output2;
    private javax.swing.JLabel Output3;
    private javax.swing.JLabel Output4;
    private javax.swing.JLabel Output5;
    private javax.swing.JLabel Output6;
    private javax.swing.JLabel PopI;
    private javax.swing.JLabel PopVar;
    private javax.swing.JLabel SL;
    private javax.swing.JLabel SL1;
    private javax.swing.JLabel SL2;
    private javax.swing.JLabel SL3;
    private javax.swing.JPanel SetP;
    private javax.swing.JLabel SettingsL;
    private javax.swing.JLabel SettingsL1;
    private javax.swing.JPanel SlideMenu;
    private com.mycompany.worldbankia.ComboBoxSuggestion Year1;
    private javax.swing.JButton generate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private com.mycompany.worldbankia.ComboBoxSuggestion year;
    private com.mycompany.worldbankia.ComboBoxSuggestion year2;
    // End of variables declaration//GEN-END:variables
}
