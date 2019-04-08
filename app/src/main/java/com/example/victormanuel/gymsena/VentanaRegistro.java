package com.example.victormanuel.gymsena;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static java.security.AccessController.getContext;

public class VentanaRegistro extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    EditText txtDocumento,txtNombre,txtApellido,txtusUario,txtFicha;

    RadioButton radioMujer,radioHombre;
    RadioGroup radioGropo;
    Button botonRegistro;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registro);


       // Log.i("Progreso",""+progreso);
        //Log.i("getAplication",""+getApplication());
        //progreso = new ProgressDialog(getApplication());
        //Log.i("Progreso",""+progreso);
        //progreso.setMessage("Cargando...");
        //progreso.show();

        txtDocumento = (EditText)findViewById(R.id.txtDocumentoRegistro);
        txtNombre = (EditText)findViewById(R.id.txtNombreRegistro);
        txtApellido = (EditText)findViewById(R.id.txtApellidoregistro);
        txtusUario = (EditText)findViewById(R.id.txtUsuarioRegistro);

        radioMujer = (RadioButton) findViewById(R.id.radioMujerRegistro);
        radioHombre = (RadioButton)findViewById(R.id.radioHombreRegistro);
        radioGropo = (RadioGroup)findViewById(R.id.grupoRadioRegistro);
        txtFicha = (EditText)findViewById(R.id.txtFichaRegistro);
        botonRegistro = (Button)findViewById(R.id.btnRegistrarseRegistro);

        request = Volley.newRequestQueue(getApplication());

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });
    }

    private void cargarWebService() {

        //10.71.140.64
        //192.168.0.11
        //192.168.43.255

        String url;
        url = "http://192.168.0.11/ServicioWeb/registroChenao.php?documento="+txtDocumento.getText().toString()+
                "&nombre="+txtNombre.getText().toString()+"&apellido="+txtApellido.getText().toString()+
                "&sexo=masculino&usuario="+txtusUario.getText().toString()+
                "&ficha="+txtFicha.getText().toString();



        if (txtNombre.getText().length()<1 || txtDocumento.getText().length()<1 ||
                txtApellido.getText().length()<1 || txtusUario.getText().length()<1 || txtFicha.getText().length()<1){

            Toast.makeText(getApplicationContext(),"Debe llenar todos los campos",Toast.LENGTH_SHORT).show();
        }else {

            url=url.replace(" ","%20");
            jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            request.add(jsonObjectRequest);
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo Registrar"+error.toString(),Toast.LENGTH_SHORT).show();
//        progreso.hide();
        Log.i("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {


    //   progreso.hide();

        Intent intent = new Intent(VentanaRegistro.this,ventana_principal.class);
        startActivity(intent);

        txtDocumento.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtusUario.setText("");
        txtFicha.setText("");


        Toast.makeText(getApplicationContext(),"Se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
    }
}
