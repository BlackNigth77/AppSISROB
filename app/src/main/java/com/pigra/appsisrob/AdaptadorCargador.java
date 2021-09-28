package com.pigra.appsisrob;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pigra.appsisrob.entidades.Equipo;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCargador extends RecyclerView.Adapter<AdaptadorCargador.MyViewHodler> {

    private Context context;
    private List<Equipo> listaEquipos = new ArrayList<>();

    public AdaptadorCargador (Context context,List<Equipo> listaEquipos){
        this.context= context ;
        this.listaEquipos =listaEquipos;
    }


    @NonNull
    @Override
    public AdaptadorCargador.MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_equipo, parent,false);

        return new AdaptadorCargador.MyViewHodler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorCargador.MyViewHodler holder, int position) {


        holder.filaMarca.setText(listaEquipos.get(position).getMarca()+"");
        holder.filaModelo.setText(listaEquipos.get(position).getModelo()+"");
        holder.filaTipo.setText(listaEquipos.get(position).getTipo()+"");


        holder.btnEditarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetalleEquipoActivity.class);
                intent.putExtra("id", listaEquipos.get(position).getId()+"");
                intent.putExtra("marca", listaEquipos.get(position).getMarca()+"");
                intent.putExtra("tipo", listaEquipos.get(position).getTipo()+"");
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        TextView filaMarca, filaModelo,filaTipo;
        ImageButton btnEditarEquipo;
        ImageView ImageEquipo;

        public MyViewHodler (@NonNull View itemView) {
            super(itemView);
            filaMarca = itemView.findViewById(R.id.filaMarca);
            filaModelo = itemView.findViewById(R.id.filaModelos);
            filaTipo = itemView.findViewById(R.id.filaTipo);
            btnEditarEquipo = itemView.findViewById(R.id.btnEditarEquipo);


            ImageEquipo = itemView.findViewById(R.id.ImgEquipo);
            ImageEquipo.setImageResource(R.drawable.eqcargador);


        }
    }

}
