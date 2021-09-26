package com.pigra.appsisrob.utilitarios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConeccionDB extends SQLiteOpenHelper {

    public ConeccionDB(Context context) {
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

        String query2 = "" +
                "CREATE TABLE " + Constantes.NOMBRE_TABLA5 +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "  descripcion TEXT NOT NULL, " +
                "  tipo TEXT NOT NULL, " +
                "  marca TEXT NOT NULL, " +
                "  modelo TEXT NOT NULL, " +
                "  capacidad INTEGER NOT NULL); ";
        sqLiteDatabase.execSQL(query2);


        String query3 = "" +
                "CREATE TABLE " + Constantes.NOMBRE_TABLA2 +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "  titulo TEXT NOT NULL, " +
                "  fecha TEXT NOT NULL, " +
                "  detalle TEXT NOT NULL); ";
        sqLiteDatabase.execSQL(query3);

        String query4 = "" +
                "CREATE TABLE " + Constantes.NOMBRE_TABLA4 +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "  codigo TEXT NOT NULL, " +
                "  fecha TEXT NOT NULL, " +
                "  descripcion TEXT NOT NULL, " +
                "  stock INTEGER NOT NULL, " +
                "  categoria INTEGER NOT NULL, " +
                "  cantidad INTEGER NOT NULL); ";
        sqLiteDatabase.execSQL(query4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

}
