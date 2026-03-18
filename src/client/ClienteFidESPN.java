/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

/**
 *
 * @author Jorge S
 */

import java.io.*;
import java.net.*;

public class ClienteFidESPN {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClienteFidESPN() throws IOException {
        socket = new Socket("localhost", 5000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    // Estos nombres deben ser EXACTOS a los que piden tus ventanas
    public void enviarMensaje(String mensaje) {
        out.println(mensaje);
    }

    public String recibirMensaje() throws IOException {
        return in.readLine();
    }
    
    // Alias para evitar errores en otras clases
    public void enviar(String mensaje) { enviarMensaje(mensaje); }
    public String recibir() throws IOException { return recibirMensaje(); }
}