package com.example.globaltics.nomygroupsa.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.AgregarBS.EnviarAgregarBS;
import com.example.globaltics.nomygroupsa.R;
import com.kosalgeek.android.md5simply.MD5;

import java.io.ByteArrayOutputStream;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.urla;
import static com.example.globaltics.nomygroupsa.Fragments.RegisterFragment.REQUEST_IMAGE_CAPTURE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Vender extends Fragment implements View.OnClickListener {


    public Vender() {
        // Required empty public constructor
    }
    private ImageView foto;
    private String accion,user;
    private EditText nombre,descripcion,precio;
    private Button registrar;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vender, container, false);
        if (getArguments() != null){
            accion = MD5.encrypt(getArguments().getString("accion","accion"));
        }

        foto = view.findViewById(R.id.foto);
        nombre = view.findViewById(R.id.nombrec);
        descripcion = view.findViewById(R.id.descripcion);
        precio = view.findViewById(R.id.precio);
        registrar = view.findViewById(R.id.registrar);


        preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        user = preferences.getString("codigo","");

        foto.setOnClickListener(this);
        registrar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrar:
                registrarbs();
                break;
            case R.id.foto:
                sacarfoto();
                break;
        }
    }
    private void sacarfoto() {
        Intent tomarfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (tomarfoto.resolveActivity(getActivity().getPackageManager())!=null){
            startActivityForResult(tomarfoto,REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            assert bundle != null;
            Bitmap imagen = (Bitmap) bundle.get("data");
            foto.setImageBitmap(imagen);
        }
    }

    private void registrarbs() {
        String nomb = nombre.getText().toString().trim();
        String descr = descripcion.getText().toString().trim();
        String pre = precio.getText().toString().trim();
        foto.buildDrawingCache();
        Bitmap bitmap = foto.getDrawingCache();
        String foto1 = convertirimagen(bitmap);
        if (nomb.length() <= 0 || descr.length() <= 0 || pre.length() <= 0 || foto1.length() <= 0 ){
            Toast.makeText(getActivity(),"Ingrese todos los datos",Toast.LENGTH_SHORT).show();
        }else {
            new EnviarAgregarBS(getActivity(),urla,accion,nomb,descr,pre,foto1,user).execute();
        }
    }
    private String convertirimagen(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,70,stream);
        byte[] bytes = stream.toByteArray();
        return Base64.encodeToString(bytes,Base64.NO_WRAP);
    }
}
