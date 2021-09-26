package com.pigra.appsisrob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pigra.appsisrob.entidades.Equipo;
import com.pigra.appsisrob.entidades.Video;
import com.pigra.appsisrob.modelo.DAOEquipo;
import com.pigra.appsisrob.modelo.DAONoticia;

public class RegistrarEquipoActivity extends AppCompatActivity {

    EditText txtDescripcionEquipo, txtTipoEquipo, txtMarcaEquipo,txtModeloEquipo,txtCapacidadEquipo;
    Button btnGrabarEquipo;
    Equipo equipo;

    String descripcion, tipo,marca,modelo;
    int capacidad, id;

    Boolean actualizar = false;

    DAOEquipo daoEquipo = new DAOEquipo(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_equipo);
        daoEquipo.abrirBD();
        asignarReferencias();
        verificarEdicion();
    }

    private void asignarReferencias() {

        txtDescripcionEquipo = findViewById(R.id.txtDescripcionEquipo);
        txtTipoEquipo = findViewById(R.id.txtTipoEquipo);
        txtMarcaEquipo = findViewById(R.id.txtMarcaEquipo);
        txtModeloEquipo = findViewById(R.id.txtModeloEquipo);
        txtCapacidadEquipo = findViewById(R.id.txtCapacidadEquipo);
        btnGrabarEquipo = findViewById(R.id.btnGrabarEquipo);


        btnGrabarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(capturarDatos()) {
                    String mensaje = null;
                    if (!actualizar)
                        mensaje = daoEquipo.registrarEquipo(equipo);

                    MostrarMensaje(mensaje);
                }
            }
        });
    }

    private void verificarEdicion() {

        if(getIntent().hasExtra("id")){

            id = Integer.parseInt(getIntent().getStringExtra("id"));
            descripcion = getIntent().getStringExtra("descripcion");
            tipo = getIntent().getStringExtra("tipo");
            marca = getIntent().getStringExtra("marca");
            modelo = getIntent().getStringExtra("modelo");
            capacidad = Integer.parseInt(getIntent().getStringExtra("capacidad"));
            actualizar = true;
            txtDescripcionEquipo.setText(descripcion);
            txtTipoEquipo.setText(tipo);
            txtMarcaEquipo.setText(marca);
            txtModeloEquipo.setText(modelo);
            txtCapacidadEquipo.setText(capacidad);
            btnGrabarEquipo.setText("EDITAR VIDEO");


        }
    }

    private void MostrarMensaje(String mensaje)
    {
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarEquipoActivity.this);
        ventana.setTitle("Mensaje");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(RegistrarEquipoActivity.this, MenuEquipos.class);
                startActivity(intent);
            }
        });
        ventana.create().show();
    }


    private boolean capturarDatos() {

        if (txtDescripcionEquipo.getText().toString().equals("")) {
            txtDescripcionEquipo.setError("Descripcion de Equipo Obligatorio");
            return false;
        }
        if (txtTipoEquipo.getText().toString().equals("")) {
            txtTipoEquipo.setError("Tipo de Equipo Obligatorio");
            return false;
        }

        if (txtMarcaEquipo.getText().toString().equals("")) {
            txtMarcaEquipo.setError("Marca de Equipo Obligatorio");
            return false;
        }
        if (txtModeloEquipo.getText().toString().equals("")) {
            txtModeloEquipo.setError("Modelo de Equipo Obligatorio");
            return false;
        }
        if (txtCapacidadEquipo.getText().toString().equals("")) {
            txtCapacidadEquipo.setError("Capacidad de Equipo Obligatoria");
            return false;
        }


        equipo = new Equipo();
        if(actualizar)
            equipo.setId(id);

        equipo.setDescripcion(txtDescripcionEquipo.getText().toString());
        equipo.setTipo(txtTipoEquipo.getText().toString());
        equipo.setMarca(txtMarcaEquipo.getText().toString());
        equipo.setModelo(txtModeloEquipo.getText().toString());
        equipo.setCapacidad(Integer.parseInt(txtCapacidadEquipo.getText().toString()));


        return true;
        
    }

}