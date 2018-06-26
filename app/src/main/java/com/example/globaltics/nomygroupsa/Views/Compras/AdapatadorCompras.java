package com.example.globaltics.nomygroupsa.Views.Compras;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
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

import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.ItemClickListener;
import com.example.globaltics.nomygroupsa.Views.PreVentas.CuerpoPreVentas;
import com.example.globaltics.nomygroupsa.Views.Ventas.PostVenta;
import com.kosalgeek.android.md5simply.MD5;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.telefonon;

/**
 * Created by GlobalTIC's on 24/12/2017.
 */

public class AdapatadorCompras extends RecyclerView.Adapter<CuerpoPreVentas> {
    private Context c;
    private ArrayList<Compras> compras;
    private SharedPreferences preferences;

    public AdapatadorCompras(Context c, ArrayList<Compras> compras) {
        this.c = c;
        this.compras = compras;
    }

    @Override
    public CuerpoPreVentas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_preventas, parent, false);
        CuerpoPreVentas cuerpoPreVentas = new CuerpoPreVentas(view);
        return cuerpoPreVentas;
    }

    @Override
    public void onBindViewHolder(CuerpoPreVentas holder, int position) {
        Compras compras1 = compras.get(position);
        holder.nombre.setText(compras1.getNombre());
        double prec = compras1.getPrecio();
        holder.precio.setText("$" + String.valueOf(prec));
        holder.foto.setImageBitmap(compras1.getImagen());
        holder.fecha.setText(compras1.getFecha());

        preferences = c.getSharedPreferences("datos", Context.MODE_PRIVATE);
        String tipo = MD5.encrypt(preferences.getString("tipouser", ""));
        final String comprobar = "nao" + tipo + "nao";

        if (Objects.equals(comprobar, "nao91f5167c34c400758115c2a6826ec2e3nao")) {
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Dialog d = new Dialog(c);
                    d.setContentView(R.layout.dialog_llamar);
                    TextView nombre = d.findViewById(R.id.nombre);
                    ImageView imagen = d.findViewById(R.id.imagen);
                    Button llamar = d.findViewById(R.id.llamar);

                    nombre.setText(compras.get(position).getNombrec());
                    String imagen1 = compras.get(position).getFotou();
                    byte[] bytes = Base64.decode(imagen1, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imagen.setImageBitmap(bitmap);
                    final int telefono = compras.get(position).getTelefono();

                    llamar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:" + telefono));

                            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }
                            c.startActivity(i);
                        }
                    });

                    Window window = d.getWindow();
                    assert window != null;
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.CENTER);
                    d.show();

                }
            });
        } else {
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int position) {
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
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.CENTER);
                    d.show();

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return compras.size();
    }
}
