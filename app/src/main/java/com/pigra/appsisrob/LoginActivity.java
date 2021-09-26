package com.pigra.appsisrob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pigra.appsisrob.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

     Button btnIngresar;
     TextView lblOlvidoPsw;
    TextView lblOlvidoPsw2;
    EditText txtUsuLogin, txtPswLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asignarReferencias();
    }

    private void asignarReferencias(){

        btnIngresar=findViewById(R.id.btnIngresar);
        lblOlvidoPsw=findViewById(R.id.txtOlvidoPsw);

        txtUsuLogin = findViewById(R.id.txtUsuLogin);
        txtPswLogin = findViewById(R.id.txtPswLogin);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean invalido=false;

                if(txtUsuLogin.getText().toString().equals(""))
                {
                    txtUsuLogin.setError("INGRESE DNI");
                    invalido = true;
                }
                if(txtPswLogin.getText().toString().equals(""))
                {
                    txtPswLogin.setError("INGRESE CONTRASEÑA");
                    invalido = true;
                }

                if(invalido) return;

                validaUsuario();

                /*
                final SesionGlobal globalVariable = (SesionGlobal) getApplicationContext();
                Usuario usuario = new Usuario();
                usuario.setDni( Integer.parseInt(txtUsuLogin.getText().toString()));
                usuario.setNombre("Ivan");
                usuario.setApellido("Tasso");
                globalVariable.setUsuario(usuario);

                Intent intent = new Intent( LoginActivity.this,MainActivity.class);
                startActivity(intent);*/
            }
        });

        lblOlvidoPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this,RecuperaPswActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validaUsuario() {

        String url = "http://pruebaupc.atwebpages.com/index.php/validaUsuario/" + txtUsuLogin.getText().toString();

        StringRequest peticion = new StringRequest(Request.Method.GET, url, new  Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray arreglo = new JSONArray( response );
                    Log.d("==>A", arreglo.toString() );

                    if( arreglo.length()<=0  )
                    {
                        MostrarMensaje("Usuario y/o contraseña invalidos");
                        return;
                    }
                    JSONObject objeto = arreglo.getJSONObject(0);
                    Usuario usuario = new Usuario();
                    usuario.setDni(objeto.getInt("DNI"));
                    usuario.setNombre(objeto.getString("NOMBRE"));
                    usuario.setApellido(objeto.getString("APELLIDO"));
                    usuario.setPassword(objeto.getString("PASSWORD"));

                    if(! usuario.getPassword().equals(txtPswLogin.getText().toString())   )
                    {
                        MostrarMensaje("Usuario y/o contraseña invalidos");
                        return;
                    }
                    final SesionGlobal globalVariable = (SesionGlobal) getApplicationContext();
                    globalVariable.setUsuario(usuario);

                    Intent intent = new Intent( LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                catch (JSONException e)
                {
                    Log.d("==>",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("==>x",error.toString());
            }
        });

        RequestQueue cola = Volley.newRequestQueue(this);
        cola.add(peticion);
    }

    private void MostrarMensaje(String mensaje)
    {
        AlertDialog.Builder ventana = new AlertDialog.Builder(LoginActivity.this);
        ventana.setTitle("Mensaje");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ventana.create().show();
    }
}