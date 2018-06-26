package com.example.globaltics.nomygroupsa.Views.PreVentas;

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
 * Created by GlobalTIC's on 10/01/2018.
 */

public class AnalizarPreVentas extends AsyncTask<Void,Void,Integer>{
    private Context c;
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private String s;
    private ArrayList<PreVentas> preVentas = new ArrayList<>();
    private AdapterPreVentas adapterPreVentas;
    public AnalizarPreVentas(Context c, RecyclerView rv, SwipeRefreshLayout srl, String s) {
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
            adapterPreVentas = new AdapterPreVentas(c,preVentas);
            rv.setAdapter(adapterPreVentas);
        }else {
            Toast.makeText(c,"No hay pre-ventas o vuelva a intentarlo",Toast.LENGTH_SHORT).show();
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
            preVentas.clear();
            PreVentas preVentas1;
            for (int i=0; i<ja.length();i++){
                jo = ja.getJSONObject(i);

                int idserv = jo.getInt("vr1");
                String nombre = jo.getString("vr2");
                String descripcion = jo.getString("vr3");
                double precio = jo.getDouble("vr4");
                String foto = jo.getString("vr5");
                String nombrec = jo.getString("vr6");
                String correo = jo.getString("vr7");
                int telefono = jo.getInt("vr8");
                int idprev = jo.getInt("vr9");
                String fecha = jo.getString("vr10");

                preVentas1 = new PreVentas();
                preVentas1.setIdserv(idserv);
                preVentas1.setNombre(nombre);
                preVentas1.setDescripcion(descripcion);
                preVentas1.setPrecio(precio);
                preVentas1.setFoto(foto);
                preVentas1.setNombrec(nombrec);
                preVentas1.setCorreo(correo);
                preVentas1.setTelefono(telefono);
                preVentas1.setIdprev(idprev);
                preVentas1.setFecha(fecha);

                preVentas.add(preVentas1);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
