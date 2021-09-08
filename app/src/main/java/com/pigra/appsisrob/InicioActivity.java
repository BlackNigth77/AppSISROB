package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inicio);

        //Agregar Animaciones

        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.deplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        TextView lblDetalle = findViewById(R.id.lblDetalle);
        TextView lblEmpresa = findViewById(R.id.lblEmpresa);
        ImageView imgLogoApp = findViewById(R.id.imgLogoApp);

        lblDetalle.setAnimation(animacion2);
        lblEmpresa.setAnimation(animacion2);
        imgLogoApp.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(InicioActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}