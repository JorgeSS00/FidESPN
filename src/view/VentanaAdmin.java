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
import java.util.List; 
import java.util.ArrayList;
import client.ClienteFidESPN;
import model.Partido;

public class VentanaAdmin extends JFrame {
    private JTextField local = new JTextField(10), visitante = new JTextField(10);
    private JTextField fecha = new JTextField(8), hora = new JTextField(5), corr = new JTextField(10);
    private JTextArea area = new JTextArea(10,30);

    public VentanaAdmin(ClienteFidESPN cliente) {
        super("Administrador - FidESPN");
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(6,2));
        p.add(new JLabel("Local:")); p.add(local);
        p.add(new JLabel("Visitante:")); p.add(visitante);
        p.add(new JLabel("Fecha:")); p.add(fecha);
        p.add(new JLabel("Hora:")); p.add(hora);
        p.add(new JLabel("Corresponsal:")); p.add(corr);
        JButton btn = new JButton("Crear Partido");
        p.add(btn);
        
        add(p, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btn.addActionListener(e -> {
            String msg = "CREAR_PARTIDO:"+local.getText()+":"+visitante.getText()+":"+fecha.getText()+":"+hora.getText()+":"+corr.getText();
            cliente.enviar(msg);
            area.append("Enviado: " + local.getText() + " vs " + visitante.getText() + "\n");
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}