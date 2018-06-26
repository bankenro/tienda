package com.example.globaltics.nomygroupsa.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globaltics.nomygroupsa.Fragments.AgregarBienesServicios;
import com.example.globaltics.nomygroupsa.Fragments.MostrarBienesServicios;
import com.example.globaltics.nomygroupsa.Fragments.MostrarPostCompras;
import com.example.globaltics.nomygroupsa.Fragments.MostrarPostVenta;
import com.example.globaltics.nomygroupsa.Fragments.MostrarPreCompras;
import com.example.globaltics.nomygroupsa.Fragments.MostrarPreVentas;
import com.example.globaltics.nomygroupsa.Fragments.Vender;
import com.example.globaltics.nomygroupsa.R;

public class OpcionesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences preferences = getSharedPreferences("datos",MODE_PRIVATE);
        String nivel = preferences.getString("tipouser","");

        switch (nivel){
            case "administrador":
                navigationView.getMenu().setGroupVisible(R.id.administrador,true);
                break;
            case "usuario":
                navigationView.getMenu().setGroupVisible(R.id.cliente,true);
                break;
        }
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView nombre = view.findViewById(R.id.nombrec);
        TextView tipo = view.findViewById(R.id.tipo);
        ImageView foto = view.findViewById(R.id.foto);

        nombre.setText(preferences.getString("nombrec",""));
        String foto1 = preferences.getString("foto","");
        byte [] bytes = Base64.decode(foto1,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        foto.setImageBitmap(bitmap);
        tipo.setText(preferences.getString("tipouser",""));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START) ) {
            drawer.closeDrawer(GravityCompat.START);
            if (getSupportFragmentManager().getBackStackEntryCount() > 0){
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().commit();
            }else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean fragmentTransaction = false;
        Fragment fragment = null;
        Intent intent;
        Bundle bundle = new Bundle();
        switch (id){
            //administrador
            //agregar bienes servicios
            case R.id.agart:
                fragment = new AgregarBienesServicios();
                fragmentTransaction = true;
                bundle.putString("accion","agrbs");
                fragment.setArguments(bundle);
                break;
            //mostrar articulos
            case R.id.vart:
                fragment = new MostrarBienesServicios();
                fragmentTransaction = true;
                bundle.putString("accion","articulos");
                fragment.setArguments(bundle);
                break;
            //mostrar servicios
            case R.id.vserv:
                fragment = new MostrarBienesServicios();
                fragmentTransaction = true;
                bundle.putString("accion","servicios");
                fragment.setArguments(bundle);
                break;
            //ver pre-ventas
            case R.id.vpreven:
                fragment = new MostrarPreVentas();
                fragmentTransaction = true;
                bundle.putString("accion","preventasa");
                fragment.setArguments(bundle);
                break;
            //ver ventas
            case R.id.vposven:
                fragment = new MostrarPostVenta();
                fragmentTransaction = true;
                bundle.putString("accion","ventasa");
                fragment.setArguments(bundle);
                break;
            //ver pre-compras
            case R.id.vprecom:
                fragment = new MostrarPreCompras();
                fragmentTransaction = true;
                bundle.putString("accion","precomprasa");
                fragment.setArguments(bundle);
                break;
            //ver compras
            case R.id.vposcom:
                fragment = new MostrarPostCompras();
                fragmentTransaction = true;
                bundle.putString("accion","comprasa");
                fragment.setArguments(bundle);
                break;
            //clientes
            //vender articulos
            case R.id.venart:
                fragment = new Vender();
                fragmentTransaction = true;
                bundle.putString("accion","vendart");
                fragment.setArguments(bundle);
                break;
            //ver articulos
            case R.id.verart:
                fragment = new MostrarBienesServicios();
                fragmentTransaction = true;
                bundle.putString("accion","articulos");
                fragment.setArguments(bundle);
                break;
            //ver servicios
            case R.id.verserv:
                fragment = new MostrarBienesServicios();
                fragmentTransaction = true;
                bundle.putString("accion","servicios");
                fragment.setArguments(bundle);
                break;
            //ver mis pre-compras
            case R.id.vermiprecom:
                fragment = new MostrarPreVentas();
                fragmentTransaction = true;
                bundle.putString("accion","preventasu");
                fragment.setArguments(bundle);
                break;
            //ver mis pre-ventas
            case R.id.vermipreven:
                fragment = new MostrarPreCompras();
                fragmentTransaction = true;
                bundle.putString("accion","precomprasu");
                fragment.setArguments(bundle);
                break;
            //ver mis compras
            case R.id.vermicom:
                fragment = new MostrarPostVenta();
                fragmentTransaction = true;
                bundle.putString("accion","ventasu");
                fragment.setArguments(bundle);
                break;
            //ver mis ventas
            case R.id.vermiven:
                fragment = new MostrarPostCompras();
                fragmentTransaction = true;
                bundle.putString("accion","comprasu");
                fragment.setArguments(bundle);
                break;
            //contacto
            case R.id.contacto:
                Uri uri = Uri.parse("https://www.naomygroup.com/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            //cerrar
            case R.id.cerrar:
                SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                intent = new Intent(OpcionesActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
        }

        if (fragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
