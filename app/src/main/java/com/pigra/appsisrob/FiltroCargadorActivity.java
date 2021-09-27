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

public class FiltroCargadorActivity extends AppCompatActivity {

    RecyclerView recyclerFiltroCar;
    ImageView ImageEquipo;
    DAOEquipo daoEquipo = new DAOEquipo(this);
    List<Equipo> listaEquipos = new ArrayList<>();
    AdaptadorEquipos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_cargador);

        asignarReferencias();
        daoEquipo.abrirBD();
        mostrarEquiposCar();

    }

    private void mostrarEquiposCar(){
        listaEquipos = daoEquipo.getAllEquiposCar();
        adaptador= new AdaptadorEquipos(this,listaEquipos);
        recyclerFiltroCar.setAdapter(adaptador);
        recyclerFiltroCar.setLayoutManager(new LinearLayoutManager(this));
    }

    private void asignarReferencias(){

        recyclerFiltroCar = findViewById(R.id.recyclerFiltroCar);

    }


}