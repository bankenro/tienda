package com.example.globaltics.nomygroupsa.Views.PreCompras;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by GlobalTIC's on 14/01/2018.
 */

public class PreCompras {
    private String nombrec,correo,nombre,descripcion,fecha,foto;
    private int telefono,idcom;
    private double precio;
    private Bitmap imagen;


    public String getNombrec() {
        return nombrec;
    }

    public void setNombrec(String nombrec) {
        this.nombrec = nombrec;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setIdcom(int idcom) {
        this.idcom = idcom;
    }

    public int getIdcom() {
        return idcom;
    }
}
