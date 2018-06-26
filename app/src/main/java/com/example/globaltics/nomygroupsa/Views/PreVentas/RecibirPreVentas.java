package com.example.globaltics.nomygroupsa.Views.PreVentas;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.Conexion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/*
 * Created by GlobalTIC's on 10/01/2018.
 */

public class RecibirPreVentas extends AsyncTask<Void,Void,String> {
    private Context c;
    private String urla,accion,user;
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    public RecibirPreVentas(Context c, String urla, String accion, SwipeRefreshLayout srl, RecyclerView rv, String user) {
        this.c = c;
        this.urla = urla;
        this.accion = accion;
        this.srl = srl;
        this.rv = rv;
        this.user = user;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.descargar();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s==null){
            srl.setRefreshing(false);
            Toast.makeText(c,"Error al conectar con el servidor",Toast.LENGTH_SHORT).show();
        }else {
            new AnalizarPreVentas(c,rv,srl,s).execute();
        }
    }

    private String descargar() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con==null){
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaquePreVentas(accion,user).packgeData());
            bw.flush();
            bw.close();
            os.close();
            int responsecode = con.getResponseCode();
            if (responsecode==con.HTTP_OK){
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                if (br!=null){
                    while ((line=br.readLine())!=null){
                        response.append(line+"n");
                    }
                }else {
                    return null;
                }
                return response.toString();
            }else {
                return String.valueOf(responsecode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
