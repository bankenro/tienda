package com.example.globaltics.nomygroupsa.Views.Articulos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.globaltics.nomygroupsa.Fragments.VentaBienesServiciosFragment;
import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.ItemClickListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by GlobalTIC's on 5/12/2017.
 */

public class AdapterArticulos extends RecyclerView.Adapter<CuerpoArticulos> {
    private Context c;
    private ArrayList<Articulos> articulos;
    public AdapterArticulos(Context c, ArrayList<Articulos> articulos) {
        this.c = c;
        this.articulos = articulos;
    }

    @Override
    public CuerpoArticulos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_bienes_servicios,parent,false);
        CuerpoArticulos cuerpoArticulos = new CuerpoArticulos(view);
        return cuerpoArticulos;
    }

    @Override
    public void onBindViewHolder(CuerpoArticulos holder, int position) {
        Articulos articulos1 = articulos.get(position);
        holder.nombre.setText(articulos1.getNombre());
        double prec = articulos1.getPrecio();
        holder.precio.setText("$"+String.valueOf(prec));
        holder.foto.setImageBitmap(articulos1.getImagen());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("imagen",articulos.get(position).getFoto());
                bundle.putString("nombre",articulos.get(position).getNombre());
                bundle.putString("precio",String.valueOf(articulos.get(position).getPrecio()));
                bundle.putString("codigo",String.valueOf(articulos.get(position).getId()));
                bundle.putString("descripcion",articulos.get(position).getDescripcion());

                Fragment fragment = new VentaBienesServiciosFragment();

                try {
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = ((FragmentActivity)c).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contenedor,fragment);
                    ((FragmentActivity)c).setTitle("Detalles");
                    fragmentTransaction.addToBackStack(null).commit();
                }catch (ClassCastException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }
}
