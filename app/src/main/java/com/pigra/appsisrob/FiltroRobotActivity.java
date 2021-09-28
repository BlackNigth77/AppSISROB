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

public class FiltroRobotActivity extends AppCompatActivity {

    RecyclerView recyclerFiltroRob;
    ImageView ImageEquipo;
    DAOEquipo daoEquipo = new DAOEquipo(this);
    List<Equipo> listaEquipos = new ArrayList<>();
    AdaptadorRobot adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_robot);
        asignarReferencias();
        daoEquipo.abrirBD();
        mostrarEquiposRob();
    }

    private void mostrarEquiposRob(){
        listaEquipos = daoEquipo.getAllEquiposRob();
        adaptador= new AdaptadorRobot(this,listaEquipos);
        recyclerFiltroRob.setAdapter(adaptador);
        recyclerFiltroRob.setLayoutManager(new LinearLayoutManager(this));
    }

    private void asignarReferencias(){

        recyclerFiltroRob = findViewById(R.id.recyclerFiltroRob);

    }


}