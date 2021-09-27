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

import com.pigra.appsisrob.entidades.Almacen;
import com.pigra.appsisrob.entidades.SolicitudRepuesto;
import com.pigra.appsisrob.entidades.UsuarioOpcion;
import com.pigra.appsisrob.entidades.Video;
import com.pigra.appsisrob.fragmets.DatePickerFragment;
import com.pigra.appsisrob.modelo.DAOSolicitudRepuesto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegistroSolicitud extends AppCompatActivity {
    TextView txtRegCodigo, lblAlmacen;
    EditText txtRegFechaSol, txtRegCantidad, txtRegSolDescripcion;
    Button btnRegSolRep, btnMapaAlmacen;

    SolicitudRepuesto solicitud ;

    Boolean actualizar = false;

    DAOSolicitudRepuesto dao = new DAOSolicitudRepuesto(this);

    Integer id;
    String codigo;
    String fecha;
    String descripcion;
    Integer stock;
    Integer cantidad;
    Integer categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_solicitud);
        dao.abrirBD();
        asignarReferencias();
        verificarEdicion();
    }

    private void asignarReferencias() {
        txtRegCodigo   = findViewById(R.id.txtRegCodigo);
        txtRegFechaSol = findViewById(R.id.txtRegFechaSol);
        txtRegCantidad = findViewById(R.id.txtRegCantidad);
        txtRegSolDescripcion = findViewById(R.id.txtRegSolDescripcion);
        btnRegSolRep   = findViewById(R.id.btnRegSolRep);
        btnMapaAlmacen = findViewById(R.id.btnMapaAlmacen);
        lblAlmacen = findViewById(R.id.lblAlmacen);

        btnRegSolRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(capturarDatos()) {
                    String mensaje;

                    if (!actualizar)
                        mensaje = dao.RegistrarSolicitud(solicitud);
                    else
                        mensaje = dao.actualizarSolicitud(solicitud);

                    MostrarMensaje(mensaje);
                }
            }
        });

        txtRegFechaSol.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                {
                    showDatePickerDialog();
                }
            }
        });

        btnMapaAlmacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abriMapa();
            }
        });
    }

    private void abriMapa() {
        Intent intent = new Intent( this,MapaActivity.class);
        intent.putExtra("tipoMapa", "REPUESTO");
        startActivityForResult(intent,1);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                lblAlmacen.setText(data.getStringExtra("nombreAlmacen"));
                stock = Integer.parseInt(data.getStringExtra("stockAlmacen"));
            }
        }
    }

    private void showDatePickerDialog() {

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = String.format("%02d", day)   + "/" + String.format("%02d",(month+1)) + "/" + year;
                txtRegFechaSol.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    private void verificarEdicion() {

        if(getIntent().hasExtra("id")){
             id= Integer.parseInt(getIntent().getStringExtra("id"));
             codigo= getIntent().getStringExtra("codigo");
             fecha= getIntent().getStringExtra("fecha");
             descripcion= getIntent().getStringExtra("descripcion");
             //stock= Integer.parseInt(getIntent().getStringExtra("stock"));
             cantidad= Integer.parseInt(getIntent().getStringExtra("cantidad"));
             //categoria= Integer.parseInt(getIntent().getStringExtra("categoria"));

            actualizar = true;

            txtRegCodigo.setText(codigo);
            txtRegFechaSol.setText( fecha );
            txtRegCantidad.setText(cantidad + "");
            txtRegSolDescripcion.setText(descripcion);
        }
        else {
            Date hoy = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-");

            txtRegCodigo.setText( dateFormat.format(hoy) + "X" );
        }
    }
    private boolean capturarDatos() {

        if(txtRegFechaSol.getText().toString().equals(""))
        {
            txtRegFechaSol.setError("Fecha obligatoria");
            return false;
        }
        if(txtRegSolDescripcion.getText().toString().equals(""))
        {
            txtRegSolDescripcion.setError("Descripcion obligatoria");
            return false;
        }
        if(txtRegCantidad.getText().toString().equals(""))
        {
            txtRegCantidad.setError("Cantidad obligatoria");
            return false;
        }
        if(stock==null){
            lblAlmacen.setError("Seleccione Almacen");
            return false;
        }

        solicitud = new SolicitudRepuesto();
        if(actualizar)
        {
            solicitud.setId(id);
            solicitud.setCodigo(txtRegCodigo.getText().toString());
        }
        else
        {
            Date hoy = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-");
            solicitud.setCodigo(dateFormat.format(hoy));
        }
        solicitud.setFecha(txtRegFechaSol.getText().toString());
        solicitud.setDescripcion(txtRegSolDescripcion.getText().toString());
        solicitud.setCantidad(Integer.parseInt(txtRegCantidad.getText().toString()));
        solicitud.setCategoria(1);
        solicitud.setStock(stock);

        return true;
    }

    private void MostrarMensaje(String mensaje)
    {
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistroSolicitud.this);
        ventana.setTitle("Mensaje");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(RegistroSolicitud.this, RepuestosActivity.class);
                startActivity(intent);
            }
        });
        ventana.create().show();
    }
}