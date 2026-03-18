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
import java.util.*;
import model.*; 

public class ServidorFidESPN {
    private static List<HiloCliente> clientes = new ArrayList<>();
    private static List<Partido> partidos = new ArrayList<>();
    private static List<Usuario> usuariosDB = new ArrayList<>(Arrays.asList(
        new Administrador("admin", "123"),
        new Corresponsal("juan", "123"),
        new Fanatico("pedro", "123")
    ));

    public static void main(String[] args) {
        System.out.println("Servidor FidESPN iniciado en puerto 5000...");
        try (ServerSocket server = new ServerSocket(5000)) {
            while (true) {
                Socket socket = server.accept();
                
                HiloCliente hilo = new HiloCliente(socket);
                clientes.add(hilo);
                hilo.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static synchronized Usuario autenticar(String u, String p) {
    for (Usuario user : usuariosDB) {
        if (user.getUsername().trim().equalsIgnoreCase(u.trim()) && user.autenticar(p.trim())) {
            return user;
        }
    }
    return null;
}

    
    public static synchronized void broadcast(String msg) {
        System.out.println("Difundiendo: " + msg);
        Iterator<HiloCliente> it = clientes.iterator();
        while (it.hasNext()) {
            HiloCliente c = it.next();
            try {
                c.enviar(msg);
            } catch (Exception e) {
                it.remove(); 
            }
        }
    }

    public static List<Partido> getPartidos() {
        return partidos;
    }
}