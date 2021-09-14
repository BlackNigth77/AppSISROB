package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pigra.appsisrob.entidades.Video;
import com.pigra.appsisrob.modelo.DAOVideo;

import java.util.ArrayList;
import java.util.List;

public class VideosActivity extends AppCompatActivity {

    FloatingActionButton btnAgregarVideo;

    RecyclerView recyclerVideo;

    DAOVideo daoVideo = new DAOVideo(this);
    List<Video> listaVideos = new ArrayList<>();
    AdaptadorPersonalizado adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        asignarReferencias();
        daoVideo.abrirBD();
        mostrarVideos();
    }

    private void mostrarVideos(){
        listaVideos = daoVideo.getAllVideos();
        adaptador = new AdaptadorPersonalizado(this,listaVideos);
        recyclerVideo.setAdapter(adaptador);
        recyclerVideo.setLayoutManager(new LinearLayoutManager(this));
    }

    private void asignarReferencias(){

        recyclerVideo = findViewById(R.id.recyclerVideos);
        btnAgregarVideo=findViewById(R.id.btnAgregarVideo);
        btnAgregarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideosActivity.this,RegistrarVideosActivity.class);
                startActivity(intent);
            }
        });

    }
}