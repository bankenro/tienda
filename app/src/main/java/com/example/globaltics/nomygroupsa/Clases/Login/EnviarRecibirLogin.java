package com.example.globaltics.nomygroupsa.Clases.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.Conexion;
import com.example.globaltics.nomygroupsa.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;


/*
 * Created by GlobalTIC's on 3/12/2017.
 */

public class EnviarRecibirLogin extends AsyncTask<Void,Void,String> {
    private ProgressDialog pd;
    private Context c;
    private String user,contra,urla,accion;
    public EnviarRecibirLogin(Context c, String urla, String accion,String user, String contra) {
        this.c = c;
        this.urla = urla;
        this.user = user;
        this.contra = contra;
        this.accion = accion;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.iniciar();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c,android.R.style.Theme_Translucent_NoTitleBar);
        pd.show();
        pd.setContentView(R.layout.login);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        if (s == null){
            Toast.makeText(c,"No se puede conectar",Toast.LENGTH_SHORT).show();
        }else {
            new AnalizadorLogin(c,s).execute();
        }
    }

    private String iniciar() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con == null){
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueLogin(user,contra,accion).packageData());
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
