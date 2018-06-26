package com.example.globaltics.nomygroupsa.Clases.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Activitys.LoginActivity;
import com.example.globaltics.nomygroupsa.Activitys.OpcionesActivity;
import com.example.globaltics.nomygroupsa.Clases.SQLite.UsuariosSQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by GlobalTIC's on 3/12/2017.
 */

public class AnalizadorLogin extends AsyncTask<Void,Void,Integer> {
    private Context c;
    private String s,codigo,nombres,nombrec,apellidop,apellidom,foto,correo,ntelefono,activo,tipouser;
    public AnalizadorLogin(Context c, String s) {
        this.c = c;
        this.s = s;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.analizar();
    }

    @Override
    protected void onPostExecute(final Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1){
            if (Objects.equals(activo,"1")){
                Toast.makeText(c,"Bienvenido "+nombrec,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(c, OpcionesActivity.class);
                SharedPreferences preferences = c.getSharedPreferences("datos",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("nombrec",nombrec);
                editor.putString("codigo",codigo);
                editor.putString("foto",foto);
                editor.putString("tipouser",tipouser);
                editor.apply();
                c.startActivity(intent);
                ((Activity)(c)).finish();
            }else if (Objects.equals(activo,"0")){
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("NECESITA SOLICITAR ACTIVACION")
                        .setMessage("Comuniquese con el administrador para activar su cuenta")
                        .setPositiveButton("LLAMAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(c, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                c.startActivity(intent);
                            }
                        })
                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(c,LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                c.startActivity(intent);
                            }
                        });
                builder.show();
            }else {
                Toast.makeText(c,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();

                SharedPreferences preferences = c.getSharedPreferences("datos",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
            }
        }else {
            Toast.makeText(c,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = c.getSharedPreferences("datos",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
    }

    private Integer analizar() {
        try {
            JSONArray ja = new JSONArray(s);
            JSONObject jo;
            for (int i = 0; i < ja.length(); i++){
                jo = ja.getJSONObject(i);
                codigo = jo.getString("vr1");
                nombres = jo.getString("vr2");
                nombrec = jo.getString("vr3");
                apellidop = jo.getString("vr4");
                apellidom = jo.getString("vr5");
                foto = jo.getString("vr6");
                correo = jo.getString("vr7");
                ntelefono = jo.getString("vr8");
                activo = jo.getString("vr9");
                tipouser = jo.getString("vr10");

                try {
                    UsuariosSQLite usuariosSQLite = new UsuariosSQLite(c,"UsersDB.sqlite",null,1);
                    usuariosSQLite.insertData(nombres,codigo,foto,tipouser);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
