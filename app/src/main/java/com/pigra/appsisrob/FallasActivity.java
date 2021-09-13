package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FallasActivity extends AppCompatActivity {

    FloatingActionButton btnAgregarFalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallas);
        asignarReferencias();
    }

    private void asignarReferencias(){

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