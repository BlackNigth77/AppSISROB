package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoticiasActivity extends AppCompatActivity {

    FloatingActionButton btnAgregarNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        asignarReferencias();
    }

    private void asignarReferencias(){

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