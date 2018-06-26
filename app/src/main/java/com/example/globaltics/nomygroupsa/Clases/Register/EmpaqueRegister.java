package com.example.globaltics.nomygroupsa.Clases.Register;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by GlobalTIC's on 3/12/2017.
 */

public class EmpaqueRegister {
    private String accion,id1,nombres1,apellidop1,apellidom1,correo,contraseña,telefono,foto;
    public EmpaqueRegister(String accion, String id1, String nombres1, String apellidop1, String apellidom1, String correo, String contraseña, String telefono, String foto) {
        this.accion = accion;
        this.id1 = id1;
        this.nombres1 = nombres1;
        this.apellidop1 = apellidop1;
        this.apellidom1 = apellidom1;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.foto = foto;
    }

    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();
        try {
            jo.put("accion",accion);
            jo.put("1",id1);
            jo.put("2",nombres1);
            jo.put("3",apellidop1);
            jo.put("4",apellidom1);
            jo.put("5",correo);
            jo.put("6",contraseña);
            jo.put("7",telefono);
            jo.put("8",foto);
            Boolean primervalor = true;
            Iterator it = jo.keys();
            do {
                String key = it.next().toString();
                String value = jo.get(key).toString();
                if (primervalor){
                    primervalor = false;
                }else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key,"UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value,"UTF-8"));
            }while (it.hasNext());
            return sb.toString();
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
