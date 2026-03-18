/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Jorge S
 */
import javax.swing.*;
import java.awt.*;
import client.ClienteFidESPN;

public class VentanaLogin extends JFrame {
    public VentanaLogin() {
        setTitle("Login FidESPN");
        setSize(350, 200);
        setLayout(new GridLayout(3, 2, 5, 5));
        setLocationRelativeTo(null);

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();
        JButton btn = new JButton("Entrar");

        add(new JLabel(" Usuario:")); add(user);
        add(new JLabel(" Password:")); add(pass);
        add(new JLabel("")); add(btn);

        btn.addActionListener(e -> {
            try {
                ClienteFidESPN cliente = new ClienteFidESPN();
                // Enviamos los datos al servidor
                cliente.enviarMensaje("LOGIN:" + user.getText().trim() + ":" + new String(pass.getPassword()));
                
                String resp = cliente.recibirMensaje();

                if (resp == null) {
                    JOptionPane.showMessageDialog(this, "Sin respuesta del servidor");
                    return;
                }

                // Usamos trim() y toUpperCase() para que la comparacion sea infalible
                String respuestaLimpia = resp.trim().toUpperCase();

                if (respuestaLimpia.contains("ADMIN")) {
                    new VentanaAdmin(cliente);
                    dispose();
                } 
                else if (respuestaLimpia.contains("CORRESPONSAL")) {
                    new VentanaCorresponsal(cliente);
                    dispose();
                } 
                else if (respuestaLimpia.contains("FANATICO")) {
                    new VentanaFanatico(cliente);
                    dispose();
                } 
                else {
                    JOptionPane.showMessageDialog(this, "Acceso Denegado: Usuario o clave incorrectos");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: Asegúrate de que el servidor esté corriendo.");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}