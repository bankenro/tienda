package com.example.globaltics.nomygroupsa.Views.Articulos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by GlobalTIC's on 5/12/2017.
 */

public class Articulos {
    private int id;
    private double precio;
    private String nombre, descripcion, foto;
    private Bitmap imagen;

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setFoto(String foto) {
        this.foto = foto;
        try {
            byte[] byteImage = Base64.decode(foto, Base64.DEFAULT);
            this.imagen = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public String getFoto() {
        return foto;
    }
}
