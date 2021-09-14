package com.pigra.appsisrob.utilitarios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReporteFallasDB extends SQLiteOpenHelper  {

    public ReporteFallasDB(Context context) {
        super(context,Constantes.NOMBRE_BD,null,Constantes.VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "" +
                "CREATE TABLE " + Constantes.NOMBRE_TABLA3+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "  fecha TEXT NOT NULL, " +
                "  unidad_minera TEXT NOT NULL, " +
                "  equipo TEXT NOT NULL, " +
                "  tipo TEXT NOT NULL, " +
                "  sistema TEXT NOT NULL, " +
                "  observacion TEXT NOT NULL, " +
                "  foto TEXT NOT NULL, " +
                "  ubicacion TEXT NOT NULL); ";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
