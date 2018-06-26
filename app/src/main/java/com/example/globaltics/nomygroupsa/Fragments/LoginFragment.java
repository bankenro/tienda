package com.example.globaltics.nomygroupsa.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.Login.EnviarRecibirLogin;
import com.example.globaltics.nomygroupsa.R;
import com.kosalgeek.android.md5simply.MD5;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{


    public LoginFragment() {
        // Required empty public constructor
    }
    public static String urla = "http://192.168.1.38/ngcsa/entrada.php";
    public static int telefonon = 956365748;
    private EditText correo, contraseña;
    private Button ingresar, registrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        correo = view.findViewById(R.id.correo);
        contraseña = view.findViewById(R.id.contraseña);
        ingresar = view.findViewById(R.id.ingresar);
        registrar = view.findViewById(R.id.registrar);

        ingresar.setOnClickListener(LoginFragment.this);
        registrar.setOnClickListener(LoginFragment.this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ingresar:
                String user = correo.getText().toString();
                String contra = contraseña.getText().toString();
                String accion = MD5.encrypt("login");
                if (user.length()<=0 || contra.length()<=0){
                    Toast.makeText(getActivity(),"Rellene los campos",Toast.LENGTH_SHORT).show();
                }else {
                    comprobarloogin(user,contra,accion);
                }

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

    private void comprobarloogin(String user, String contra, String accion) {
        new EnviarRecibirLogin(getActivity(),urla,accion,user,contra).execute();
    }
}
