package com.pigra.appsisrob.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pigra.appsisrob.entidades.Equipo;
import com.pigra.appsisrob.entidades.ReporteFalla;
import com.pigra.appsisrob.utilitarios.Constantes;
import com.pigra.appsisrob.utilitarios.EquipoDB;
import com.pigra.appsisrob.utilitarios.ReporteFallasDB;

import java.util.ArrayList;
import java.util.List;


public class DAOReporteFalla {

    ReporteFallasDB reporteFallasDB;
    SQLiteDatabase db;
    private Context context;

    public DAOReporteFalla(Context context) {
        this.context = context;
        reporteFallasDB = new ReporteFallasDB (context);
    }

    public void abrirBD(){
        db = reporteFallasDB.getWritableDatabase() ;
    }

    public String registrarReporteFalla(ReporteFalla reporteFalla){

        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("fecha", reporteFalla.getFecha());
            valores.put("unidad_minera",reporteFalla.getUnidad_minera());
            valores.put("equipo",reporteFalla.getEquipo());
            valores.put("modelo",reporteFalla.getTipo());
            valores.put("sistema",reporteFalla.getSistema());
            valores.put("observacion",reporteFalla.getObservacion());
            valores.put("foto",reporteFalla.getFoto());
            valores.put("ubicacion",reporteFalla.getUbicacion());
            long resultado =  db.insert(Constantes.NOMBRE_TABLA3,null,valores);

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al Insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Insertar el Reporte de Fallas";
            else
                //Toast.makeText(this.context, "Se Inserto el Equipo correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Inserto Correctamente el Reporte de Fallas";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public String actualizarReporteFalla(ReporteFalla reporteFalla){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", reporteFalla.getId());
            valores.put("fecha", reporteFalla.getFecha());
            valores.put("unidad_minera",reporteFalla.getUnidad_minera());
            valores.put("equipo",reporteFalla.getEquipo());
            valores.put("modelo",reporteFalla.getTipo());
            valores.put("sistema",reporteFalla.getSistema());
            valores.put("observacion",reporteFalla.getObservacion());
            valores.put("foto",reporteFalla.getFoto());
            valores.put("ubicacion",reporteFalla.getUbicacion());

            long resultado =  db.update(Constantes.NOMBRE_TABLA3, valores,
                    "id=" + reporteFalla.getId(),null);

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Actualizar el Reporte de Fallas\"";
            else
                //Toast.makeText(this.context, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Actualizó correctamente los datos del Reporte de Fallas\"";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public String eliminarReporteFalla(int _id){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", _id);

            long resultado =  db.delete(Constantes.NOMBRE_TABLA3,"id=" + _id,null );

            if (resultado==-1)
                //Toast.makeText(this.context, "Error al insertar", Toast.LENGTH_SHORT).show();
                rspta = "Error al Eliminar el Reporte de Fallas";
            else
                //Toast.makeText(this.context, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                rspta ="Se Eliminó Correctamente el Reporte de Fallas";
        }
        catch (Exception ex){
            //Toast.makeText(this.context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public List<ReporteFalla> getAllReporteFalla(){
        List<ReporteFalla> lstReporteFallas = new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA3,null);
            while (cursor.moveToNext())
            {
                lstReporteFallas.add(new ReporteFalla(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)));
            }

        }
        catch(Exception e)
        {
            Log.d("=>",e.getMessage());
        }
        return lstReporteFallas;
    }

}
