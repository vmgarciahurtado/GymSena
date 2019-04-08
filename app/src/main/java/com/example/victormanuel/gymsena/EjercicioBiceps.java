package com.example.victormanuel.gymsena;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EjercicioBiceps extends AppCompatActivity {


    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;
    TextView txt6;
    TextView txt7;
    TextView txt8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_biceps);

        //FUENTES PARA LAS GIFS
        txt1 = (TextView) findViewById(R.id.txt1);
        Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Italic.ttf");
        txt1.setTypeface(roboto);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
