/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jorge S
 */
import java.util.ArrayList;
import java.util.List;

public class Fanatico extends Usuario {

    private List<String> equiposFavoritos;

    public Fanatico(String u, String p) {
        super(u, p, "FANATICO");
        equiposFavoritos = new ArrayList<>();
    }

    public void agregarEquipoFavorito(String equipo) {
        equiposFavoritos.add(equipo);
    }
}