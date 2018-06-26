package com.example.globaltics.nomygroupsa.Clases.Register;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.Conexion;
import com.example.globaltics.nomygroupsa.Clases.Login.EnviarRecibirLogin;
import com.kosalgeek.android.md5simply.MD5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.urla;

/**
 * Created by GlobalTIC's on 3/12/2017.
 */

public class AnalizarRegister extends AsyncTask<Void,Void,Integer> {
    private Context c;
    private String s,correo,contraseña,mensaje;
    public AnalizarRegister(Context c, String s, String correo, String contraseña) {
        this.c = c;
        this.s = s;
        this.correo = correo;
        this.contraseña = contraseña;
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1){
            if (mensaje.length()>0){
                if (Objects.equals(mensaje,"Registrado con exito")){
                    String accion = MD5.encrypt("login");
                    new EnviarRecibirLogin(c,urla,accion,correo,contraseña).execute();
                }else if (Objects.equals(mensaje,"")){
                    Toast.makeText(c,"Error al registrarse",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(c,"Error al registrarse",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(c,"Error al registrarse",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(c,"Error al registrarse",Toast.LENGTH_SHORT).show();
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
            for (int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);
                mensaje = jo.getString("mensaje");
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
