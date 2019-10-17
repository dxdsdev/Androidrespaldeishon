package pe.edu.idat.semana2.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import java.util.ArrayList;

import pe.edu.idat.semana2.PokemonActivity;
import pe.edu.idat.semana2.R;
import pe.edu.idat.semana2.model.Pokemon;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Pokemon> list;

    public PokemonRecyclerAdapter(Context context) {
        this.context = context;
        list= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pokemon,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.getItems(list.get(i));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgItemPokemon;
        private TextView tvItemPokemon;
        private CardView cardContenedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemPokemon = itemView.findViewById(R.id.imgItemPokemon);
            tvItemPokemon = itemView.findViewById(R.id.tvItemPokemon);
            cardContenedor = itemView.findViewById(R.id.cardContenedor);
        }

        public void getItems(final Pokemon pokemon) {

            Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getId()+".png")
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgItemPokemon);
            tvItemPokemon.setText(pokemon.getNombre());

            cardContenedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context,PokemonActivity.class);
                    intent.putExtra("pokemon",pokemon);
                    context.startActivity(intent);

                }
            });

        }
    }

    public void agregarLista(ArrayList<Pokemon> data){
        list.addAll(data);
        notifyDataSetChanged();
    }
}
