package com.example.globaltics.nomygroupsa.Views.PreCompras;

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

public class AnalizarPreCompras extends AsyncTask<Void,Void,Integer> {
    private Context c;
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private String s;
    private ArrayList<PreCompras> preCompras = new ArrayList<>();
    private AdapterPreCompras adapterPreCompras;
    public AnalizarPreCompras(Context c, RecyclerView rv, SwipeRefreshLayout srl, String s) {
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
            adapterPreCompras = new AdapterPreCompras(c,preCompras);
            rv.setAdapter(adapterPreCompras);
        }else {
            Toast.makeText(c,"No hay pre-compras que mostrar o vuelva a intentarlo",Toast.LENGTH_SHORT).show();
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
            preCompras.clear();
            PreCompras preCompras1;
            for (int i=0; i<ja.length();i++){
                jo = ja.getJSONObject(i);

                String nombrec = jo.getString("vr1");
                String correo = jo.getString("vr2");
                int telefono = jo.getInt("vr3");
                String nombre = jo.getString("vr4");
                String descripcion = jo.getString("vr5");
                double precio = jo.getDouble("vr6");
                String fecha = jo.getString("vr7");
                String foto = jo.getString("vr8");
                int cdgcom = jo.getInt("vr9");

                preCompras1 = new PreCompras();
                preCompras1.setNombre(nombre);
                preCompras1.setDescripcion(descripcion);
                preCompras1.setPrecio(precio);
                preCompras1.setFoto(foto);
                preCompras1.setNombrec(nombrec);
                preCompras1.setCorreo(correo);
                preCompras1.setTelefono(telefono);
                preCompras1.setFecha(fecha);
                preCompras1.setIdcom(cdgcom);

                preCompras.add(preCompras1);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
