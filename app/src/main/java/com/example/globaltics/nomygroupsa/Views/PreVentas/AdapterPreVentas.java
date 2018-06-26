package com.example.globaltics.nomygroupsa.Views.PreVentas;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globaltics.nomygroupsa.Fragments.ConcretarVenta;
import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.ItemClickListener;
import com.kosalgeek.android.md5simply.MD5;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.telefonon;

/**
 * Created by GlobalTIC's on 10/01/2018.
 */

public class AdapterPreVentas extends RecyclerView.Adapter<CuerpoPreVentas> {
    private Context c;
    private ArrayList<PreVentas> preVentas;
    private SharedPreferences preferences;
    public AdapterPreVentas(Context c, ArrayList<PreVentas> preVentas) {
        this.c = c;
        this.preVentas = preVentas;
    }

    @Override
    public CuerpoPreVentas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_preventas,parent,false);
        CuerpoPreVentas cuerpoPreVentas = new CuerpoPreVentas(view);
        return cuerpoPreVentas;
    }

    @Override
    public void onBindViewHolder(CuerpoPreVentas holder, int position) {
        PreVentas preVentas1 = preVentas.get(position);
        holder.nombre.setText(preVentas1.getNombre());
        double prec = preVentas1.getPrecio();
        holder.precio.setText("$"+String.valueOf(prec));
        holder.foto.setImageBitmap(preVentas1.getImagen());
        holder.fecha.setText(preVentas1.getFecha());

        preferences = c.getSharedPreferences("datos",Context.MODE_PRIVATE);
        String tipo = MD5.encrypt(preferences.getString("tipouser", ""));
        String comprobar = "nao"+tipo+"nao";
        if (Objects.equals(comprobar,"nao91f5167c34c400758115c2a6826ec2e3nao")){
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("imagen",preVentas.get(position).getFoto());
                    bundle.putString("nombre",preVentas.get(position).getNombre());
                    bundle.putString("precio",String.valueOf(preVentas.get(position).getPrecio()));
                    bundle.putString("cdgserv",String.valueOf(preVentas.get(position).getIdserv()));
                    bundle.putString("cdgprev",String.valueOf(preVentas.get(position).getIdprev()));
                    bundle.putString("descripcion",preVentas.get(position).getDescripcion());
                    bundle.putString("telefono",String.valueOf(preVentas.get(position).getTelefono()));
                    bundle.putString("correo",preVentas.get(position).getCorreo());
                    bundle.putString("nombrec",preVentas.get(position).getNombrec());
                    bundle.putString("fecha",preVentas.get(position).getFecha());

                    Fragment fragment = new ConcretarVenta();

                    try {
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = ((FragmentActivity)c).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.contenedor,fragment);
                        ((FragmentActivity)c).setTitle("Concretar Venta");
                        fragmentTransaction.addToBackStack(null).commit();
                    }catch (ClassCastException e){
                        e.printStackTrace();
                    }
                }
            });
        }else {
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int pos) {
                    Dialog d = new Dialog(c);
                    d.setContentView(R.layout.dialog_llamar);
                    Button llamar = d.findViewById(R.id.llamar);


                    llamar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:" + telefonon));

                            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }
                            c.startActivity(i);
                        }
                    });

                    Window window = d.getWindow();
                    assert window != null;
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.CENTER);
                    d.show();
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return preVentas.size();
    }
}
