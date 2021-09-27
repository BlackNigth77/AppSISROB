package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pigra.appsisrob.entidades.Almacen;
import com.pigra.appsisrob.entidades.SesionGlobal;
import com.pigra.appsisrob.entidades.UsuarioOpcion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtUsuario, txtMenuPrinOp1,txtMenuPrinOp6 ,txtMenuPrinOp2 ,txtMenuPrinOp3,txtMenuPrinOp4,txtMenuPrinOp5;
    ImageView ImgPrinOp1,ImgPrinOp6,ImgPrinOp2,ImgPrinOp3,ImgPrinOp4,ImgPrinOp5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        obtenerOpciones();
    }

    private void obtenerOpciones() {

        final SesionGlobal globalVariable = (SesionGlobal) getApplicationContext();
        int dni = globalVariable.getUsuario().getDni();


        List<UsuarioOpcion> lstOpcion = new ArrayList<>();

        txtUsuario.setText( "Bienvenido(a): "  +  globalVariable.getUsuario().getNombre() + " " + globalVariable.getUsuario().getApellido() );
        if (globalVariable.getOpciones()==null)
        {
            String urlOpcion = "http://pruebaupc.atwebpages.com/index.php/opciones/" + dni ;
            Log.d("==>","envía");
            StringRequest peticion = new StringRequest(Request.Method.GET, urlOpcion,
                    response -> {
                try {
                    Log.d("==>C","cool");
                    JSONArray arreglo = new JSONArray( response );
                    lstOpcion.clear();
                    for(int i=0; i<arreglo.length();i++)
                    {
                        JSONObject objeto = arreglo.getJSONObject(i);
                        UsuarioOpcion opcion = new UsuarioOpcion();
                        opcion.setIdOpcion( objeto.getInt("IDOPCION"));
                        opcion.setDNI( objeto.getInt("DNI"));
                        opcion.setVisible( objeto.getString ("VISIBLE"));
                        lstOpcion.add(opcion);
                    }
                    globalVariable.setOpciones(lstOpcion);
                    visibilidadOpciones(lstOpcion);
                }
                catch (JSONException e)
                {
                    Log.d("==>",e.getMessage());
                }
                }, error -> Log.d("==>E",error.getMessage() ));
            RequestQueue cola = Volley.newRequestQueue(this);
            cola.add(peticion);
        }
    }

    private void SetVisibilidad(TextView txt, ImageView img, String visibilidad )
    {
        int intVisibilidad = View.VISIBLE;
        if(visibilidad.equals("NO"))
            intVisibilidad = View.GONE;

        txt.setVisibility(intVisibilidad);
        img.setVisibility(intVisibilidad);
    }

    private void visibilidadOpciones(List<UsuarioOpcion> lstOpcion) {
        SetVisibilidad(txtMenuPrinOp1,ImgPrinOp1,lstOpcion.get(0).getVisible());
        SetVisibilidad(txtMenuPrinOp2,ImgPrinOp2,lstOpcion.get(1).getVisible());
        SetVisibilidad(txtMenuPrinOp3,ImgPrinOp3,lstOpcion.get(2).getVisible());
        SetVisibilidad(txtMenuPrinOp4,ImgPrinOp4,lstOpcion.get(3).getVisible());
        SetVisibilidad(txtMenuPrinOp5,ImgPrinOp5,lstOpcion.get(4).getVisible());
    }

    private void asignarReferencias() {

        txtUsuario = findViewById(R.id.txtUsuario);

        txtMenuPrinOp1 = findViewById(R.id.txtEquipo);
        ImgPrinOp1 = findViewById(R.id.imgMenOp1);

        txtMenuPrinOp2 = findViewById(R.id.lglTituloYoutube);
        ImgPrinOp2 = findViewById(R.id.imgMenOp2);

        txtMenuPrinOp3 = findViewById(R.id.txtNoticias);
        ImgPrinOp3 = findViewById(R.id.imgMenOp3);

        txtMenuPrinOp4 = findViewById(R.id.txtRegFallas);
        ImgPrinOp4 = findViewById(R.id.imgMenOp4);

        txtMenuPrinOp5= findViewById(R.id.txtSolRepuesto);
        ImgPrinOp5 = findViewById(R.id.imgMenOp5);

        txtMenuPrinOp6 = findViewById(R.id.txtCerrarSesion);
        ImgPrinOp6 = findViewById(R.id.imgMenOp6);

        txtMenuPrinOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,MenuEquipos.class);
                startActivity(intent);
            }
        });

        ImgPrinOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,MenuEquipos.class);
                startActivity(intent);
            }
        });


        txtMenuPrinOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,VideosActivity.class);
                startActivity(intent);
            }
        });

        ImgPrinOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,VideosActivity.class);
                startActivity(intent);
            }
        });


        txtMenuPrinOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,NoticiasActivity.class);
                startActivity(intent);
            }
        });

        ImgPrinOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,NoticiasActivity.class);
                startActivity(intent);
            }
        });

        txtMenuPrinOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,FallasActivity.class);
                startActivity(intent);
            }
        });

        ImgPrinOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,FallasActivity.class);
                startActivity(intent);
            }
        });

        txtMenuPrinOp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,RepuestosActivity.class);
                startActivity(intent);
            }
        });

        ImgPrinOp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,RepuestosActivity.class);
                startActivity(intent);
            }
        });


        txtMenuPrinOp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SesionGlobal globalVariable = (SesionGlobal) getApplicationContext();
                globalVariable.setOpciones(null);
                globalVariable.setUsuario(null);
                finish();
            }
        });



        ImgPrinOp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SesionGlobal globalVariable = (SesionGlobal) getApplicationContext();
                globalVariable.setOpciones(null);
                globalVariable.setUsuario(null);
                finish();
            }
        });



    }


}