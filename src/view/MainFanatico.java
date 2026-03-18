/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JOptionPane;
import client.ClienteFidESPN;

public class MainFanatico {
    public static void main(String[] args) {
        try {
            String rol = JOptionPane.showInputDialog("Ingresa rol (admin, corresponsal, fanatico)");
            if (rol == null) return;
            
            ClienteFidESPN c = new ClienteFidESPN();
            if (rol.equalsIgnoreCase("admin")) {
                new VentanaAdmin(c);
            } else if (rol.equalsIgnoreCase("corresponsal")) {
                new VentanaCorresponsal(c);
            } else if (rol.equalsIgnoreCase("fanatico")) {
                new VentanaFanatico(c);
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}
