package pe.edu.idat.semana2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import pe.edu.idat.semana2.model.Pokemon;

public class PokemonActivity extends AppCompatActivity {

    private ImageView imgPokemon;
    private TextView tvPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        imgPokemon = findViewById(R.id.imgPokemon);
        tvPokemon = findViewById(R.id.tvPokemon);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Pokemon objPkemon= getIntent().getParcelableExtra("pokemon");
        tvPokemon.setText(objPkemon.getNombre());
        Glide.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+objPkemon.getId()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgPokemon);

    }
}
