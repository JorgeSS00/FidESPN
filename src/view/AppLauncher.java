/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Jorge S
 */
import server.ServidorFidESPN;
import javax.swing.SwingUtilities;

public class AppLauncher {
    public static void main(String[] args) {
        
        new Thread(() -> {
            ServidorFidESPN.main(args);
        }).start();

       
        try { Thread.sleep(1200); } catch (Exception e) {}

        
        SwingUtilities.invokeLater(() -> {
            new VentanaLogin();
        });
    }
}