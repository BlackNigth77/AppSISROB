package com.pigra.appsisrob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pigra.appsisrob.entidades.ReporteFalla;
import com.pigra.appsisrob.AdaptadorFalla;

import java.util.ArrayList;
import java.util.List;

public class FallasActivity extends AppCompatActivity {

    RecyclerView recyclerFallas;
    FloatingActionButton btnAgregarFalla;
    FirebaseDatabase fbDatabase;
    DatabaseReference dbReference;

    List<ReporteFalla> listaFalla = new ArrayList<>();
    //ArrayAdapter<ReporteFalla> adaptador ;
    AdaptadorFalla adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallas);
        asignarReferencias();
        inicializarFirebase();
        listarDatos();
    }

    private void listarDatos(){
        dbReference.child("ReporteFalla").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaFalla.clear();
                for(DataSnapshot item:snapshot.getChildren()){
                    ReporteFalla rf = item.getValue(ReporteFalla.class);
                    listaFalla.add(rf);
                }
                //adaptador = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1,listaPersonas);
                //lstPersonas.setAdapter(adaptador);
                adaptador = new AdaptadorFalla(FallasActivity.this,listaFalla);
                recyclerFallas.setAdapter(adaptador);
                recyclerFallas.setLayoutManager(new LinearLayoutManager(FallasActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        fbDatabase = FirebaseDatabase.getInstance();
        dbReference = fbDatabase.getReference();
    }

    private void asignarReferencias(){

        recyclerFallas = findViewById(R.id.recyclerFallas);
        btnAgregarFalla=findViewById(R.id.btnAgregarFalla);
        btnAgregarFalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FallasActivity.this,RegistroFallasActivity.class);
                startActivity(intent);
            }
        });

    }
}