package com.example.victormanuel.gymsena;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ventana_principal extends AppCompatActivity {

    ListViewAdapter adapter;

    String[] titulos = new String[]{
            "Guia de ejercicios",
            "Rutinas asignadas",
            "Mi perfil",
            "Motivaciones",
            "Reglamento",
            "Horarios",
            "Ubicacion",
            "Acerca de",
    };

    String[] subTitulos = new String[]{
            "guia de ejercicios",
            "rutinas asignadas",
            "miperfil",
            "motivaciones",
            "reglamento",
            "horarios",
            "ubicacion",
            "acerca de",
    };

    int[] imagenes = {
            R.drawable.guiaejercicios,
            R.drawable.rutinasasignadas,
            R.drawable.miperfil,
            R.drawable.motivaciones,
            R.drawable.reglamento,
            R.drawable.horario,
            R.drawable.ubicacion,
            R.drawable.acercade,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_principal);



        ListView lista = (ListView) findViewById(R.id.listViewVentanaPrincipal);

        adapter = new ListViewAdapter(this,titulos,subTitulos,imagenes);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), "presiono " + i, Toast.LENGTH_SHORT).show();

                if (i==0){
                    Intent guia = new Intent(getApplicationContext(),guia_ejercicios.class);
                    startActivity(guia);
                }



                if (i==1){
                    Intent guia1 = new Intent(getApplicationContext(),RutinasAsignadas.class);
                    startActivity(guia1);
                }
            }
        });


    }


    //LIST VIEW ADAPTER


    public class ListViewAdapter extends BaseAdapter {
        // Declare Variables
        Context context;
        String[] titulos;
        String[] subTitulos;
        int[] imagenes;
        LayoutInflater inflater;

        public ListViewAdapter(Context context, String[] titulos,String[] subTitulos, int[] imagenes) {
            this.context = context;
            this.titulos = titulos;
            this.subTitulos = subTitulos;
            this.imagenes = imagenes;
        }

        @Override
        public int getCount() {
            return titulos.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            // Declare Variables
            TextView txtTitle;
            TextView txtSubTitle;
            ImageView imgImg;

            //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.list_row, parent, false);

            // Locate the TextViews in listview_item.xml
            txtTitle = (TextView) itemView.findViewById(R.id.tituloLista);
            txtSubTitle = (TextView) itemView.findViewById(R.id.subtituloLista);
            imgImg = (ImageView) itemView.findViewById(R.id.imagenLista);

            // Capture position and set to the TextViews
            txtTitle.setText(titulos[position]);
            txtSubTitle.setText(subTitulos[position]);
            imgImg.setImageResource(imagenes[position]);

            return itemView;
        }
    }



}
