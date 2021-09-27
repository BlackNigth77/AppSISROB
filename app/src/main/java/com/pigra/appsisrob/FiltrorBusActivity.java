package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.pigra.appsisrob.entidades.Equipo;
import com.pigra.appsisrob.modelo.DAOEquipo;

import java.util.ArrayList;
import java.util.List;

public class FiltrorBusActivity extends AppCompatActivity {

    RecyclerView recyclerFiltroBus;
    ImageView ImageEquipo;
    DAOEquipo daoEquipo = new DAOEquipo(this);
    List<Equipo> listaEquipos = new ArrayList<>();
    AdaptadorEquipos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtror_bus);

        asignarReferencias();
        daoEquipo.abrirBD();
        mostrarEquiposBus();

    }

    private void mostrarEquiposBus(){
        listaEquipos = daoEquipo.getAllEquiposBus();
        adaptador= new AdaptadorEquipos(this,listaEquipos);
        recyclerFiltroBus.setAdapter(adaptador);
        recyclerFiltroBus.setLayoutManager(new LinearLayoutManager(this));
    }

    private void asignarReferencias(){

        recyclerFiltroBus = findViewById(R.id.recyclerFiltroBus);

    }
}