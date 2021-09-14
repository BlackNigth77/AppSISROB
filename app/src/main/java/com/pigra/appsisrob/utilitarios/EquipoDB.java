package com.pigra.appsisrob.utilitarios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EquipoDB extends SQLiteOpenHelper {

  public EquipoDB(Context context) {
    super(context,Constantes.NOMBRE_BD,null,Constantes.VERSION);
  }


  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {

    String query = "" +
            "CREATE TABLE " + Constantes.NOMBRE_TABLA5 +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "  descripcion TEXT NOT NULL, " +
            "  tipo TEXT NOT NULL, " +
            "  marca TEXT NOT NULL, " +
            "  modelo TEXT NOT NULL, " +
            "  capacidad INTEGER NOT NULL); ";
    sqLiteDatabase.execSQL(query);

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {

  }


}
