package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pigra.appsisrob.entidades.Noticia;
import com.pigra.appsisrob.entidades.Video;
import com.pigra.appsisrob.fragmets.DatePickerFragment;
import com.pigra.appsisrob.modelo.DAONoticia;
import com.pigra.appsisrob.modelo.DAOVideo;

import java.util.ArrayList;
import java.util.List;

public class NoticiasActivity extends AppCompatActivity {
   // String variable;
    FloatingActionButton btnAgregarNoticia;
    RecyclerView recyclerNoticias;

    DAONoticia daoNoticia = new DAONoticia(this);
    List<Noticia> listaNoticias = new ArrayList<>();
    AdaptadorPersonalNoticia adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        asignarReferencias();
        daoNoticia.abrirBD();
        mostrarVideos();

    }

    private void mostrarVideos(){
        listaNoticias = daoNoticia.getAllNoticias();
        adaptador = new AdaptadorPersonalNoticia(this,listaNoticias);
        recyclerNoticias.setAdapter(adaptador);
        recyclerNoticias.setLayoutManager(new LinearLayoutManager(this));
    }


    private void asignarReferencias(){

        recyclerNoticias = findViewById(R.id.recyclerNoticias);
        btnAgregarNoticia=findViewById(R.id.btnAgregarNoticia);
        btnAgregarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticiasActivity.this,RegistrarNoticiasActivity.class);
                startActivity(intent);
            }
        });

    }



}