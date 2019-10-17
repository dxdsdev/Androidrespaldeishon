package com.croweloper.appfragments;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity  implements  Fragment_1.OnFragmentInteractionListener,Fragment_2.OnFragmentInteractionListener{

    Button btnf1,btnf2;
    FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnf1=findViewById(R.id.btnFragment1);
        btnf2=findViewById(R.id.btnFragment2);
        fl=findViewById(R.id.flContenedor);
        final Fragment_1 fr1=new Fragment_1();
        getSupportFragmentManager().beginTransaction().add(R.id.flContenedor,fr1).commit();
        btnf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_1 frag1=new Fragment_1();
                FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.flContenedor,frag1);
                trans.commit();
            }
        });
        btnf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_2 frag2=new Fragment_2();
                getSupportFragmentManager().beginTransaction().replace(R.id.flContenedor,frag2).commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
