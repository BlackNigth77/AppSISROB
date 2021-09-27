package com.pigra.appsisrob.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pigra.appsisrob.entidades.SolicitudRepuesto;
import com.pigra.appsisrob.utilitarios.Constantes;
import com.pigra.appsisrob.utilitarios.SolicitudRepuestoDB;

import java.util.ArrayList;
import java.util.List;


public class DAOSolicitudRepuesto {
    SolicitudRepuestoDB solicitudRepuestoDB;
    SQLiteDatabase db;
    private Context context;

    public DAOSolicitudRepuesto(Context context) {
        this.context = context;
        solicitudRepuestoDB = new SolicitudRepuestoDB(context);
    }
    public void abrirBD(){
        db = solicitudRepuestoDB.getWritableDatabase() ;
    }

    public String RegistrarSolicitud(SolicitudRepuesto repuesto){
        String rpta;
        try
        {
            String codigo ="";

            List<Integer> cantidad = getCount(repuesto.getCodigo());
            if(cantidad.size()>0) {
                Integer numero = cantidad.get(0);
                numero++;
                codigo = repuesto.getCodigo() + "" + numero.toString();
            }
            else
                codigo = repuesto.getCodigo() + "1";
            ContentValues valores = new ContentValues();
            valores.put("codigo", codigo);
            valores.put("fecha", repuesto.getFecha());
            valores.put("descripcion", repuesto.getDescripcion());
            valores.put("stock", repuesto.getStock());
            valores.put("categoria", repuesto.getCategoria());
            valores.put("cantidad", repuesto.getCantidad());

            long resultado =  db.insert(Constantes.NOMBRE_TABLA4,null,valores);

            if(resultado==-1)
                rpta = "Error al Insertar la solicitud";
            else
                rpta = "Se registro correctamente la solicitud";
        }
        catch (Exception e) {
            rpta = e.getMessage();
        }
       return rpta;
    }

    public String actualizarSolicitud(SolicitudRepuesto repuesto){
        String rspta;
        try {
            ContentValues valores = new ContentValues();

            valores.put("id", repuesto.getId());
            valores.put("codigo", repuesto.getCodigo());
            valores.put("fecha", repuesto.getFecha());
            valores.put("descripcion", repuesto.getDescripcion());
            valores.put("stock", repuesto.getStock());
            valores.put("categoria", repuesto.getCategoria());
            valores.put("cantidad", repuesto.getCantidad());

            long resultado =  db.update(Constantes.NOMBRE_TABLA4, valores,"id=" + repuesto.getId(),null);

            if (resultado==-1)
                rspta = "Error al actualizar";
            else
                rspta ="Se actualizó correctamente";
        }
        catch (Exception ex){
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public String eliminarSolicitud(int _id){
        String rspta;
        try {
            ContentValues valores = new ContentValues();
            valores.put("id", _id);

            long resultado =  db.delete(Constantes.NOMBRE_TABLA4,"id=" + _id,null );

            if (resultado==-1)
                rspta = "Error al eliminar";
            else
                rspta ="Se eliminó correctamente la solicitud";
        }
        catch (Exception ex){
            rspta = ex.getMessage();
        }
        return rspta;
    }

    public List<SolicitudRepuesto> getAllSolicitudes(){
        List<SolicitudRepuesto> lstRet = new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA4,null);
            while (cursor.moveToNext())
            {
                SolicitudRepuesto soli = new SolicitudRepuesto();
                soli.setId(cursor.getInt(0));
                soli.setCodigo(cursor.getString(1));
                soli.setFecha(cursor.getString(2));
                soli.setDescripcion(cursor.getString(3));
                soli.setCategoria(cursor.getInt(4));
                soli.setStock(cursor.getInt(5));
                soli.setCantidad(cursor.getInt(6));

                lstRet.add(soli);
            }
        }
        catch(Exception e)
        {
            Log.d("=>",e.getMessage());
        }
        return lstRet;
    }

    public List<Integer> getCount(String codigo){
        List<Integer> lstRet = new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery(" SELECT count(1) FROM " + Constantes.NOMBRE_TABLA4 +
                                             " WHERE codigo like '" +  codigo + "%'",null);
            while (cursor.moveToNext())
            {
                Integer cant =  cursor.getInt(0) + 1;
                lstRet.add(cant);
            }
        }
        catch(Exception e)
        {
            Log.d("=>",e.getMessage());
        }
        return lstRet;
    }
}
