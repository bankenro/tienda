package com.example.globaltics.nomygroupsa.Clases.ConcretarCompra;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GlobalTIC's on 15/01/2018.
 */

public class AnalizarConcretarCompra extends AsyncTask<Void,Void,Integer> {
    private Context c;
    private String s,mensaje;
    public AnalizarConcretarCompra(Context c, String s) {
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
        return this.concretar();
    }

    private Integer concretar() {
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
