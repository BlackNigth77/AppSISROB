package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.pigra.appsisrob.entidades.Video;
import com.pigra.appsisrob.modelo.DAOVideo;


public class VisorVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,YouTubePlayer.PlaybackEventListener {

    YouTubePlayerView youTubePlayerView;
    TextView lglTituloYoutube ;

    Video video;
    String  ruta;
    String  titulo;

    DAOVideo daoVideo = new DAOVideo(this);
    String claveYoutube = "AIzaSyAYfC1vcQ7SrmzWb2dKQcOm7EzWpg3cBbQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_video);
        daoVideo.abrirBD();

        lglTituloYoutube = findViewById(R.id.lglTituloYoutube);

        if(getIntent().hasExtra("id")){

            ruta = getIntent().getStringExtra("ruta");
            titulo= getIntent().getStringExtra("titulo");
            lglTituloYoutube.setText(titulo);



        }



        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(claveYoutube,this);

}

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if(!b){
             youTubePlayer.cueVideo(ruta);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

         if(youTubeInitializationResult.isUserRecoverableError()){
             youTubeInitializationResult.getErrorDialog(this,1).show();

         }else{
             String error = "Error al Inicializar Youtube" + youTubeInitializationResult.toString();
             Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();

         }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          if(resultCode==1){

              getYouTubePlayerProvider().initialize(claveYoutube,this);

          }

    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider(){
       return youTubePlayerView;
    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }
}