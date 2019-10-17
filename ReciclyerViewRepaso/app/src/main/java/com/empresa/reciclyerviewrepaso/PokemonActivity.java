package com.empresa.reciclyerviewrepaso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.empresa.reciclyerviewrepaso.Modelo.Pokemon;

public class PokemonActivity extends AppCompatActivity {

    private TextView tvDetPokemon;
    private ImageView ivDetPokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        ivDetPokemon=findViewById(R.id.ivDetPokemon);
        tvDetPokemon=findViewById(R.id.tvDetPokemon);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Pokemon objPkemon= getIntent().getParcelableExtra("pokemon");
        tvDetPokemon.setText(objPkemon.getNombre());
        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+objPkemon.getId()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivDetPokemon);

    }
}
