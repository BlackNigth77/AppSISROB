package com.pigra.appsisrob;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pigra.appsisrob.entidades.ReporteFalla;
import com.pigra.appsisrob.fragmets.DatePickerFragment;

import java.util.HashMap;
import java.util.UUID;

public class RegistroFallasActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase fbDatabase;
    DatabaseReference dbReference;
    EditText txtFechaFalla,txtFotoFalla, txtUnidadMinera, txtEquipoFalla, txtTipoFalla, txtSistemaFalla, txtObservacionFalla, txtUbicacionFalla ;
    ImageView fotoFalla;
    Button btnGrabarEquipo, btnFotoFalla, btnSR, btnTambo;
    //TextView equipoFalla;
    Spinner spinner_equipo;


    String id, fechaFalla,unidadMinera, equipoFalla, tipoFalla, sistemaFalla, foto, observacionFalla, ubicacionFalla ;

    Boolean registrar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_fallas);
        inicializarFirebase();
        txtFechaFalla = (EditText) findViewById(R.id.txtFechaFalla);
        txtFechaFalla.setOnClickListener(this);
        fotoFalla = findViewById(R.id.idFotofalla);
        asignarReferencias();
        verificarRegistrar();
        txtEquipoFalla = (EditText)findViewById(R.id.txtEquipoFalla);
        spinner_equipo = (Spinner) findViewById(R.id.spinnerEquipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_tipoequipos, android.R.layout.simple_spinner_item);
        spinner_equipo.setAdapter(adapter);
        spinner_equipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                //Toast.makeText(parent.getContext(),"El libro corresponde a la sede de: "+parent.getItemAtPosition(i).toString(),Toast.LENGTH_LONG).show();
                //equipoFalla.setText("El libro pertenece a la sede de: "+parent.getItemAtPosition(i).toString());
                txtEquipoFalla.setText(parent.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }
    private void verificarRegistrar(){
        if(getIntent().hasExtra("id")){
            registrar = false;
            id = getIntent().getStringExtra("id");
            txtFechaFalla.setText(getIntent().getStringExtra("fecha"));
            txtUnidadMinera.setText(getIntent().getStringExtra("unidad_minera"));
            txtEquipoFalla.setText(getIntent().getStringExtra("equipo"));
            txtTipoFalla.setText(getIntent().getStringExtra("tipo"));
            txtSistemaFalla.setText(getIntent().getStringExtra("sistema"));
            txtObservacionFalla.setText(getIntent().getStringExtra("observacion"));
            txtFotoFalla.setText(getIntent().getStringExtra("foto"));
            txtUbicacionFalla.setText(getIntent().getStringExtra("ubicacion"));

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtFechaFalla:
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
                txtFechaFalla.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onclick(View view) {
        cargarImagen();
    }

    public void cargarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione App"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path=data.getData();
            fotoFalla.setImageURI(path);
        }
    }

    public void asignarReferencias(){
                txtFechaFalla = findViewById(R.id.txtFechaFalla);
                txtUnidadMinera = findViewById(R.id.txtUnidadMinera);
                txtEquipoFalla = findViewById(R.id.txtEquipoFalla);
                txtTipoFalla = findViewById(R.id.txtTipoFalla);
                txtSistemaFalla = findViewById(R.id.txtSistemaFalla);
                txtFotoFalla = findViewById(R.id.txtFotoFalla);
                txtObservacionFalla = findViewById(R.id.txtObservacionFalla);
                txtUbicacionFalla = findViewById(R.id.txtUbicacionFalla);
               //fotoFalla = findViewById(R.id.idFotofalla);
               // btnFotoFalla = findViewById(R.id.btnFotoFalla);
                btnSR = findViewById(R.id.btnSR);
                btnTambo = findViewById(R.id.btnTambo);
                btnSR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RegistroFallasActivity.this, MapaActivity.class);
                        intent.putExtra("latitud","-14.231817194940652");
                        intent.putExtra("longitud","-70.32245853830929");
                        intent.putExtra("titulo","UM San Rafael");
                        startActivity(intent);
                    }
                });
                btnTambo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RegistroFallasActivity.this, MapaActivity.class);
                        intent.putExtra("latitud","-15.463164363533581");
                        intent.putExtra("longitud","-71.91598371269598");
                        intent.putExtra("titulo","UM Tambomayo");
                        startActivity(intent);

                    }
                });


                btnGrabarEquipo = findViewById(R.id.btnGrabarEquipo);
                btnGrabarEquipo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registrar();
                    }
                });
    }
    private void registrar(){
        fechaFalla = txtFechaFalla.getText().toString();
        unidadMinera = txtUnidadMinera.getText().toString();
        equipoFalla = txtEquipoFalla.getText().toString();
        tipoFalla = txtTipoFalla.getText().toString();
        sistemaFalla = txtSistemaFalla.getText().toString();
        observacionFalla = txtObservacionFalla.getText().toString();
        foto = txtFotoFalla.getText().toString();
        ubicacionFalla = txtUbicacionFalla.getText().toString();


        if (registrar == true) {

            ReporteFalla rf = new ReporteFalla();
            rf.setId(UUID.randomUUID().toString());
            rf.setFecha(fechaFalla);
            rf.setUnidad_minera(unidadMinera);
            rf.setEquipo(equipoFalla);
            rf.setTipo(tipoFalla);
            rf.setSistema(sistemaFalla);
            rf.setObservacion(observacionFalla);
            rf.setFoto(foto);
            rf.setUbicacion(ubicacionFalla);
            dbReference.child("ReporteFalla").child(rf.getId()).setValue(rf);
            Toast.makeText(this,"Falla Registrada",Toast.LENGTH_SHORT).show();
        }else{
            HashMap map = new HashMap();
            map.put("fecha", fechaFalla);
            map.put("unidad_minera", unidadMinera);
            map.put("equipo", equipoFalla);
            map.put("tipo", tipoFalla);
            map.put("sistema", sistemaFalla);
            map.put("observacion", observacionFalla);
            map.put("foto", foto);
            map.put("ubicacion", ubicacionFalla);
            dbReference.child("ReporteFalla").child(id).updateChildren(map);
            Toast.makeText(this,"Falla actualizada",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegistroFallasActivity.this, FallasActivity.class);
            startActivity(intent);
        }
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        fbDatabase = FirebaseDatabase.getInstance();
        dbReference = fbDatabase.getReference();
    }

}