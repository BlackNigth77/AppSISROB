package com.pigra.appsisrob.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.pigra.appsisrob.entidades.Noticia;
import com.pigra.appsisrob.utilitarios.Constantes;
import com.pigra.appsisrob.utilitarios.NoticiaDB;

import java.util.ArrayList;
import java.util.List;


public class DAONoticia {

    NoticiaDB noticiaDB;
    SQLiteDatabase db;
    private Context context;

    public DAONoticia(Context context) {
        this.context = context;
        noticiaDB = new NoticiaDB (context);
    }

    public void abrirBD(){
        db = noticiaDB.getWritableDatabase() ;
    }

    public String registrarNoticia(Noticia noticia){


        String rspta;

        try {
            ContentValues valores = new ContentValues();
            valores.put("titulo",noticia.getTitulo());
            valores.put("fecha",noticia.getFecha());
            valores.put("detalle",noticia.getDetalle());

            long resultado =  db.insert(Constantes.NOMBRE_TABLA2,null,valores);

            if (resultado==-1) {
                Toast.makeText(this.context, noticia.getFecha(), Toast.LENGTH_SHORT).show();
                rspta = "Error al Insertar la Noticia";
            }else
                //Toast.makeText(this.context, "Se Inserto el Equipo correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Inserto Correctamente la Noticia";

        }catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;

    }


    public String actualizarNoticia(Noticia noticia){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", noticia.getId());
            valores.put("titulo", noticia.getTitulo());
            valores.put("fecha",noticia.getFecha());
            valores.put("detalle",noticia.getDetalle());

            long resultado =  db.update(Constantes.NOMBRE_TABLA2, valores,
                    "id=" + noticia.getId(),null);

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Actualizar la Noticia";
            else
                //Toast.makeText(this.context, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Actualizó correctamente los datos la Noticia";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }


    public String eliminarNoticia(int _id){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", _id);

            long resultado =  db.delete(Constantes.NOMBRE_TABLA2,"id=" + _id,null );

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Eliminar la Noticia";
            else
                //Toast.makeText(this.context, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Eliminó Correctamente la Noticia";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public List<Noticia> getAllNoticias(){
        List<Noticia> lstNoticias = new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA2,null);
            while (cursor.moveToNext())
            {
                lstNoticias.add(new Noticia(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            }

        }
        catch(Exception e)
        {
            Log.d("=>",e.getMessage());
        }
        return lstNoticias;
    }



}


