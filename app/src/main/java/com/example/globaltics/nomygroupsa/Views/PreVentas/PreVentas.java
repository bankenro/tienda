package com.example.globaltics.nomygroupsa.Views.PreVentas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by GlobalTIC's on 10/01/2018.
 */

public class PreVentas {
    private int idserv, telefono, idprev;
    private double precio;
    private String nombre, descripcion, foto, nombrec, correo, fecha;
    private Bitmap imagen;

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdprev() {
        return idprev;
    }

    public void setIdprev(int idprev) {
        this.idprev = idprev;
    }

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdserv() {
        return idserv;

    }

    public void setIdserv(int idserv) {
        this.idserv = idserv;
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
