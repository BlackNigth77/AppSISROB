package com.pigra.appsisrob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.pigra.appsisrob.entidades.Video;
import com.pigra.appsisrob.modelo.DAOVideo;

public class RegistrarVideosActivity extends AppCompatActivity {

    TextView lblTituloGenVideo;
    EditText txtTituloVideo, txtDuracionVideo, txtRutaVideo;
    Button btnGrabarVideo;
    Video video;


    String titulo;
    String ruta;

    int  id;
    int  duracion;

    Boolean actualizar = false;

    DAOVideo daoVideo = new DAOVideo(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_videos);
        daoVideo.abrirBD();
        asignarReferencias();
        verificarEdicion();

    }

    private void asignarReferencias() {

        txtTituloVideo = findViewById(R.id.txtTituloVideo);
        txtDuracionVideo = findViewById(R.id.txtDuracionVideo);
        txtRutaVideo = findViewById(R.id.txtRutaVideo);
        btnGrabarVideo = findViewById(R.id.btnGrabarVideo);
        lblTituloGenVideo = findViewById(R.id.lblTituloGenVideo);


        btnGrabarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(capturarDatos()) {
                    String mensaje;
                    if (!actualizar)
                        mensaje = daoVideo.registrarVideo(video);
                    else
                        mensaje = daoVideo.actualizarVideo(video);

                    MostrarMensaje(mensaje);
                }
            }
        });
    }

    private void verificarEdicion() {


        if(getIntent().hasExtra("id")) {

            id = Integer.parseInt(getIntent().getStringExtra("id"));
            titulo = getIntent().getStringExtra("titulo");
            ruta = getIntent().getStringExtra("ruta");
            duracion = Integer.parseInt(getIntent().getStringExtra("duracion"));
            actualizar = true;
            txtTituloVideo.setText(titulo);
            txtDuracionVideo.setText(duracion+"");
            txtRutaVideo.setText(ruta);
            btnGrabarVideo.setText("EDITAR VIDEO");
            lblTituloGenVideo.setText("EDITAR INFORMACION");



        }


    }

    private void MostrarMensaje(String mensaje)
    {
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarVideosActivity.this);
        ventana.setTitle("Mensaje");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(RegistrarVideosActivity.this, VideosActivity.class);
                startActivity(intent);
            }
        });
        ventana.create().show();
    }

    private boolean capturarDatos() {

        if(txtTituloVideo.getText().toString().equals(""))
        {
            txtTituloVideo.setError("Titulo de Video Obligatorio");
            return false;
        }
        if(txtDuracionVideo.getText().toString().equals(""))
        {
            txtDuracionVideo.setError("Duracion de Video Obligatoria");
            return false;
        }
        if(txtRutaVideo.getText().toString().equals(""))
        {
            txtRutaVideo.setError("Ruta de Video Obligatoria");
            return false;
        }
        Toast.makeText(getApplicationContext(), "Verificar", Toast.LENGTH_SHORT).show();

        video = new Video();
        if(actualizar)

            video.setId(id);
            video.setTitulo(txtTituloVideo.getText().toString());
            video.setDuracion(Integer.parseInt(txtDuracionVideo.getText().toString()));
            video.setRuta(txtRutaVideo.getText().toString());



        return true;
    }
}