package com.example.globaltics.nomygroupsa.Clases.VentaBS;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


/**
 * Created by GlobalTIC's on 10/12/2017.
 */

public class AnalizarVentaBS extends AsyncTask<Void,Void,Integer> {
    private Context c;
    private String s,mensaje;
    public AnalizarVentaBS(Context c, String s) {
        this.c = c;
        this.s = s;
    }
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1){
            if (mensaje!=null){
                Toast.makeText(c,mensaje,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(c, "Error al Comprar", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(c,"Error al Comprar",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected Integer doInBackground(Void... voids) {
        return this.analizar();
    }
    private Integer analizar() {
        try {
            JSONArray ja = new JSONArray(s);
            JSONObject jo;
            for (int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);
                mensaje = jo.getString("mensaje");
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
