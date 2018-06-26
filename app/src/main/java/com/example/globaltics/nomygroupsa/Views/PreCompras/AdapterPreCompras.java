package com.example.globaltics.nomygroupsa.Views.PreCompras;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.globaltics.nomygroupsa.Fragments.ConcretarCompra;
import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.ItemClickListener;
import com.kosalgeek.android.md5simply.MD5;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.telefonon;

/**
 * Created by GlobalTIC's on 14/01/2018.
 */

public class AdapterPreCompras extends RecyclerView.Adapter<CuerpoPreCompras> {
    private Context c;
    private ArrayList<PreCompras> preCompras;
    private SharedPreferences preferences;
    public AdapterPreCompras(Context c, ArrayList<PreCompras> preCompras) {
        this.c = c;
        this.preCompras = preCompras;
        this.preferences = c.getSharedPreferences("datos",Context.MODE_PRIVATE);
    }


    @Override
    public CuerpoPreCompras onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_preventas,parent,false);
        CuerpoPreCompras cuerpoPreCompras = new CuerpoPreCompras(view);
        return cuerpoPreCompras;
    }

    @Override
    public void onBindViewHolder(CuerpoPreCompras holder, int position) {
        PreCompras preCompras1 = preCompras.get(position);
        holder.nombre.setText(preCompras1.getNombre());
        double prec = preCompras1.getPrecio();
        holder.precio.setText("$"+String.valueOf(prec));
        holder.foto.setImageBitmap(preCompras1.getImagen());
        holder.fecha.setText(preCompras1.getFecha());

        String tipo = MD5.encrypt(preferences.getString("tipouser", ""));
        String comprobar = "nao"+tipo+"nao";
        if (Objects.equals(comprobar,"nao91f5167c34c400758115c2a6826ec2e3nao")){
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("imagen",preCompras.get(position).getFoto());
                    bundle.putString("nombre",preCompras.get(position).getNombre());
                    bundle.putString("precio",String.valueOf(preCompras.get(position).getPrecio()));
                    bundle.putString("cdgcom",String.valueOf(preCompras.get(position).getIdcom()));
                    bundle.putString("descripcion",preCompras.get(position).getDescripcion());
                    bundle.putString("telefono",String.valueOf(preCompras.get(position).getTelefono()));
                    bundle.putString("correo",preCompras.get(position).getCorreo());
                    bundle.putString("nombrec",preCompras.get(position).getNombrec());
                    bundle.putString("fecha",preCompras.get(position).getFecha());

                    Fragment fragment = new ConcretarCompra();

                    try {
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = ((FragmentActivity)c).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.contenedor,fragment);
                        ((FragmentActivity)c).setTitle("Concretar Compra");
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
        return preCompras.size();
    }
}