package com.pigra.appsisrob.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pigra.appsisrob.entidades.Equipo;
import com.pigra.appsisrob.utilitarios.Constantes;
import com.pigra.appsisrob.utilitarios.EquipoDB;

import java.util.ArrayList;
import java.util.List;


public class DAOEquipo {

    EquipoDB equipoBD;
    SQLiteDatabase db;
    private Context context;

    public DAOEquipo(Context context) {
        this.context = context;
        equipoBD = new EquipoDB (context);
    }

    public void abrirBD(){
        db = equipoBD.getWritableDatabase() ;
    }

    public String registrarEquipo(Equipo equipo){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("descripcion", equipo.getDescripcion());
            valores.put("tipo",equipo.getTipo());
            valores.put("marca",equipo.getMarca());
            valores.put("modelo",equipo.getModelo());
            valores.put("capacidad",equipo.getCapacidad());
            long resultado =  db.insert(Constantes.NOMBRE_TABLA5,null,valores);

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al Insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Insertar el Equipo";
            else
                //Toast.makeText(this.context, "Se Inserto el Equipo correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Inserto Correctamente el Equipo";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public String actualizarEquipo(Equipo equipo){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", equipo.getId());
            valores.put("descripcion", equipo.getDescripcion());
            valores.put("tipo", equipo.getTipo());
            valores.put("marca",equipo.getMarca());
            valores.put("modelo",equipo.getModelo());
            valores.put("capacidad",equipo.getCapacidad());

            long resultado =  db.update(Constantes.NOMBRE_TABLA5, valores,
                    "id=" + equipo.getId(),null);

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Actualizar el Equipo";
            else
                //Toast.makeText(this.context, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Actualizó correctamente los datos del Equipo";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public String eliminarEquipo(int _id){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", _id);

            long resultado =  db.delete(Constantes.NOMBRE_TABLA5,"id=" + _id,null );

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Eliminar el Equipo";
            else
                //Toast.makeText(this.context, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Eliminó Correctamente el Equipo";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public List<Equipo> getAllEquipos(){
        List<Equipo> lstEquipos = new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA5,null);
            while (cursor.moveToNext())
            {
                lstEquipos.add(new Equipo(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)));
            }

        }
        catch(Exception e)
        {
            Log.d("=>",e.getMessage());
        }
        return lstEquipos;
    }

    public List<Equipo> getAllEquiposTipo(String _tipo){
        List<Equipo> lstEquipos = new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA5 + " WHERE tipo = "+_tipo,null);
            while (cursor.moveToNext())
            {
                lstEquipos.add(new Equipo(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)));
            }

        }
        catch(Exception e)
        {
            Log.d("=>",e.getMessage());
        }
        return lstEquipos;
    }

}


