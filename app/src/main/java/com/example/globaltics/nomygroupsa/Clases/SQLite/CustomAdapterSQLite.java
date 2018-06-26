package com.example.globaltics.nomygroupsa.Clases.SQLite;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globaltics.nomygroupsa.Clases.Login.EnviarRecibirLogin;
import com.example.globaltics.nomygroupsa.R;
import com.example.globaltics.nomygroupsa.Views.ItemClickListener;
import com.kosalgeek.android.md5simply.MD5;

import java.util.ArrayList;

import static com.example.globaltics.nomygroupsa.Fragments.LoginFragment.urla;

/*
 * Created by GlobalTIC's on 2/12/2017.
 */

public class CustomAdapterSQLite extends RecyclerView.Adapter<CuerpoUsuarios> {
    private Context c;
    private ArrayList<Usuarios>usuarios;
    public CustomAdapterSQLite(Context c, ArrayList<Usuarios> usuarios) {
        this.c = c;
        this.usuarios = usuarios;
    }


    @Override
    public CuerpoUsuarios onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_usuarios,parent,false);
        CuerpoUsuarios cuerpoUsuarios = new CuerpoUsuarios(view);
        return cuerpoUsuarios;
    }

    @Override
    public void onBindViewHolder(CuerpoUsuarios holder, final int position) {
        final Usuarios usuarios1 = usuarios.get(position);

        holder.nombre.setText(usuarios1.getNombre());
        holder.tipo.setText(usuarios1.getTipo());
        String imagen = usuarios1.getImagen();
        byte[] bytes = Base64.decode(imagen,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        holder.foto.setImageBitmap(bitmap);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Dialog d = new Dialog(c);
                d.setContentView(R.layout.dialog_login);
                TextView nombre = d.findViewById(R.id.nombrec);
                TextView tipo = d.findViewById(R.id.precio);
                final EditText password = d.findViewById(R.id.password);
                ImageView imagen = d.findViewById(R.id.imagen);
                Button ingresar = d.findViewById(R.id.ingresar);

                nombre.setText(usuarios.get(position).getNombre());
                tipo.setText(usuarios.get(position).getTipo());
                String imagen1 = usuarios.get(position).getImagen();
                byte[] bytes = Base64.decode(imagen1,Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imagen.setImageBitmap(bitmap);

                final String codigo = usuarios.get(position).getCodigo();
                ingresar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String password1 = password.getText().toString().trim();
                        String accion = MD5.encrypt("login");
                        if (password1.length()<=0 || codigo.length() <= 0){
                            Toast.makeText(c,"Ingrese sus contraseña",Toast.LENGTH_SHORT).show();
                        }else {
                            new EnviarRecibirLogin(c,urla,accion,codigo,password1).execute();
                        }
                    }
                });
                Window window = d.getWindow();
                assert window != null;
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                d.show();

            }
        });
        final UsuariosSQLite usuariosSQLite = new UsuariosSQLite(c,"UsersDB.sqlite",null,1);
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(c,view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_login,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int i = menuItem.getItemId();
                        if (i == R.id.eliminar){
                            try {
                                usuariosSQLite.deleteData(usuarios.get(position).getCodigo());
                                Toast.makeText(c,"Usuario Eliminado "+usuarios.get(position).getCodigo(),Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            usuarios.remove(position);
                            notifyDataSetChanged();
                            return true;
                        }else{
                            return onMenuItemClick(menuItem);
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}
