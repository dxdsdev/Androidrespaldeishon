package com.empresa.reciclyerviewrepaso;

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
import com.empresa.reciclyerviewrepaso.Adapters.PokemonRecyclerAdapter;
import com.empresa.reciclyerviewrepaso.Modelo.Pokemon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String urlPokemon="https://pokeapi.co/api/v2/pokemon/";
    private RecyclerView rvPokemon;
    private PokemonRecyclerAdapter adapter;
    private boolean puedecargar;
    private String nextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPokemon=findViewById(R.id.rvPokemon);
        adapter=new PokemonRecyclerAdapter(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        rvPokemon.setAdapter(adapter);
        rvPokemon.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
        rvPokemon.setHasFixedSize(true);
        rvPokemon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //super.onScrollStateChanged(recyclerView, newState);
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int itemsVisibles = rvPokemon.getLayoutManager().getChildCount();
                    int itemstotales = rvPokemon.getLayoutManager().getItemCount();
                    int primerItemVisible = ((GridLayoutManager) rvPokemon.getLayoutManager()).findFirstVisibleItemPosition();
                    if(puedecargar){
                        if(itemsVisibles + primerItemVisible >=itemstotales){
                            puedecargar=false;
                            getObtenerPokemon(nextUrl);
                        }
                    }
                }
            }
        });
        getObtenerPokemon(urlPokemon);
    }

    public void getObtenerPokemon(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    nextUrl=response.getString("next");
                    JSONArray jsonArray=response.getJSONArray("results");
                    if (jsonArray.length()>0){
                        ArrayList<Pokemon> listPokemon=new ArrayList<>();
                        puedecargar=true;
                        //final String url=
                        for (int i=0;i<jsonArray.length();i++){

                            JSONObject jsonObjectPokemon=jsonArray.getJSONObject(i);
                            final Pokemon nuevoPokemon=new Pokemon(jsonObjectPokemon.getString("name"),jsonObjectPokemon.getString("url"));
                            listPokemon.add(nuevoPokemon);

                        }

                        adapter.agregarLista(listPokemon);

                    }

                } catch (Exception ex) {
                    puedecargar=false;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
