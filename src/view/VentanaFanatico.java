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

public class VentanaFanatico extends JFrame {
    private JTextArea area = new JTextArea(20,40);
    private JTextField campo = new JTextField(30);
    private ClienteFidESPN cliente;

    public VentanaFanatico(ClienteFidESPN c) {
        super("Fanático - Minuto a Minuto");
        this.cliente = c;
        setLayout(new BorderLayout());
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(campo);
        JButton enviar = new JButton("Enviar Chat");
        panel.add(enviar);
        add(panel, BorderLayout.SOUTH);

        enviar.addActionListener(e -> {
            cliente.enviarMensaje("CHAT: Fanatico: " + campo.getText());
            campo.setText("");
        });

        new Thread(() -> {
            try {
                String mensaje;
                while ((mensaje = cliente.recibirMensaje()) != null) {
                    area.append(mensaje + "\n");
                }
            } catch (Exception e) { }
        }).start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}