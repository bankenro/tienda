package com.example.globaltics.nomygroupsa.Clases.AgregarBS;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.Conexion;
import com.example.globaltics.nomygroupsa.Clases.Register.EmpaqueRegister;

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

public class EnviarAgregarBS extends AsyncTask<Void,Void,String>{
    private Context c;
    private String urla,accion,nomb,descr,pre,foto1,idsp;
    public EnviarAgregarBS(Context c, String urla, String accion, String nomb, String descr, String pre, String foto1, String idsp) {
        this.c = c;
        this.urla = urla;
        this.accion = accion;
        this.nomb = nomb;
        this.descr = descr;
        this.pre = pre;
        this.foto1 = foto1;
        this.idsp = idsp;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.registrar();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null){
            Toast.makeText(c,"Vuelva a intentarlo",Toast.LENGTH_SHORT).show();
        }else {
            new AnalizarAgregarBS(c,s).execute();
        }

    }

    private String registrar() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con==null){
            return null;
        }
        try{
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueAgragarBS(accion,nomb,descr,pre,foto1,idsp).packageData());
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
