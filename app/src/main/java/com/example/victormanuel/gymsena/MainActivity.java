package com.example.victormanuel.gymsena;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText usuario,pass;
    Button enviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText)findViewById(R.id.txtUsu);
        pass = (EditText)findViewById(R.id.txtPas);
        enviar = (Button)findViewById(R.id.btnLogin);

        enviar.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        ConnectivityManager con = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();


        Thread tr = new Thread(){

            @Override
            public void run() {
                final String resultado = enviarDatosGET(usuario.getText().toString(),pass.getText().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtenerDatosJson(resultado);
                        if (r>0){
                            Intent i = new Intent(getApplicationContext(),ventana_principal.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(getApplicationContext(),"El usuario o el documento son incorrectos",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }


        };


        if (networkInfo!=null && networkInfo.isConnected()){
            tr.start();
        }else{
           Toast.makeText(getApplicationContext(),"No se pudo conectar, verifique el acceso" +
                   " a internet e intente conectarse nuevamente",Toast.LENGTH_LONG).show();}

    }





public String enviarDatosGET(String usu,String pas){

    URL url = null;
    String linea="";
    int respuesta=0;

    StringBuilder result=null;

    try {
        //10.71.140.64
        //192.168.0.11
        //192.168.43.255
        url = new URL("http://192.168.0.11/ServicioWeb/validacion.php?pas="+pas+"&usu="+usu);

        HttpURLConnection connection =(HttpURLConnection)url.openConnection();

        respuesta = connection.getResponseCode();
        result = new StringBuilder();

        if (respuesta == HttpURLConnection.HTTP_OK) {

            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while ((linea = reader.readLine()) != null) {
                result.append(linea);
            }
        }

    }catch (Exception e){}

    return result.toString();
}


public int obtenerDatosJson(String response){
    int respu = 0;

    try {

        JSONArray json = new JSONArray(response);
        if (json.length()>0) {
            respu = 1;
        }
    }catch (Exception e){}
    return respu;
}






    public void ventanaRegistro(View view) {

        if (view.getId()==R.id.txtVentanaInicioRegistro){
            Intent intent = new Intent(MainActivity.this,VentanaRegistro.class);
            startActivity(intent);

        }
    }


}
