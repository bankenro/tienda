package com.example.globaltics.nomygroupsa.Views.Articulos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.WeakHashMap;

/**
 * Created by GlobalTIC's on 5/12/2017.
 */

public class EmpaqueArticulos {
    private String accion;
    public EmpaqueArticulos(String accion) {
        this.accion = accion;
    }

    String packgeData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();
        try {
            jo.put("accion",accion);
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
            } while (it.hasNext());
            return sb.toString();
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
