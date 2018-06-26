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

import com.example.globaltics.nomygroupsa.Clases.ConcretarVenta.RecibirConcretarVenta;
import com.example.globaltics.nomygroupsa.R;
import com.kosalgeek.android.md5simply.MD5;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class
ConcretarVenta extends Fragment implements View.OnClickListener {


    public ConcretarVenta() {
        // Required empty public constructor
    }
    private ImageView imagen;
    private TextView precio,nombre,descripcion,fecha,nombrec,correo,telefono;
    private Button comprar;
    private String imagenb,nombreb,preciob,cdgservb,cdgprevb,descripcionb,telefonob,correob,nombrecb,fechab,user;
    private SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_concretar_venta, container, false);
        if (getArguments()!=null){
            imagenb = getArguments().getString("imagen","imagen");
            nombreb = getArguments().getString("nombre","nombre");
            preciob = getArguments().getString("precio","precio");
            cdgservb = getArguments().getString("cdgserv","cdgserv");
            cdgprevb = getArguments().getString("cdgprev","cdgprev");
            descripcionb = getArguments().getString("descripcion","descripcion");
            telefonob = getArguments().getString("telefono","telefono");
            correob = getArguments().getString("correo","correo");
            nombrecb = getArguments().getString("nombrec","nombrec");
            fechab = getArguments().getString("fecha","fecha");

        }
        imagen = view.findViewById(R.id.imagen);
        precio = view.findViewById(R.id.precio);
        nombre = view.findViewById(R.id.nombre);
        descripcion = view.findViewById(R.id.descripcion);
        fecha = view.findViewById(R.id.fecha);
        nombrec = view.findViewById(R.id.nombrec);
        correo = view.findViewById(R.id.correo);
        telefono = view.findViewById(R.id.telefono);
        comprar = view.findViewById(R.id.comprar);

        preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        user = preferences.getString("codigo","");


        byte[] bytes = Base64.decode(imagenb,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imagen.setImageBitmap(bitmap);
        precio.setText("$"+preciob);
        nombre.setText(nombreb);
        descripcion.setText(descripcionb);
        fecha.setText(fechab);
        nombrec.setText(nombrecb);
        correo.setText(correob);
        telefono.setText(telefonob);

        comprar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.comprar:
                vender1();
                break;
        }
    }

    private void vender1() {
        String accion = MD5.encrypt("concretarv");
        new RecibirConcretarVenta(getActivity(),urla,accion,cdgprevb,user,cdgservb).execute();
    }
}
