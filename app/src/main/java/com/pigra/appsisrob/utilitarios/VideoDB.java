package com.pigra.appsisrob.utilitarios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VideoDB extends SQLiteOpenHelper {

    public VideoDB(Context context) {
        super(context,Constantes.NOMBRE_BD,null,Constantes.VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "" +
                "CREATE TABLE " + Constantes.NOMBRE_TABLA1 +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "  titulo TEXT NOT NULL, " +
                "  duracion int NOT NULL, " +
                "  ruta TEXT NOT NULL); ";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

}
