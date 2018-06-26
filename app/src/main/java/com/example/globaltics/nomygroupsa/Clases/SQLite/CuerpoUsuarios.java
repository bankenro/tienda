package com.example.globaltics.nomygroupsa.Clases.SQLite;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.ItemClickListener;

/**
 * Created by GlobalTIC's on 3/12/2017.
 */

public class CuerpoUsuarios extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ItemClickListener itemClickListener;
    ImageView foto;
    ImageButton menu;
    TextView nombre, tipo;
    public CuerpoUsuarios(View itemView) {
        super(itemView);
        foto = itemView.findViewById(R.id.foto);
        menu = itemView.findViewById(R.id.menu);
        nombre = itemView.findViewById(R.id.nombrec);
        tipo = itemView.findViewById(R.id.precio);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }
}
