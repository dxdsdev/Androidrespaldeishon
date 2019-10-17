package com.empresa.reciclyerviewrepaso.Adapters;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.empresa.reciclyerviewrepaso.Modelo.Pokemon;
import com.empresa.reciclyerviewrepaso.PokemonActivity;
import com.empresa.reciclyerviewrepaso.R;

import java.util.ArrayList;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonRecyclerAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataPokemon;
    private Context context;

    public PokemonRecyclerAdapter(Context context) {
        this.context = context;
        dataPokemon=new ArrayList<>();
    }

    @NonNull
    @Override
    public PokemonRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonRecyclerAdapter.ViewHolder viewHolder, int i) {

        final Pokemon objPokemon=dataPokemon.get(i);
        viewHolder.tvItemPokemon.setText(objPokemon.getNombre());
        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+objPokemon.getId()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivItemPokemon);
        viewHolder.contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PokemonActivity.class);
                intent.putExtra("pokemon",objPokemon);
                context.startActivity(intent);;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataPokemon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivItemPokemon;
        private TextView tvItemPokemon;
        private CardView contenedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItemPokemon=itemView.findViewById(R.id.ivItemPokemon);
            tvItemPokemon=itemView.findViewById(R.id.tvItemPokemon);
            contenedor=itemView.findViewById(R.id.contenedor);
        }
    }

    public void agregarLista(ArrayList<Pokemon> listaPokemon){
        dataPokemon.addAll(listaPokemon);
        notifyDataSetChanged();
    }

}
