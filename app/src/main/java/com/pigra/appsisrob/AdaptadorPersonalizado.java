package com.pigra.appsisrob;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pigra.appsisrob.entidades.Video;

import java.util.ArrayList;
import java.util.List;
import android.net.Uri;

import com.pigra.appsisrob.MainActivity;
import com.pigra.appsisrob.R;


public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MyViewHodler>
{

    private Context context;
    private List<Video> listaVideos = new ArrayList<>();

    public AdaptadorPersonalizado (Context context,List<Video> listaVideos){
        this.context= context ;
        this.listaVideos =listaVideos;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizado.MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_videos, parent,false);

        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.MyViewHodler holder, int position) {

        holder.filaTitulo.setText(listaVideos.get(position).getTitulo()+"");
        holder.filaRutaVideo.setText(listaVideos.get(position).getRuta()+"");
        holder.fIlaDuracionVideo.setText(listaVideos.get(position).getDuracion()+" min ");

        holder.btnVerVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, VisorVideoActivity.class);
                intent.putExtra("id", listaVideos.get(position).getId()+"");
                intent.putExtra("titulo", listaVideos.get(position).getTitulo()+"");
                intent.putExtra("duracion", listaVideos.get(position).getDuracion()+"");
                intent.putExtra("ruta", listaVideos.get(position).getRuta()+"");
                context.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return listaVideos.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        TextView filaTitulo, filaRutaVideo,fIlaDuracionVideo;
        ImageButton btnVerVideo;

        public MyViewHodler (@NonNull View itemView) {
            super(itemView);
            filaTitulo = itemView.findViewById(R.id.filaTituloVideo);
            filaRutaVideo = itemView.findViewById(R.id.filaRutaVideo);
            fIlaDuracionVideo = itemView.findViewById(R.id.fIlaDuracionVideo);
            btnVerVideo = itemView.findViewById(R.id.btnVerVideo);

        }
    }
}
