package com.example.globaltics.nomygroupsa.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.VentaBS.EnviarVentaBS;
import com.example.globaltics.nomygroupsa.R;
import com.kosalgeek.android.md5simply.MD5;

import java.util.Objects;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class VentaBienesServiciosFragment extends Fragment implements View.OnClickListener {


    public VentaBienesServiciosFragment() {
        // Required empty public constructor
    }
    private ImageView imagen;
    private TextView precio,nombre,descripcion;
    private Button comprar;
    private String imagenb,nombreb,preciob,codigob,descripcionb,user,tipo,comprobar;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null){
            imagenb = getArguments().getString("imagen","imagen");
            nombreb = getArguments().getString("nombre","nombre");
            preciob = getArguments().getString("precio","precio");
            codigob = getArguments().getString("codigo","codigo");
            descripcionb = getArguments().getString("descripcion","descripcion");
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_venta_bienes_servicios, container, false);
        imagen = view.findViewById(R.id.imagen);
        precio = view.findViewById(R.id.precio);
        nombre = view.findViewById(R.id.nombrec);
        descripcion = view.findViewById(R.id.descripcion);
        comprar = view.findViewById(R.id.comprar);

        preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        user = preferences.getString("codigo","");
        tipo = MD5.encrypt(preferences.getString("tipouser", ""));
        comprobar = "nao" + tipo + "nao";


        byte[] bytes = Base64.decode(imagenb,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imagen.setImageBitmap(bitmap);
        precio.setText("$"+preciob);
        nombre.setText(nombreb);
        descripcion.setText(descripcionb);

        comprar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.comprar:
                if (Objects.equals(comprobar, "nao91f5167c34c400758115c2a6826ec2e3nao")) {
                    Toast.makeText(getActivity(),"Usted no puede realizar la compra ya que usted es el vendedor",Toast.LENGTH_SHORT).show();
                }else {
                    comprarvbs();
                }
                break;
        }
    }

    private void comprarvbs() {
        String accion = MD5.encrypt("venta");
        new EnviarVentaBS(getActivity(),urla,accion,user,codigob).execute();
    }
}
