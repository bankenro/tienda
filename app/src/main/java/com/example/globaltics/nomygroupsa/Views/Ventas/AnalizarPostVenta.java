package com.example.globaltics.nomygroupsa.Views.Ventas;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GlobalTIC's on 14/01/2018.
 */

public class AnalizarPostVenta extends AsyncTask<Void,Void,Integer> {
    private Context c;
    private RecyclerView rv;
    private SwipeRefreshLayout srl;
    private String s;
    private ArrayList<PostVenta> postVentas = new ArrayList<>();
    private AdapterPostVenta adapterPostVenta;
    public AnalizarPostVenta(Context c, RecyclerView rv, SwipeRefreshLayout srl, String s) {
        this.c = c;
        this.rv = rv;
        this.srl = srl;
        this.s = s;
    }
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        srl.setRefreshing(false);
        if (integer==1){
            adapterPostVenta = new AdapterPostVenta(c,postVentas);
            rv.setAdapter(adapterPostVenta);

        }else {
            Toast.makeText(c,"No hay ventas o vuelva a intentarlo",Toast.LENGTH_SHORT).show();
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
            postVentas.clear();
            PostVenta postVenta1;
            for (int i=0; i<ja.length();i++){
                jo = ja.getJSONObject(i);

                String nombrec = jo.getString("vr1");
                int telefono = jo.getInt("vr2");
                String fotou = jo.getString("vr3");
                String nombre = jo.getString("vr4");
                double precio = jo.getDouble("vr5");
                String foto = jo.getString("vr6");
                String fecha = jo.getString("vr7");

                postVenta1 = new PostVenta();
                postVenta1.setNombre(nombre);
                postVenta1.setPrecio(precio);
                postVenta1.setFoto(foto);
                postVenta1.setFotou(fotou);
                postVenta1.setNombrec(nombrec);
                postVenta1.setTelefono(telefono);
                postVenta1.setFecha(fecha);

                postVentas.add(postVenta1);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
