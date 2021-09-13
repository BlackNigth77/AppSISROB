package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuEquipos extends AppCompatActivity {

    FloatingActionButton btnRegistrarEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_equipos);
        asignarReferencias();
    }

    private void asignarReferencias(){

        btnRegistrarEquipo=findViewById(R.id.btnAgregarEquipo);
        btnRegistrarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEquipos.this,RegistrarEquipoActivity.class);
                startActivity(intent);
            }
        });

    }
}