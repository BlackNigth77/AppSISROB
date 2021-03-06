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

public class ActivityFiltroEquipos extends AppCompatActivity {

    RecyclerView recyclerFiltroEq;
    ImageView ImageEquipo;
    DAOEquipo daoEquipo = new DAOEquipo(this);
    List<Equipo> listaEquipos = new ArrayList<>();
    AdaptadorEquipos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_equipos);


        asignarReferencias();
        daoEquipo.abrirBD();
        mostrarEquipos();
    }

    private void mostrarEquipos(){
        listaEquipos = daoEquipo.getAllEquipos();
        adaptador= new AdaptadorEquipos(this,listaEquipos);
        recyclerFiltroEq.setAdapter(adaptador);
        recyclerFiltroEq.setLayoutManager(new LinearLayoutManager(this));
    }

    private void asignarReferencias(){

        recyclerFiltroEq = findViewById(R.id.recyclerFiltroEq);

    }

}