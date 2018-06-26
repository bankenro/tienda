package com.example.globaltics.nomygroupsa.Views.PreCompras;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.ItemClickListener;

/**
 * Created by GlobalTIC's on 14/01/2018.
 */

public class CuerpoPreCompras extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ItemClickListener itemClickListener;
    public ImageView foto;
    public TextView nombre;
    public TextView precio;
    public TextView fecha;
    public CuerpoPreCompras(View itemView) {
        super(itemView);
        foto = itemView.findViewById(R.id.foto);
        nombre = itemView.findViewById(R.id.nombrec);
        precio = itemView.findViewById(R.id.precio);
        fecha = itemView.findViewById(R.id.fecha);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
