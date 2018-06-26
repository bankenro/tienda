package com.example.globaltics.nomygroupsa.Clases.VentaBS;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by GlobalTIC's on 10/12/2017.
 */

public class EmpaqueVentaBS {
    private String accion,user,codigob;
    public EmpaqueVentaBS(String accion, String user, String codigob) {
        this.accion = accion;
        this.user = user;
        this.codigob = codigob;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();
        try {
            jo.put("accion",accion);
            jo.put("1",user);
            jo.put("2",codigob);
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
