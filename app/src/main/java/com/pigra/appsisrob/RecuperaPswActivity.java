package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecuperaPswActivity extends AppCompatActivity {

    Button btnRecuperar;
    TextView lblRecuperaPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_psw);
        asignarReferencias();
    }


    private void asignarReferencias(){

        btnRecuperar=findViewById(R.id.btnRecuperar);
        lblRecuperaPsw=findViewById(R.id.lblRecuperaT2);

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RecuperaPswActivity.this,ValRecupPswActivity.class);
                startActivity(intent);
            }
        });

        lblRecuperaPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RecuperaPswActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}