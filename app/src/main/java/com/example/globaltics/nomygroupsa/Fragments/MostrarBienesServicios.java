package com.example.globaltics.nomygroupsa.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.Articulos.RecibirArticulos;
import com.kosalgeek.android.md5simply.MD5;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarBienesServicios extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    public MostrarBienesServicios() {
        // Required empty public constructor
    }
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private String accion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mostrar_bienes_servicios, container, false);

        if (getArguments() != null){
            accion = MD5.encrypt(getArguments().getString("accion","accion"));
        }

        srl = view.findViewById(R.id.srl);
        rv = view.findViewById(R.id.rv);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rv.setLayoutManager(layoutManager);

        srl.setOnRefreshListener(this);
        srl.post(new Runnable() {
            @Override
            public void run() {
                descargar();
            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
        descargar();
    }

    private void descargar() {
        srl.setRefreshing(true);
        new RecibirArticulos(getActivity(),urla,accion,rv,srl).execute();
    }
}
