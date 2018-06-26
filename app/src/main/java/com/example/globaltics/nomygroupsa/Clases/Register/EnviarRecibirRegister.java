package com.example.globaltics.nomygroupsa.Clases.Register;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.ExpandableListAdapter;
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
 * Created by GlobalTIC's on 3/12/2017.
 */

public class EnviarRecibirRegister extends AsyncTask<Void,Void,String> {
    private Context c;
    private String urla,accion,id1,nombres1,apellidop1,apellidom1,correo,contraseña,telefono,foto;
    public EnviarRecibirRegister(Context c, String urla, String accion, String id1, String nombres1, String apellidop1, String apellidom1, String correo1, String contraseña1, String telefono1, String foto1) {
        this.c = c;
        this.urla = urla;
        this.accion = accion;
        this.id1 = id1;
        this.nombres1 = nombres1;
        this.apellidop1 = apellidop1;
        this.apellidom1 = apellidom1;
        this.correo = correo1;
        this.contraseña = contraseña1;
        this.telefono = telefono1;
        this.foto = foto1;
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
            new AnalizarRegister(c,s,correo,contraseña).execute();
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
            bw.write(new EmpaqueRegister(accion,id1,nombres1,apellidop1,apellidom1,correo,contraseña,telefono,foto).packageData());
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
