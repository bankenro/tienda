package com.example.globaltics.nomygroupsa.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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

import com.example.globaltics.nomygroupsa.Clases.Register.EnviarRecibirRegister;
import com.example.globaltics.nomygroupsa.R;
import com.kosalgeek.android.md5simply.MD5;

import java.io.ByteArrayOutputStream;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener{


    public RegisterFragment() {
        // Required empty public constructor
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView foto;
    private EditText id,nombres,apellidop,apellidom,correo,contraseña,telefono;
    private Button registrar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        foto = view.findViewById(R.id.foto);
        id = view.findViewById(R.id.id);
        nombres = view.findViewById(R.id.nombres);
        apellidop = view.findViewById(R.id.apellidop);
        apellidom = view.findViewById(R.id.apellidom);
        correo = view.findViewById(R.id.correo);
        contraseña = view.findViewById(R.id.contraseña);
        telefono = view.findViewById(R.id.telefono);
        registrar = view.findViewById(R.id.registrar);

        registrar.setOnClickListener(RegisterFragment.this);
        foto.setOnClickListener(RegisterFragment.this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrar:
                fregistrar();
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
            Bitmap imagen = (Bitmap) bundle.get("data");
            foto.setImageBitmap(imagen);
        }
    }

    private void fregistrar() {
        String id1 = id.getText().toString();
        String nombres1 = nombres.getText().toString();
        String apellidop1 = apellidop.getText().toString();
        String apellidom1 = apellidom.getText().toString();
        String correo1 = correo.getText().toString();
        String contraseña1 = contraseña.getText().toString();
        String telefono1 = telefono.getText().toString();
        String accion = MD5.encrypt("registrar");
        foto.buildDrawingCache();
        Bitmap bitmap = foto.getDrawingCache();
        String foto1 = convertirimagen(bitmap);
        if (id1.length()<=0 || nombres1.length()<=0 || apellidop1.length()<=0 || apellidom1.length()<=0 || correo1.length()<=0 || contraseña1.length()<=0 || telefono1.length()<=0 || foto1.length() <=0){
            Toast.makeText(getActivity(),"LLene todos los campos",Toast.LENGTH_SHORT).show();
        }else {
            new EnviarRecibirRegister(getActivity(),urla,accion,id1,nombres1,apellidop1,apellidom1,
                    correo1,contraseña1,telefono1,foto1).execute();
        }
    }

    private String convertirimagen(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,70,stream);
        byte[] bytes = stream.toByteArray();
        return Base64.encodeToString(bytes,Base64.NO_WRAP);
    }
}
