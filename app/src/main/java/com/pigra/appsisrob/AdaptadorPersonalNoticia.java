package com.pigra.appsisrob;

        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AlertDialog;
        import androidx.recyclerview.widget.RecyclerView;

        import com.pigra.appsisrob.entidades.Noticia;
        import com.pigra.appsisrob.modelo.DAONoticia;

        import java.util.ArrayList;
        import java.util.List;


public class AdaptadorPersonalNoticia extends RecyclerView.Adapter<AdaptadorPersonalNoticia.MyViewHolder>
{

    private Context context;
    private List<Noticia> listaNoticias = new ArrayList<>();

    public AdaptadorPersonalNoticia (Context context,List<Noticia> listaNoticias){
        this.context= context ;
        this.listaNoticias =listaNoticias;
    }

    @NonNull
    @Override
    public AdaptadorPersonalNoticia.MyViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_noticias, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalNoticia.MyViewHolder holder, int position) {

        holder.filaTituloNoticia.setText(listaNoticias.get(position).getTitulo()+"");
        holder.filaFechaNoticia.setText(listaNoticias.get(position).getFecha()+"");



        holder.btnEditarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, RegistrarNoticiasActivity.class);
                intent.putExtra("id", listaNoticias.get(position).getId()+"");
                intent.putExtra("titulo", listaNoticias.get(position).getTitulo()+"");
                intent.putExtra("fecha", listaNoticias.get(position).getFecha()+"");
                intent.putExtra("detalle",listaNoticias.get(position).getDetalle()+"");
                context.startActivity(intent);
            }
        });



        holder.btnEliminarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle("Confirmar!");
                ventana.setMessage("Desea eliminar la noticia: "+listaNoticias.get(position).getTitulo());
                ventana.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DAONoticia daoNoticia = new DAONoticia(context);
                        daoNoticia.abrirBD();
                        String resultado = daoNoticia.eliminarNoticia(listaNoticias.get(position).getId());

                        AlertDialog.Builder v2 = new AlertDialog.Builder(context);
                        v2.setTitle("Información!");
                        v2.setMessage(resultado);
                        v2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(context,RegistrarNoticiasActivity.class);
                                context.startActivity(intent);
                            }
                        });
                        v2.create().show();

                    }
                });
                ventana.setNegativeButton("No",null);
                ventana.create().show();
            }
        });



    }


    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView filaTituloNoticia, filaFechaNoticia;
        ImageButton btnEditarNoticia, btnEliminarNoticia;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);
            filaTituloNoticia = itemView.findViewById(R.id.filaTituloNoticia);
            filaFechaNoticia = itemView.findViewById(R.id.filaFechaNoticia);
            btnEditarNoticia = itemView.findViewById(R.id.btnEditarNoticia);
            btnEliminarNoticia = itemView.findViewById(R.id.btnEliminarNoticia);

        }
    }
}
