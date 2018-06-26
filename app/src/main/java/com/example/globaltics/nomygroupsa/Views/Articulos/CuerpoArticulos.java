package com.example.globaltics.nomygroupsa.Views.Articulos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.ItemClickListener;

/**
 * Created by GlobalTIC's on 5/12/2017.
 */

public class CuerpoArticulos extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ItemClickListener itemClickListener;
    ImageView foto;
    TextView nombre,precio;
    public CuerpoArticulos(View itemView) {
        super(itemView);
        foto = itemView.findViewById(R.id.foto);
        nombre = itemView.findViewById(R.id.nombrec);
        precio = itemView.findViewById(R.id.precio);
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
