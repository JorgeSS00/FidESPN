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

public class VentanaCorresponsal extends JFrame {
    private JTextArea area = new JTextArea(20,40);
    private JTextField campo = new JTextField(30);
    private ClienteFidESPN cliente;

    public VentanaCorresponsal(ClienteFidESPN c) {
        super("Corresponsal - Reporte de Partidos");
        this.cliente = c;
        setLayout(new BorderLayout());
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(campo);
        JButton enviar = new JButton("Reportar");
        panel.add(enviar);
        add(panel, BorderLayout.SOUTH);

        enviar.addActionListener(e -> {
            
            cliente.enviarMensaje("REPORTAR: Corresponsal: " + campo.getText());
            area.append("Tú: " + campo.getText() + "\n");
            campo.setText("");
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}