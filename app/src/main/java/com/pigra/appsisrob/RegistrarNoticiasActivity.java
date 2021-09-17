package com.pigra.appsisrob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.pigra.appsisrob.entidades.Noticia;
import com.pigra.appsisrob.fragmets.DatePickerFragment;
import com.pigra.appsisrob.modelo.DAONoticia;



public class RegistrarNoticiasActivity extends AppCompatActivity implements View.OnClickListener{

    TextView lblTituloGenVideo;
    EditText txtTituloNoticias, txtFechaNoticias, txtDetalleNoticia;
    Button btnGrabarNoticia;
    Noticia noticia;


    String titulo, fecha, detalle;
    int  id;

    Boolean actualizar = false;

    DAONoticia daoNoticia = new DAONoticia(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_noticias);
        daoNoticia.abrirBD();
        asignarReferencias();
        verificarEdicion();
        txtFechaNoticias = (EditText) findViewById(R.id.txtFechaNoticias);
        txtFechaNoticias.setOnClickListener(this);

    }

    private void asignarReferencias() {

        txtTituloNoticias = findViewById(R.id.txtTituloNoticias);
        txtFechaNoticias = findViewById(R.id.txtFechaNoticias);
        txtDetalleNoticia = findViewById(R.id.txtDetalleNoticia);
        btnGrabarNoticia = findViewById(R.id.btnGrabarNoticia);
        lblTituloGenVideo = findViewById(R.id.lblTituloGenVideo);


        btnGrabarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(capturarDatos()) {
                    String mensaje;
                    if (!actualizar)
                        mensaje = daoNoticia.registrarNoticia(noticia);
                    else
                        mensaje = daoNoticia.actualizarNoticia(noticia);

                    MostrarMensaje(mensaje);
                }
            }
        });
    }

    private void verificarEdicion() {

        if(getIntent().hasExtra("id")){

            id = Integer.parseInt(getIntent().getStringExtra("id"));
            titulo = getIntent().getStringExtra("titulo");
            fecha = getIntent().getStringExtra("fecha");
            detalle = getIntent().getStringExtra("detalle");
            actualizar = true;
            txtTituloNoticias.setText(titulo);
            txtFechaNoticias.setText(fecha);
            txtDetalleNoticia.setText(detalle);
            btnGrabarNoticia.setText("EDITAR VIDEO");
            lblTituloGenVideo.setText("EDITAR INFORMACION");


        }
    }

    private void MostrarMensaje(String mensaje)
    {
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarNoticiasActivity.this);
        ventana.setTitle("Mensaje");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(RegistrarNoticiasActivity.this, NoticiasActivity.class);
                startActivity(intent);
            }
        });
        ventana.create().show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtFechaNoticias:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                txtFechaNoticias.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }



    private boolean capturarDatos() {

        if(txtTituloNoticias.getText().toString().equals(""))
        {
            txtTituloNoticias.setError("Titulo de Noticia Obligatoria");
            return false;
        }
        if(txtFechaNoticias.getText().toString().equals(""))
        {
            txtFechaNoticias.setError("Fecha de Noticia Obligatoria");
            return false;
        }
        if(txtDetalleNoticia.getText().toString().equals(""))
        {
            txtDetalleNoticia.setError("Detalle de Noticia Obligatorio");
            return false;
        }

        noticia = new Noticia();
        if(actualizar)
            noticia.setId(id);

        noticia.setTitulo(txtTituloNoticias.getText().toString());
        noticia.setFecha(txtFechaNoticias.getText().toString());
        noticia.setDetalle(txtDetalleNoticia.getText().toString());



        return true;
    }


}