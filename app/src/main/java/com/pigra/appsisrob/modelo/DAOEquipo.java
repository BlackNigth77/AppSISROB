package com.pigra.appsisrob.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.pigra.appsisrob.entidades.Equipo;
import com.pigra.appsisrob.utilitarios.Constantes;
import com.pigra.appsisrob.utilitarios.ConeccionDB;



public class DAOEquipo {

    ConeccionDB equipoBD;
    SQLiteDatabase db1;
    private Context context;

    public DAOEquipo(Context context) {
        this.context = context;
        equipoBD = new ConeccionDB (context);
    }

    public void abrirBD(){
        db1 = equipoBD.getWritableDatabase() ;
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
            long resultado =  db1.insert(Constantes.NOMBRE_TABLA5,null,valores);

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


}


