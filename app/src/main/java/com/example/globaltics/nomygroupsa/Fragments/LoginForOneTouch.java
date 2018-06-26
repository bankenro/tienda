package com.example.globaltics.nomygroupsa.Fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.SQLite.CustomAdapterSQLite;
import com.example.globaltics.nomygroupsa.Clases.SQLite.Usuarios;
import com.example.globaltics.nomygroupsa.Clases.SQLite.UsuariosSQLite;
import com.example.globaltics.nomygroupsa.R;
import com.kosalgeek.android.md5simply.MD5;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginForOneTouch extends Fragment implements View.OnClickListener{


    public LoginForOneTouch() {
        // Required empty public constructor
    }
    private Button ingresar,registrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login_for_one_touch, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv);
        ingresar = view.findViewById(R.id.ingresar);
        registrar = view.findViewById(R.id.registrar);

        UsuariosSQLite usuariosSQLite = new UsuariosSQLite(getActivity(),"UsersDB.sqlite",null,1);
        ArrayList<Usuarios> usuarios = new ArrayList<>();
        usuarios.clear();
        CustomAdapterSQLite adapterSQLite = new CustomAdapterSQLite(getActivity(),usuarios);

        Cursor cursor = usuariosSQLite.getData("SELECT * FROM USERS");
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String codigo = cursor.getString(2);
            String imagen = cursor.getString(3);
            String tipo = cursor.getString(4);
            usuarios.add(new Usuarios(id,nombre,codigo,imagen,tipo));
        }
        adapterSQLite.notifyDataSetChanged();

        recyclerView.setAdapter(adapterSQLite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ingresar.setOnClickListener(this);
        registrar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ingresar:
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                Fragment fragment1 = new LoginFragment();
                fragmentTransaction1.replace(R.id.contenedor,fragment1);
                fragmentTransaction1.addToBackStack(null).commit();
                break;
            case R.id.registrar:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new RegisterFragment();
                fragmentTransaction.replace(R.id.contenedor,fragment);
                fragmentTransaction.addToBackStack(null).commit();
                break;
        }
    }
}
