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

public class VentanaAdmin extends JFrame {
    private JTextField local = new JTextField(10), visitante = new JTextField(10);
    private JTextField fecha = new JTextField(8), hora = new JTextField(5), corr = new JTextField(10);
    private JTextArea area = new JTextArea(10,30);

    public VentanaAdmin(ClienteFidESPN cliente) {
        super("Panel de Control - Administrador");
        setLayout(new BorderLayout());
        
        JPanel p = new JPanel(new GridLayout(6,2, 5, 5));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        p.add(new JLabel("Equipo Local:")); p.add(local);
        p.add(new JLabel("Equipo Visitante:")); p.add(visitante);
        p.add(new JLabel("Fecha (dd/mm):")); p.add(fecha);
        p.add(new JLabel("Hora (hh:mm):")); p.add(hora);
        p.add(new JLabel("Corresponsal ID:")); p.add(corr);
        
        JButton btn = new JButton("Publicar Jornada");
        btn.setBackground(new Color(46, 204, 113)); // Color verde éxito
        p.add(new JLabel("Acción:")); p.add(btn);
        
        add(p, BorderLayout.NORTH);
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btn.addActionListener(e -> {
            try {
                // Validación de campos (Excepciones)
                if (local.getText().trim().isEmpty() || visitante.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Error: El nombre de los equipos es obligatorio.");
                }
                if (fecha.getText().trim().isEmpty()) {
                    throw new Exception("Error: Debe indicar una fecha.");
                }

                String msg = "CREAR_PARTIDO:"+local.getText()+":"+visitante.getText()+":"+fecha.getText()+":"+hora.getText()+":"+corr.getText();
                cliente.enviar(msg);
                
                area.append("✔ Enviado al servidor: " + local.getText() + " vs " + visitante.getText() + "\n");
                limpiarCampos();

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos Incompletos", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void limpiarCampos() {
        local.setText(""); visitante.setText(""); fecha.setText(""); hora.setText(""); corr.setText("");
    }
}