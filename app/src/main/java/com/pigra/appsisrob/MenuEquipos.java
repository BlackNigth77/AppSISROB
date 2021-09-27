package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuEquipos extends AppCompatActivity {

    TextView txtMixer,txtRobot ,txtCargador ,txtCamion;
    ImageView imgMenEqOp1,imgMenEqOp2,imgMenEqOp3,imgMenEqOp4;

    FloatingActionButton btnRegistrarEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_equipos);
        asignarReferencias();
    }

    private void asignarReferencias(){


        txtMixer = findViewById(R.id.txtMixer);
        imgMenEqOp1 = findViewById(R.id.imgMenEqOp1);

        txtRobot = findViewById(R.id.txtRobot);
        imgMenEqOp2 = findViewById(R.id.imgMenEqOp2);

        txtCargador = findViewById(R.id.txtCargador);
        imgMenEqOp3 = findViewById(R.id.imgMenEqOp3);

        txtCamion = findViewById(R.id.txtCamion);
        imgMenEqOp4 = findViewById(R.id.imgMenEqOp4);

        btnRegistrarEquipo=findViewById(R.id.btnAgregarEquipo);

        txtMixer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuEquipos.this,ActivityFiltroEquipos.class);
                startActivity(intent);
            }
        });

        imgMenEqOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuEquipos.this,ActivityFiltroEquipos.class);
                startActivity(intent);
            }
        });

        txtRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuEquipos.this,FiltroRobotActivity.class);
                startActivity(intent);
            }
        });

        imgMenEqOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuEquipos.this,FiltroRobotActivity.class);
                startActivity(intent);
            }
        });

        txtCargador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuEquipos.this,FiltroCargadorActivity.class);
                startActivity(intent);
            }
        });

        imgMenEqOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuEquipos.this,FiltroCargadorActivity.class);
                startActivity(intent);
            }
        });

        txtCamion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuEquipos.this,FiltrorBusActivity.class);
                startActivity(intent);
            }
        });

        imgMenEqOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuEquipos.this,FiltrorBusActivity.class);
                startActivity(intent);
            }
        });


        btnRegistrarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEquipos.this,RegistrarEquipoActivity.class);
                startActivity(intent);
            }
        });

    }
}