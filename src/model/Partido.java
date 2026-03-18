/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jorge S
 */

import java.io.Serializable;

public class Partido implements Serializable {
    private String local, visitante, fecha, hora, corresponsal;
    private int golesLocal = 0, golesVis = 0;

    public Partido(String local, String visitante, String fecha, String hora, String corresponsal) {
        this.local = local;
        this.visitante = visitante;
        this.fecha = fecha;
        this.hora = hora;
        this.corresponsal = corresponsal;
    }

    public void golLocal() { golesLocal++; }
    public String marcador() { return local + " " + golesLocal + " - " + golesVis + " " + visitante; }
    
    @Override
    public String toString() {
        return marcador() + " (" + fecha + " " + hora + ") Corresponsal: " + corresponsal;
    }
}