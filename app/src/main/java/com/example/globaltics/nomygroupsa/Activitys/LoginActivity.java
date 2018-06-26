package com.example.globaltics.nomygroupsa.Activitys;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.globaltics.nomygroupsa.Clases.SQLite.UsuariosSQLite;
import com.example.globaltics.nomygroupsa.Fragments.LoginForOneTouch;
import com.example.globaltics.nomygroupsa.Fragments.LoginFragment;
import com.example.globaltics.nomygroupsa.R;

public class LoginActivity extends AppCompatActivity {
    private String nombre,codigo,foto,tipouser;
    final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        nombre = preferences.getString("nombrec","");
        codigo = preferences.getString("codigo","");
        foto = preferences.getString("foto","");
        tipouser = preferences.getString("tipouser","");

        UsuariosSQLite usuariosSQLite = new UsuariosSQLite(LoginActivity.this,"UsersDB.sqlite",null,1);
        usuariosSQLite.queryData("CREATE TABLE IF NOT EXISTS USERS (Id INTEGER PRIMARY KEY AUTOINCREMENT,nombre VARCHAR, codigo VARCHAR UNIQUE,image VARCHAR, tipo VARCHAR)");

        Cursor cursor = usuariosSQLite.getData("SELECT * FROM USERS");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PHONE)) {

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                }
            }
        }

        if (savedInstanceState == null){
            if (nombre.length()>0 || codigo.length()>0 || foto.length()>0 || tipouser.length()>0){
                Intent intent = new Intent(this, OpcionesActivity.class);
                startActivity(intent);
                finish();
            }else if (cursor.moveToFirst()){
                Fragment fragment = new LoginForOneTouch();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.contenedor,fragment);
                fragmentTransaction.commit();
            }else{
                Fragment fragment = new LoginFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.contenedor,fragment);
                fragmentTransaction.commit();
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction().commit();
        }else {
            super.onBackPressed();
        }
    }
}
