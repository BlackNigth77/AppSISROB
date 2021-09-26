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

import com.pigra.appsisrob.entidades.Equipo;
import com.pigra.appsisrob.entidades.Video;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.MyViewHodler> {

    private Context context;
    private List<Equipo> listaEquipos = new ArrayList<>();

    public AdaptadorEquipos (Context context,List<Video> listaVideos){
        this.context= context ;
        this.listaEquipos =listaEquipos;
    }

    @NonNull
    @Override
    public AdaptadorEquipos.MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_equipo, parent,false);

        return new AdaptadorEquipos.MyViewHodler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorEquipos.MyViewHodler holder, int position) {

        holder.filaMarca.setText(listaEquipos.get(position).getMarca()+"");
        holder.filaModelo.setText(listaEquipos.get(position).getModelo()+"");


        holder.btnEditarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, VisorVideoActivity.class);
                intent.putExtra("id", listaEquipos.get(position).getId()+"");
                intent.putExtra("marca", listaEquipos.get(position).getMarca()+"");
                intent.putExtra("modelo", listaEquipos.get(position).getModelo()+"");
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        TextView filaMarca, filaModelo;
        ImageButton btnEditarEquipo;

        public MyViewHodler (@NonNull View itemView) {
            super(itemView);
            filaMarca = itemView.findViewById(R.id.filaMarca);
            filaModelo = itemView.findViewById(R.id.filaModelo);
            btnEditarEquipo = itemView.findViewById(R.id.btnEditarEquipo);


        }
    }

}
