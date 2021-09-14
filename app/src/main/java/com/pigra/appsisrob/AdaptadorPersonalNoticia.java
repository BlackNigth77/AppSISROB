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

        import com.pigra.appsisrob.entidades.Noticia;

        import java.util.ArrayList;
        import java.util.List;
        import android.net.Uri;

        import com.pigra.appsisrob.MainActivity;
        import com.pigra.appsisrob.R;


public class AdaptadorPersonalNoticia extends RecyclerView.Adapter<AdaptadorPersonalNoticia.MyViewHodler>
{

    private Context context;
    private List<Noticia> listaNoticias = new ArrayList<>();

    public AdaptadorPersonalNoticia (Context context,List<Noticia> listaNoticias){
        this.context= context ;
        this.listaNoticias =listaNoticias;
    }

    @NonNull
    @Override
    public AdaptadorPersonalNoticia.MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_noticias, parent,false);

        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalNoticia.MyViewHodler holder, int position) {

        holder.filaTituloNoticia.setText(listaNoticias.get(position).getTitulo()+"");
        holder.filaFechaNoticia.setText(listaNoticias.get(position).getFecha()+"");


        holder.btnEditarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, VisorVideoActivity.class);
                intent.putExtra("id", listaNoticias.get(position).getId()+"");
                intent.putExtra("titulo", listaNoticias.get(position).getTitulo()+"");
                intent.putExtra("fecha", listaNoticias.get(position).getFecha()+"");
                context.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        TextView filaTituloNoticia, filaFechaNoticia;
        ImageButton btnEditarNoticia;

        public MyViewHodler (@NonNull View itemView) {
            super(itemView);
            filaTituloNoticia = itemView.findViewById(R.id.filaTituloNoticia);
            filaFechaNoticia = itemView.findViewById(R.id.filaFechaNoticia);
            btnEditarNoticia = itemView.findViewById(R.id.btnEditarNoticia);

        }
    }
}
