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

public abstract class Usuario implements Serializable {

    protected String username;
    protected String password;
    protected String rol;

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getUsername() { return username; }
    public String getRol() { return rol; }

    public boolean autenticar(String password) {
        return this.password.equals(password);
    }
}