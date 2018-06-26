package com.example.globaltics.nomygroupsa.Clases.ConcretarVenta;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by GlobalTIC's on 14/01/2018.
 */

public class EmpaqueConcretarVenta {
    private String accion,cdgprevb,user,cdgservb;
    public EmpaqueConcretarVenta(String accion, String cdgprevb, String user, String cdgservb) {
        this.accion = accion;
        this.cdgprevb  = cdgprevb;
        this.user = user;
        this.cdgservb = cdgservb;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();

        try{
            jo.put("accion",accion);
            jo.put("1",cdgprevb);
            jo.put("2",user);
            jo.put("3",cdgservb);

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
