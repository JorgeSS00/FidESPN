/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Jorge S
 */

import client.ClienteFidESPN;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaFanatico extends JFrame {
    private JTextArea area = new JTextArea(20,40);
    private JTextField campo = new JTextField(20);
    private ClienteFidESPN cliente;
    private List<String> misFavoritos = new ArrayList<>(); // Colección para personalización

    public VentanaFanatico(ClienteFidESPN c) {
        super("FidESPN - Fanático Minuto a Minuto");
        this.cliente = c;
        setLayout(new BorderLayout());
        
        area.setEditable(false);
        area.setBackground(new Color(245, 245, 245));
        add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.add(new JLabel("Chat:"));
        panelInferior.add(campo);
        
        JButton btnEnviar = new JButton("Enviar");
        JButton btnFav = new JButton("⭐ Seguir Equipo");
        btnFav.setBackground(new Color(241, 196, 15)); // Color amarillo favorito
        
        panelInferior.add(btnEnviar);
        panelInferior.add(btnFav);
        add(panelInferior, BorderLayout.SOUTH);

        // Lógica de Favoritos (Historia de Usuario 3)
        btnFav.addActionListener(e -> {
            String equipo = JOptionPane.showInputDialog(this, "¿Qué equipo deseas seguir?");
            if (equipo != null && !equipo.trim().isEmpty()) {
                misFavoritos.add(equipo.toLowerCase().trim());
                area.append("📌 Ahora sigues a: " + equipo.toUpperCase() + ". Recibirás alertas especiales.\n");
            }
        });

        btnEnviar.addActionListener(e -> {
            if(!campo.getText().isEmpty()){
                cliente.enviarMensaje("CHAT: Fanático: " + campo.getText());
                campo.setText("");
            }
        });

        // Hilo de recepción con filtrado de favoritos (Historia de Usuario 4)
        new Thread(() -> {
            try {
                String mensaje;
                while ((mensaje = cliente.recibirMensaje()) != null) {
                    String msjFinal = mensaje;
                    boolean esFavorito = misFavoritos.stream().anyMatch(f -> msjFinal.toLowerCase().contains(f));
                    
                    if (esFavorito) {
                        area.append("⭐ NOTICIA DE TU EQUIPO: " + mensaje + " ⭐\n");
                    } else {
                        area.append(mensaje + "\n");
                    }
                }
            } catch (Exception e) {
                area.append("Desconectado del servidor.\n");
            }
        }).start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}