package com.example.victormanuel.gymsena.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.victormanuel.gymsena.R;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRegistro.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRegistro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegistro extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText txtDocumento,txtNombre,txtApellido,txtusUario,txtContraseña,txtFicha;
    RadioButton radioMujer,radioHombre;
    RadioGroup radioGropo;
    Button botonRegistro;
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public FragmentRegistro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegistro newInstance(String param1, String param2) {
        FragmentRegistro fragment = new FragmentRegistro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View vista = inflater.inflate(R.layout.fragment_blank,container,false);

        txtDocumento = (EditText) vista.findViewById(R.id.txtDocumentoRegistro);
        txtNombre = (EditText) vista.findViewById(R.id.txtNombreRegistro);
        txtApellido = (EditText) vista.findViewById(R.id.txtApellidoregistro);
        txtusUario = (EditText) vista.findViewById(R.id.txtUsuarioRegistro);
        txtContraseña = (EditText) vista.findViewById(R.id.txtContraseñaRegistro);
        radioMujer = (RadioButton) vista.findViewById(R.id.radioMujerRegistro);
        radioHombre = (RadioButton) vista.findViewById(R.id.radioHombreRegistro);
        radioGropo = (RadioGroup) vista.findViewById(R.id.grupoRadioRegistro);
        txtFicha = (EditText) vista.findViewById(R.id.txtFichaRegistro);
        botonRegistro = (Button) vista.findViewById(R.id.btnRegistrarseRegistro);

        request = Volley.newRequestQueue(getContext());

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });
        return vista;
    }

    private void cargarWebService() {

        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        String url="http://192.168.0.11/gimnasioSena/registroCheano.php?documento="+txtDocumento.getText().toString()+
                "&nombre="+txtNombre.getText().toString()+"&apellido="+txtApellido.getText().toString()+
                "&sexo=masculino&usuario="+txtusUario.getText().toString()+
                "&contrasena="+txtContraseña.getText().toString()+"&ficha="+txtFicha.getText().toString();

        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(getContext(),"Se ha registrado exitosamente",Toast.LENGTH_SHORT).show();
        progreso.hide();
        txtDocumento.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtusUario.setText("");
        txtContraseña.setText("");
        txtFicha.setText("");

    }


    @Override
    public void onErrorResponse(VolleyError error) {


        Toast.makeText(getContext(),"No se pudo Registrar"+error.toString(),Toast.LENGTH_SHORT).show();
        progreso.hide();
        Log.i("ERROR",error.toString());
    }











    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
