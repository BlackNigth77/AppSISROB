package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

     Button btnIngresar;
     TextView lblOlvidoPsw;
    TextView lblOlvidoPsw2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asignarReferencias();
    }

    private void asignarReferencias(){

        btnIngresar=findViewById(R.id.btnIngresar);
        lblOlvidoPsw=findViewById(R.id.txtOlvidoPsw);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        lblOlvidoPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this,RecuperaPswActivity.class);
                startActivity(intent);
            }
        });

    }
}