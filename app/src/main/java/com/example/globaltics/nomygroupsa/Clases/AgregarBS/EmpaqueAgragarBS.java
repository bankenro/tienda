package com.example.globaltics.nomygroupsa.Clases.AgregarBS;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by GlobalTIC's on 31/12/2017.
 */

public class EmpaqueAgragarBS {
    private String accion,nomb,descr,pre,foto1,idsp;
    public EmpaqueAgragarBS(String accion, String nomb, String descr, String pre, String foto1, String idsp) {
        this.accion = accion;
        this.nomb = nomb;
        this.descr = descr;
        this.pre = pre;
        this.foto1 = foto1;
        this.idsp = idsp;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();
        try {
            jo.put("accion",accion);
            jo.put("1",nomb);
            jo.put("2",descr);
            jo.put("3",pre);
            jo.put("4",foto1);
            jo.put("5",idsp);
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
