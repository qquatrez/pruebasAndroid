package com.example.pruebasandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //version DB
    public static final int DATABASE_VERSION = 1;
    //nombre DB
    public static final String DATABASE_NOMBRE = "agenda_db";
    //nombre tabla
    public static final String TABLA_CONTACTOS = "t_contactos";

    //Create table
    private String CREAR_TABLA_CONTACTOS = " CREATE TABLE "+ TABLA_CONTACTOS + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nombre TEXT NOT NULL,"+
            "telefono TEXT + NOT NULL,"+
            "correo TEXT)";

    private String BORRAR_TABLA_CONTACTOS = "DROP TABLE "+ TABLA_CONTACTOS;
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_CONTACTOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BORRAR_TABLA_CONTACTOS);
        onCreate(db);


    }
}
