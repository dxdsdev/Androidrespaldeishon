package com.croweloper.apps.apprecyclerandcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.croweloper.apps.apprecyclerandcard.Modelo.Animales;
import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    private ImageView ivAnimal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ivAnimal=findViewById(R.id.ivImagenDetalle);
        if (getIntent().hasExtra("objanimal")){
            Animales obj=getIntent().getParcelableExtra("objanimal");
            Picasso.with(getApplicationContext()).load(obj.getUrlImage()).centerCrop().fit().into(ivAnimal);
        }
    }
}
