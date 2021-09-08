package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ValRecupPswActivity extends AppCompatActivity {

    Button btnInicioSes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_val_recup_psw);
        asignarReferencias();
    }

    private void asignarReferencias() {

        btnInicioSes=findViewById(R.id.btnInicioSes);

        btnInicioSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ValRecupPswActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}