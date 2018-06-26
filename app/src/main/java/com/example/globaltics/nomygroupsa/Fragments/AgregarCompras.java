package com.example.globaltics.nomygroupsa.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.globaltics.nomygroupsa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarCompras extends Fragment implements View.OnClickListener{


    public AgregarCompras() {
        // Required empty public constructor
    }
    private ImageView foto;
    private EditText nombre,descripcion,precio;
    private Button registrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_compras, container, false);
        foto = view.findViewById(R.id.foto);
        nombre = view.findViewById(R.id.nombrec);
        descripcion = view.findViewById(R.id.descripcion);
        precio = view.findViewById(R.id.precio);
        registrar = view.findViewById(R.id.registrar);

        registrar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrar:
                registrarcompras();
                break;
        }
    }

    private void registrarcompras() {

    }
}
