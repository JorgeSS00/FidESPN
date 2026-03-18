/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author Jorge S
 */

import java.io.*;
import java.net.*;
import model.Usuario;
import model.Partido;

public class HiloCliente extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public HiloCliente(Socket socket) throws Exception {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void enviar(String msg) { 
        out.println(msg); 
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                // 1. Lógica de Autenticación
                if (mensaje.startsWith("LOGIN:")) {
                    String[] d = mensaje.split(":");
                    Usuario u = ServidorFidESPN.autenticar(d[1], d[2]);
                    if (u != null) {
                        enviar("LOGIN_OK:" + u.getRol().toUpperCase());
                    } else {
                        enviar("LOGIN_FAIL");
                    }
                } 
                // 2. Lógica de Creación de Partidos (Admin)
                else if (mensaje.startsWith("CREAR_PARTIDO:")) {
                    String[] d = mensaje.split(":");
                    // Estructura: Local, Visitante, Fecha, Hora, Corresponsal
                    Partido p = new Partido(d[1], d[2], d[3], d[4], d[5]);
                    ServidorFidESPN.getPartidos().add(p);
                    ServidorFidESPN.broadcast("SISTEMA: Nuevo partido programado -> " + p.toString());
                }
                // 3. Lógica de Reportes en Tiempo Real (Corresponsal)
                else if (mensaje.startsWith("REPORTAR:")) {
                    String contenido = mensaje.substring(9);
                    // Le damos un formato especial de "Urgente" para el Fanático
                    ServidorFidESPN.broadcast("⚽ REPORTE OFICIAL: " + contenido);
                }
                // 4. Lógica de Chat Grupal (Fanático)
                else if (mensaje.startsWith("CHAT:")) {
                    String contenido = mensaje.substring(5);
                    ServidorFidESPN.broadcast("💬 CHAT: " + contenido);
                }
            }
        } catch (Exception e) {
            System.out.println("Cliente desconectado.");
        } finally {
            try { socket.close(); } catch (IOException e) {}
        }
    }
}