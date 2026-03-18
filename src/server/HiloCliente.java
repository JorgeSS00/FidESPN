/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author Jorge S
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
                if (mensaje.startsWith("LOGIN:")) {
                    String[] d = mensaje.split(":");
                    Usuario u = ServidorFidESPN.autenticar(d[1], d[2]);
                    
                    if (u != null) {
                       
                        enviar("LOGIN_OK:" + u.getRol().toUpperCase());
                    } else {
                        enviar("LOGIN_FAIL");
                    }
                } 
                else if (mensaje.startsWith("CREAR_PARTIDO:")) {
                    String[] d = mensaje.split(":");
                    
                    Partido p = new Partido(d[1], d[2], d[3], d[4], d[5]);
                    ServidorFidESPN.getPartidos().add(p);
                    ServidorFidESPN.broadcast("NUEVO_PARTIDO:" + p.toString());
                }
                else if (mensaje.startsWith("CHAT:") || mensaje.startsWith("REPORTAR:")) {
                    ServidorFidESPN.broadcast(mensaje);
                }
            }
        } catch (Exception e) {
            System.out.println("Cliente desconectado.");
        } finally {
            try { 
                socket.close(); 
            } catch (IOException e) {
                // Error al cerrar socket
            }
        }
    }
}