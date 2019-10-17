package com.croweloper.apps.apprecyclerandcard.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croweloper.apps.apprecyclerandcard.DetalleActivity;
import com.croweloper.apps.apprecyclerandcard.Modelo.Animales;
import com.croweloper.apps.apprecyclerandcard.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnimalesAdapter extends RecyclerView.Adapter<AnimalesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Animales> lista;

    public AnimalesAdapter(Context context) {
        this.context = context;
        lista=new ArrayList<>();
    }


    @NonNull
    @Override
    public AnimalesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista=LayoutInflater.from(context).inflate(R.layout.item_detail,viewGroup,false);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalesAdapter.ViewHolder viewHolder, int i) {
        final Animales item=lista.get(i);
        viewHolder.tvNombre.setText("Creado por : "+item.getNombre());
        Picasso.with(context).load(item.getUrlImage()).fit().centerInside().into(viewHolder.ivImagen);

        viewHolder.cContenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intDetalle =new Intent(context,DetalleActivity.class);
                intDetalle.putExtra("objanimal",item);
                context.startActivity(intDetalle);
            }
        });

    }

    public void AgregarElementos(ArrayList<Animales> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImagen;
        TextView tvNombre;
        CardView cContenedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen=itemView.findViewById(R.id.ivImagen);
            tvNombre=itemView.findViewById(R.id.tvNombre);
            cContenedor=itemView.findViewById(R.id.cvContenedor);


        }
    }
}
