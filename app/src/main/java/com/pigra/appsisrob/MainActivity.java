package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMenuPrinOp1,txtMenuPrinOp6 ;
    ImageView ImgPrinOp1,ImgPrinOp6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias() {

        txtMenuPrinOp1 = findViewById(R.id.txtEquipo);
        ImgPrinOp1 = findViewById(R.id.imgMenOp1);

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