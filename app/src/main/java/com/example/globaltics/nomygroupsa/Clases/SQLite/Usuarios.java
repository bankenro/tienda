package com.example.globaltics.nomygroupsa.Clases.SQLite;

/**
 * Created by GlobalTIC's on 3/12/2017.
 */

public class Usuarios {
    private int id;
    private String nombre,codigo,imagen,tipo;
    public Usuarios(int id, String nombre, String codigo, String imagen, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.imagen = imagen;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTipo() {
        return tipo;
    }
}
