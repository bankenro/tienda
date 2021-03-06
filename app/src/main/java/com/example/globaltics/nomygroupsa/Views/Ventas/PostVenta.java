package com.example.globaltics.nomygroupsa.Views.Ventas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by GlobalTIC's on 14/01/2018.
 */

public class PostVenta {
    private Bitmap imagen,imagenu;
    private int telefono;
    private double precio;
    private String nombrec,nombre,foto,fecha,fotou;

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getFotou() {
        return fotou;
    }

    public Bitmap getImagenu() {
        return imagenu;
    }

    public void setImagenu(Bitmap imagenu) {
        this.imagenu = imagenu;
    }

    public void setFotou(String fotou) {
        this.fotou = fotou;
        try {
            byte[] byteImage = Base64.decode(fotou, Base64.DEFAULT);
            this.imagenu = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombrec() {
        return nombrec;
    }

    public void setNombrec(String nombrec) {
        this.nombrec = nombrec;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Bitmap getImagen() {
        return imagen;
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
}
