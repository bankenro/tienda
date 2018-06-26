package com.example.globaltics.nomygroupsa.Views.Articulos;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GlobalTIC's on 5/12/2017.
 */

public class AnalizarArticulos extends AsyncTask<Void,Void,Integer>{
    private Context c;
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private String s;
    private ArrayList<Articulos> articulos = new ArrayList<>();
    private AdapterArticulos adapterArticulos;
    public AnalizarArticulos(Context c, RecyclerView rv, SwipeRefreshLayout srl, String s) {
        this.c = c;
        this.rv = rv;
        this.srl = srl;
        this.s = s;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.analizar();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        srl.setRefreshing(false);
        if (integer==1){
            adapterArticulos = new AdapterArticulos(c,articulos);
            rv.setAdapter(adapterArticulos);

        }else {
            Toast.makeText(c,"No hay articulos o vuelva a intentarlo",Toast.LENGTH_SHORT).show();
        }
    }

    private Integer analizar() {
        try {
            JSONArray ja = new JSONArray(s);
            JSONObject jo;
            articulos.clear();
            Articulos articulos1;
            for (int i=0; i<ja.length();i++){
                jo = ja.getJSONObject(i);

                int id = jo.getInt("vr1");
                String nombre = jo.getString("vr2");
                String descripcion = jo.getString("vr3");
                double precio = jo.getDouble("vr4");
                String foto = jo.getString("vr5");

                articulos1 = new Articulos();
                articulos1.setId(id);
                articulos1.setNombre(nombre);
                articulos1.setDescripcion(descripcion);
                articulos1.setPrecio(precio);
                articulos1.setFoto(foto);

                articulos.add(articulos1);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
