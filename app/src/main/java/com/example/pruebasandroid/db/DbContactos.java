package com.example.pruebasandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.pruebasandroid.entidades.Contactos;

import java.util.ArrayList;

public class DbContactos extends DbHelper {
    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarContacto(String nombre, String telefono, String correo){
        long id=0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo", correo);

            id = db.insert(TABLA_CONTACTOS, null, values);
        }
        catch (Exception ex){
            ex.toString();

        }
        return  id;
    }

    public ArrayList <Contactos> mostrarContactos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos= new ArrayList<>();
        Contactos contacto = null;
        Cursor cursorContactos= null;

        String consultaContactos = "select * from " + TABLA_CONTACTOS;

        cursorContactos= db.rawQuery(consultaContactos, null);

        if(cursorContactos.moveToFirst()){
            do{
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo(cursorContactos.getString(3));
                listaContactos.add(contacto);
            }while (cursorContactos.moveToNext());
        }

        return  listaContactos;
    }

}
