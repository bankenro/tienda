package com.example.globaltics.nomygroupsa.Clases.VentaBS;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
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

/**
 * Created by GlobalTIC's on 10/12/2017.
 */

public class EnviarVentaBS extends AsyncTask<Void,Void,String> {
    private Context c;
    private String urla,accion,user,codigob;
    public EnviarVentaBS(Context c, String urla, String accion, String user, String codigob) {
        this.c = c;
        this.urla = urla;
        this.accion = accion;
        this.user = user;
        this.codigob = codigob;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.enviar();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null){
            Toast.makeText(c,"Vuelva a intentarlo",Toast.LENGTH_SHORT).show();
        }else {
            new AnalizarVentaBS(c,s).execute();
        }
    }

    private String enviar() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con==null){
            return null;
        }
        try{
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueVentaBS(accion,user,codigob).packageData());
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
                    while ((line=br.readLine())!=null){
                        response.append(line+"n");
                    }
                }else {
                    return null;
                }
                return response.toString();
            } else {
                return String.valueOf(responsecode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
