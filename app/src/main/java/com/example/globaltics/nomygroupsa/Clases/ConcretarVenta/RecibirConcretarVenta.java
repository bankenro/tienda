package com.example.globaltics.nomygroupsa.Clases.ConcretarVenta;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.Conexion;
import com.example.globaltics.nomygroupsa.Clases.Login.AnalizadorLogin;
import com.example.globaltics.nomygroupsa.Clases.Login.EmpaqueLogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by GlobalTIC's on 14/01/2018.
 */

public class RecibirConcretarVenta extends AsyncTask<Void,Void,String> {
    private Context c;
    private String urla,accion,cdgprevb,user,cdgservb;
    public RecibirConcretarVenta(Context c, String urla, String accion, String cdgprevb, String user, String cdgservb) {
        this.c = c;
        this.urla = urla;
        this.accion = accion;
        this.cdgprevb = cdgprevb;
        this.user = user;
        this.cdgservb = cdgservb;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.recibir();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null){
            Toast.makeText(c,"No se puede conectar",Toast.LENGTH_SHORT).show();
        }else {
            new AnalizadorConcretarVenta(c,s).execute();
        }
    }

    private String recibir() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con == null){
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueConcretarVenta(accion,cdgprevb,user,cdgservb).packageData());
            bw.flush();
            bw.close();
            os.close();
            int responsecode = con.getResponseCode();
            if (responsecode==con.HTTP_OK){
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                if (br != null){
                    while ((line=br.readLine()) != null){
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
