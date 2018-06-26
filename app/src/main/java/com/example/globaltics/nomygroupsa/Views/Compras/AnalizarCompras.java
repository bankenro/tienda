package com.example.globaltics.nomygroupsa.Views.Compras;

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
 * Created by GlobalTIC's on 24/12/2017.
 */

public class AnalizarCompras extends AsyncTask<Void,Void,Integer> {
    private Context c;
    private RecyclerView rv;
    private SwipeRefreshLayout srl;
    private String s;
    private ArrayList<Compras> compras = new ArrayList<>();
    private AdapatadorCompras adapatadorCompras;
    public AnalizarCompras(Context c, RecyclerView rv, SwipeRefreshLayout srl, String s) {
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
            adapatadorCompras = new AdapatadorCompras(c,compras);
            rv.setAdapter(adapatadorCompras);

        }else {
            Toast.makeText(c,"No hay compras que mostrar o vuelva a intentarlo ",Toast.LENGTH_SHORT).show();
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
            compras.clear();
            Compras compras1;
            for (int i=0; i<ja.length();i++){
                jo = ja.getJSONObject(i);

                String nombrec = jo.getString("vr1");
                int telefono = jo.getInt("vr2");
                String fotou = jo.getString("vr3");
                String nombre = jo.getString("vr4");
                double precio = jo.getDouble("vr5");
                String foto = jo.getString("vr6");
                String fecha = jo.getString("vr7");

                compras1 = new Compras();
                compras1.setNombre(nombre);
                compras1.setPrecio(precio);
                compras1.setFoto(foto);
                compras1.setFotou(fotou);
                compras1.setNombrec(nombrec);
                compras1.setTelefono(telefono);
                compras1.setFecha(fecha);

                compras.add(compras1);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
