package com.pigra.appsisrob.utilitarios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SolicitudRepuestoDB extends SQLiteOpenHelper {

    public  SolicitudRepuestoDB(Context context){
        super(context,Constantes.NOMBRE_BD,null,Constantes.VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "" +
                "CREATE TABLE " + Constantes.NOMBRE_TABLA4 +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "  codigo TEXT NOT NULL, " +
                "  fecha TEXT NOT NULL, " +
                "  descripcion TEXT NOT NULL, " +
                "  stock INTEGER NOT NULL, " +
                "  categoria INTEGER NOT NULL, " +
                "  cantidad INTEGER NOT NULL); ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
