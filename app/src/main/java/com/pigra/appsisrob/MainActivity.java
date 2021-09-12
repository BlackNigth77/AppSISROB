package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMenuPrinOp1,txtMenuPrinOp6 ,txtMenuPrinOp2 ,txtMenuPrinOp3,txtMenuPrinOp4,txtMenuPrinOp5;
    ImageView ImgPrinOp1,ImgPrinOp6,ImgPrinOp2,ImgPrinOp3,ImgPrinOp4,ImgPrinOp5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias() {

        txtMenuPrinOp1 = findViewById(R.id.txtEquipo);
        ImgPrinOp1 = findViewById(R.id.imgMenOp1);

        txtMenuPrinOp2 = findViewById(R.id.txtVideos);
        ImgPrinOp2 = findViewById(R.id.imgMenOp2);

        txtMenuPrinOp3 = findViewById(R.id.txtNoticias);
        ImgPrinOp3 = findViewById(R.id.imgMenOp3);

        txtMenuPrinOp4 = findViewById(R.id.txtRegFallas);
        ImgPrinOp4 = findViewById(R.id.imgMenOp4);

        txtMenuPrinOp5= findViewById(R.id.txtSolRepuesto);
        ImgPrinOp5 = findViewById(R.id.imgMenOp5);

        txtMenuPrinOp6 = findViewById(R.id.txtCerrarSesion);
        ImgPrinOp6 = findViewById(R.id.imgMenOp6);

        txtMenuPrinOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,MenuEquipos.class);
                startActivity(intent);
            }
        });

        ImgPrinOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,MenuEquipos.class);
                startActivity(intent);
            }
        });


        txtMenuPrinOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,VideosActivity.class);
                startActivity(intent);
            }
        });

        ImgPrinOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,VideosActivity.class);
                startActivity(intent);
            }
        });


        txtMenuPrinOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,NoticiasActivity.class);
                startActivity(intent);
            }
        });

        ImgPrinOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,NoticiasActivity.class);
                startActivity(intent);
            }
        });

        txtMenuPrinOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,FallasActivity.class);
                startActivity(intent);
            }
        });

        ImgPrinOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,FallasActivity.class);
                startActivity(intent);
            }
        });

        txtMenuPrinOp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,RepuestosActivity.class);
                startActivity(intent);
            }
        });

        ImgPrinOp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,RepuestosActivity.class);
                startActivity(intent);
            }
        });


        txtMenuPrinOp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        ImgPrinOp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }


}