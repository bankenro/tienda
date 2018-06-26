package com.example.globaltics.nomygroupsa.Clases.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by GlobalTIC's on 3/12/2017.
 */

public class EmpaqueLogin {
    private String user,contra,accion;
    public EmpaqueLogin(String user, String contra, String accion) {
        this.accion = accion;
        this.user = user;
        this.contra = contra;
    }


    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();

        try{
            jo.put("accion",accion);
            jo.put("1",user);
            jo.put("2",contra);
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
