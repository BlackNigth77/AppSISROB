package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pigra.appsisrob.entidades.SolicitudRepuesto;
import com.pigra.appsisrob.modelo.DAOSolicitudRepuesto;

import java.util.ArrayList;
import java.util.List;

public class RepuestosActivity extends AppCompatActivity {

    RecyclerView recyclerRepuestos;
    FloatingActionButton btnAgregarRepuesto;

    DAOSolicitudRepuesto daoRepuesto = new DAOSolicitudRepuesto(this);
    List<SolicitudRepuesto> lstEntidad = new ArrayList<>();
    AdaptadorSolicitudRepuesto adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repuestos);
        asignarReferencias();
        daoRepuesto.abrirBD();
        mostrarLista();
    }

    private void mostrarLista() {
        lstEntidad = daoRepuesto.getAllSolicitudes();
        adaptador = new AdaptadorSolicitudRepuesto(this, lstEntidad);
        recyclerRepuestos.setAdapter(adaptador);
        recyclerRepuestos.setLayoutManager( new LinearLayoutManager(this));
    }

    private void asignarReferencias() {
        recyclerRepuestos = findViewById(R.id.recyclerRepuestos);
        btnAgregarRepuesto = findViewById(R.id.btnAgregarRepuesto);
        btnAgregarRepuesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RepuestosActivity.this, RegistroSolicitud.class);
                startActivity(intent);
            }
        });
    }

}