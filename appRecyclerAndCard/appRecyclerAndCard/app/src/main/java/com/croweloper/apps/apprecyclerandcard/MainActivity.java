package com.croweloper.apps.apprecyclerandcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.croweloper.apps.apprecyclerandcard.Adaptadores.AnimalesAdapter;
import com.croweloper.apps.apprecyclerandcard.Modelo.Animales;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvDatos;
    private AnimalesAdapter adapter;
    //cola de peticiones
    private RequestQueue mQueue;
    private ArrayList<Animales> vDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvDatos=findViewById(R.id.rvDatos);
        rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter=new AnimalesAdapter(MainActivity.this);
        rvDatos.setAdapter(adapter);
        vDatos=new ArrayList<>();
        //instanciar la cola de peticiones
        mQueue=Volley.newRequestQueue(this);
        ConsumirWS();

    }

    public void ConsumirWS(){
        String url="https://pixabay.com/api/?key=12544769-ce772d6f6df4078b74b23c3cf&q=yellow+flowers&image_type=photo&pretty=true";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject obj=jsonArray.getJSONObject(i);

                        vDatos.add(new Animales(obj.getString("user"),obj.getString("webformatURL")));
                    }
                    adapter.AgregarElementos(vDatos);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);

    }


}
