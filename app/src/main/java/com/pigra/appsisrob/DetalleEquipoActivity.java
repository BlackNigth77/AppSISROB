package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleEquipoActivity extends AppCompatActivity {

    TextView txtDetEquipOp1,txtDetEquipOp2 ,txtDetEquipOp4,txtDetEquipOp5,txtDetEquipOp6;
    ImageView DetEquipOp1,DetEquipOp2,DetEquipOp4,DetEquipOp5,DetEquipOp6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipo);
        asignarReferencias();
    }

    private void asignarReferencias(){


        txtDetEquipOp1 = findViewById(R.id.txtDetEquipOp1);
        DetEquipOp1 = findViewById(R.id.DetEquipOp1);

        txtDetEquipOp2 = findViewById(R.id.txtDetEquipOp2);
        DetEquipOp2 = findViewById(R.id.DetEquipOp2);

        txtDetEquipOp4 = findViewById(R.id.txtDetEquipOp4);
        DetEquipOp4 = findViewById(R.id.DetEquipOp4);

        txtDetEquipOp5 = findViewById(R.id.txtDetEquipOp5);
        DetEquipOp5 = findViewById(R.id.DetEquipOp5);

        txtDetEquipOp6 = findViewById(R.id.txtDetEquipOp6);
        DetEquipOp6 = findViewById(R.id.DetEquipOp6);

        txtDetEquipOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,VisorPdfActivity.class);
                startActivity(intent);
            }
        });

        DetEquipOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,VisorPdfActivity.class);
                startActivity(intent);
            }
        });

        txtDetEquipOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,VisorPdfTablero.class);
                startActivity(intent);
            }
        });

        DetEquipOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,VisorPdfTablero.class);
                startActivity(intent);
            }
        });

        txtDetEquipOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,VisorPdfPartes.class);
                startActivity(intent);
            }
        });

        DetEquipOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,VisorPdfPartes.class);
                startActivity(intent);
            }
        });

        txtDetEquipOp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,FlotaActivity.class);
                startActivity(intent);
            }
        });

        DetEquipOp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,FlotaActivity.class);
                startActivity(intent);
            }
        });

        txtDetEquipOp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,StockUMActivity.class);
                startActivity(intent);
            }
        });

        DetEquipOp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DetalleEquipoActivity.this,StockUMActivity.class);
                startActivity(intent);
            }
        });

    }

}