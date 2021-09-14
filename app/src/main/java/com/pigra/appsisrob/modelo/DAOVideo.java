package com.pigra.appsisrob.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pigra.appsisrob.entidades.Video;
import com.pigra.appsisrob.utilitarios.Constantes;
import com.pigra.appsisrob.utilitarios.VideoDB;

import java.util.ArrayList;
import java.util.List;


public class DAOVideo {

    VideoDB videoDB;
    SQLiteDatabase db;
    private Context context;

    public DAOVideo(Context context) {
        this.context = context;
        videoDB = new VideoDB (context);
    }

    public void abrirBD(){
        db = videoDB.getWritableDatabase() ;
    }

    public String registrarVideo(Video video){


        String rspta;

        try {
            ContentValues valores = new ContentValues();
            valores.put("titulo",video.getTitulo());
            valores.put("duracion",video.getDuracion());
            valores.put("ruta",video.getRuta());

            long resultado =  db.insert(Constantes.NOMBRE_TABLA1,null,valores);

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al Insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Insertar el Video";
            else
                //Toast.makeText(this.context, "Se Inserto el Equipo correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Inserto Correctamente el Video";

            }catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;

    }

    public String actualizarVideo(Video video){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", video.getId());
            valores.put("titulo", video.getTitulo());
            valores.put("duracion",video.getDuracion());
            valores.put("ruta",video.getRuta());

            long resultado =  db.update(Constantes.NOMBRE_TABLA1, valores,
                    "id=" + video.getId(),null);

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Actualizar el Video";
            else
                //Toast.makeText(this.context, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Actualizó correctamente los datos del Video";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public String eliminarVideo(int _id){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", _id);

            long resultado =  db.delete(Constantes.NOMBRE_TABLA1,"id=" + _id,null );

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Eliminar el Video";
            else
                //Toast.makeText(this.context, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Eliminó Correctamente el Video";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public List<Video> getAllVideos(){
        List<Video> lstVideos = new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA1,null);
            while (cursor.moveToNext())
            {
                lstVideos.add(new Video(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3)));
            }

        }
        catch(Exception e)
        {
            Log.d("=>",e.getMessage());
        }
        return lstVideos;
    }


}
