package pe.edu.idat.semana2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.idat.semana2.adapters.PokemonRecyclerAdapter;
import pe.edu.idat.semana2.model.Pokemon;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPokemon;

    private String url = "https://pokeapi.co/api/v2/pokemon/";
    private boolean carga;
    private String nexturl;

    private PokemonRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new PokemonRecyclerAdapter(this);
        recyclerViewPokemon = findViewById(R.id.rvPokemon);

    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewPokemon.setAdapter(adapter);
        recyclerViewPokemon.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
        recyclerViewPokemon.setHasFixedSize(true);
        recyclerViewPokemon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int itemsVisibles = recyclerViewPokemon.getLayoutManager().getChildCount();
                    int itemstotales = recyclerViewPokemon.getLayoutManager().getItemCount();
                    int primerItemVisible = ((GridLayoutManager) recyclerViewPokemon.getLayoutManager()).findFirstVisibleItemPosition();
                    if(carga){
                        if(itemsVisibles + primerItemVisible >=itemstotales){
                            carga=false;
                            getObtenerPokemon(nexturl);
                        }
                    }
                }
            }
        });
        getObtenerPokemon(url);
    }

    public void getObtenerPokemon(String urll){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, urll, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            nexturl = response.getString("next");
                            JSONArray jsonArray = response.getJSONArray("results");
                            if(jsonArray.length() > 0){
                                ArrayList<Pokemon> list = new ArrayList<>();
                                carga = true;
                                final String url,nombre;
                                for (int i=0;i < jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final Pokemon nuevoPokemon = new Pokemon(
                                            jsonObject.getString("name"),
                                            jsonObject.getString("url")
                                    );
                                    list.add(nuevoPokemon);
                                }
                                adapter.agregarLista(list);
                            }


                        }catch (Exception e){
                            carga = false;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}
