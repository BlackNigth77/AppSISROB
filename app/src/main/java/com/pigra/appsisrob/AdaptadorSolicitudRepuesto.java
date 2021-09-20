package com.pigra.appsisrob;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pigra.appsisrob.entidades.SolicitudRepuesto;
import com.pigra.appsisrob.modelo.DAOSolicitudRepuesto;
import com.pigra.appsisrob.utilitarios.SolicitudRepuestoDB;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorSolicitudRepuesto extends RecyclerView.Adapter<AdaptadorSolicitudRepuesto.vistaHolder> {
    private Context context;
    private List<SolicitudRepuesto> listaSolicitudes = new ArrayList<>();

    public AdaptadorSolicitudRepuesto(Context context, List<SolicitudRepuesto> lstSOl ) {
        this.context = context;
        this.listaSolicitudes = lstSOl;
    }

    @NonNull
    @Override
    public vistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_solicitud_repuesto, parent,false);
        return new vistaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vistaHolder holder, int position) {
        holder.txtFilCodigo.setText( String.valueOf(listaSolicitudes.get(position).getCodigo()));
        holder.txtFilDescSolRep.setText(String.valueOf(listaSolicitudes.get(position).getDescripcion()));
        holder.txtFilCantidad.setText(String.valueOf(listaSolicitudes.get(position).getCantidad()));
        holder.imgButEditSolRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, RegistroSolicitud.class);
                intent.putExtra("id", listaSolicitudes.get(position).getId()+"");
                intent.putExtra("codigo", listaSolicitudes.get(position).getCodigo()+"");
                intent.putExtra("fecha", listaSolicitudes.get(position).getFecha()+"");
                intent.putExtra("descripcion", listaSolicitudes.get(position).getDescripcion()+"");
                intent.putExtra("cantidad", listaSolicitudes.get(position).getCantidad()+"");
                //intent.putExtra("largo", listaSolicitudes.get(position).getLargo()+"");
                context.startActivity(intent);
            }
        });
        holder.imgButDelSolRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle("Confirmación");
                ventana.setMessage("¿Está seguro que desea eliminar la solicitud "
                        + listaSolicitudes.get(position).getCodigo() + "?");
                ventana.setNegativeButton("No", null);

                ventana.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DAOSolicitudRepuesto daoRepuesto= new DAOSolicitudRepuesto(context);
                        daoRepuesto.abrirBD();
                        String resultado = "";// daoRepuesto.eliminarSolicitud(listaSolicitudes.get(position).getId());
                        AlertDialog.Builder wnd = new AlertDialog.Builder(context);
                        wnd.setTitle("Confirmación");
                        wnd.setMessage(resultado);
                        wnd.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(context, RepuestosActivity.class);
                                context.startActivity(intent);
                            }
                        });
                        wnd.create().show();
                    }
                });
                ventana.create().show();
            }
        });
    }

    public void onClickEdit(View view, int position)
    {
        Intent intent= new Intent(context, RegistroSolicitud.class);
        intent.putExtra("id", listaSolicitudes.get(position).getId()+"");
        intent.putExtra("codigo", listaSolicitudes.get(position).getCodigo()+"");
        intent.putExtra("fecha", listaSolicitudes.get(position).getFecha()+"");
        intent.putExtra("descripcion", listaSolicitudes.get(position).getDescripcion()+"");
        intent.putExtra("cantidad", listaSolicitudes.get(position).getCantidad()+"");
        //intent.putExtra("largo", listaSolicitudes.get(position).getLargo()+"");
        context.startActivity(intent);
    }

    public void onClickDelete(View view, int position){
        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
        ventana.setTitle("Confirmación");
        ventana.setMessage("¿Está seguro que desea eliminar la solicitud "
                + listaSolicitudes.get(position).getCodigo() + "?");
        ventana.setNegativeButton("No", null);

        ventana.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DAOSolicitudRepuesto daoRepuesto= new DAOSolicitudRepuesto(context);
                daoRepuesto.abrirBD();
                String resultado = "";// daoRepuesto.eliminarSolicitud(listaSolicitudes.get(position).getId());
                AlertDialog.Builder wnd = new AlertDialog.Builder(context);
                wnd.setTitle("Confirmación");
                wnd.setMessage(resultado);
                wnd.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, RepuestosActivity.class);
                        context.startActivity(intent);
                    }
                });
                wnd.create().show();
            }
        });
        ventana.create().show();
    }

    @Override
    public int getItemCount() {
        return listaSolicitudes.size();
    }


    public class vistaHolder extends RecyclerView.ViewHolder {
        TextView txtFilCodigo, txtFilDescSolRep , txtFilCantidad;
        ImageButton imgButEditSolRep, imgButDelSolRep  ;
        public vistaHolder(@NonNull View itemView) {
            super(itemView);

            txtFilCodigo = itemView.findViewById(R.id.txtFilCodigo);
            txtFilDescSolRep = itemView.findViewById(R.id.txtFilDescSolRep);
            txtFilCantidad = itemView.findViewById(R.id.txtFilCantidad);
            imgButEditSolRep = itemView.findViewById(R.id.imgButEditSolRep);
            imgButDelSolRep = itemView.findViewById(R.id.imgButDelSolRep);

        }
    }
}
