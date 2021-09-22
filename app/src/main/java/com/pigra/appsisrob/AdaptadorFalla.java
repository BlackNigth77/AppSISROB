package com.pigra.appsisrob;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pigra.appsisrob.R;
import com.pigra.appsisrob.RegistroFallasActivity;
import com.pigra.appsisrob.entidades.ReporteFalla;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorFalla extends RecyclerView.Adapter<AdaptadorFalla.vistaHolder> {
    private Context context;
    private List<ReporteFalla> listaFalla = new ArrayList<>();

    public AdaptadorFalla(Context c, List<ReporteFalla> lista){
        this.context = c;
        this.listaFalla = lista;

    }

    @NonNull
    @Override

    public AdaptadorFalla.vistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.fila_fallas,parent,false);
        return new vistaHolder(vista);
    }

    @Override

    public void onBindViewHolder(@NonNull AdaptadorFalla.vistaHolder holder, @SuppressLint("RecyclerView") final int position) {
        //holder.filaIdFalla.setText(String.valueOf(listaFalla.get(position).getId()));
        holder.filaFechaFalla.setText(String.valueOf(listaFalla.get(position).getFecha()));
        holder.filaUnidadFalla.setText(String.valueOf(listaFalla.get(position).getUnidad_minera()));
        holder.filaEquipoFalla.setText(String.valueOf(listaFalla.get(position).getEquipo()));
        holder.filaTipoFalla.setText(String.valueOf(listaFalla.get(position).getTipo()));
        holder.btnEditarFalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegistroFallasActivity.class);
                intent.putExtra("id",listaFalla.get(position).getId());
                intent.putExtra("fecha",listaFalla.get(position).getFecha());
                intent.putExtra("unidad_minera",listaFalla.get(position).getUnidad_minera());
                intent.putExtra("equipo",listaFalla.get(position).getEquipo());
                intent.putExtra("tipo",listaFalla.get(position).getTipo());
                intent.putExtra("sistema",listaFalla.get(position).getSistema());
                intent.putExtra("observacion",listaFalla.get(position).getObservacion());
                intent.putExtra("foto",listaFalla.get(position).getFoto());
                intent.putExtra("ubicacion",listaFalla.get(position).getUbicacion());
                context.startActivity(intent);
            }
        });

        holder.btnEliminaFalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setMessage("Desea eliminar la falla?");
                ventana.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase fbDatabase;
                        DatabaseReference dbReference;
                        FirebaseApp.initializeApp(context);
                        fbDatabase = FirebaseDatabase.getInstance();
                        dbReference = fbDatabase.getReference();

                        dbReference.child("ReporteFalla").child(listaFalla.get(position).getId()).removeValue();
                    }
                });
                ventana.setNegativeButton("NO", null);
                ventana.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaFalla.size();
    }

    public class vistaHolder extends RecyclerView.ViewHolder {
        TextView filaFechaFalla, filaUnidadFalla, filaEquipoFalla, filaTipoFalla;
        ImageButton btnEditarFalla,btnEliminaFalla ;
        public vistaHolder(@NonNull View itemView) {
            super(itemView);
            filaFechaFalla = itemView.findViewById(R.id.filaFechaFalla);
            filaUnidadFalla = itemView.findViewById(R.id.filaUnidadFalla);
            filaEquipoFalla = itemView.findViewById(R.id.filaEquipoFalla);
            filaTipoFalla = itemView.findViewById(R.id.filaTipoFalla);
            btnEditarFalla = itemView.findViewById(R.id.btnEditarFalla);
            btnEliminaFalla = itemView.findViewById(R.id.btnEliminaFalla);


        }

    }

}
        /*
        filaIdFalla, filafechaFalla, filaUnidadFalla, filaEquipoFalla, filaTipoFalla

        fechaFalla = txtFechaFalla.getText().toString();
        unidadMinera = txtUnidadMinera.getText().toString();
        equipoFalla = txtEquipoFalla.getText().toString();
        tipoFalla = txtTipoFalla.getText().toString();
        sistemaFalla = txtSistemaFalla.getText().toString();
        foto = txtFotoFalla.getText().toString();
        observacionFalla = txtObservacionFalla.getText().toString();
        ubicacionFalla = txtUbicacionFalla.getText().toString();*/


