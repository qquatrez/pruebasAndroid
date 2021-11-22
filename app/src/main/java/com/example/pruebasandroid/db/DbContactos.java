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

        cursorContactos.close();
        return  listaContactos;
    }

    public Contactos verContacto(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos= new ArrayList<>();
        Contactos contacto = null;
        Cursor cursorContactos= null;

        String consultaContactos = "select * from " + TABLA_CONTACTOS + " where id = "+ id + " limit 1";

        cursorContactos= db.rawQuery(consultaContactos, null);

        if(cursorContactos.moveToFirst()){
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo(cursorContactos.getString(3));
        }
        cursorContactos.close();
        return  contacto;
    }

    public boolean editarContacto(int id, String nombre, String telefono, String correo){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String actualizarContacto = "update " + TABLA_CONTACTOS + " SET nombre= '"+nombre
                +"', telefono= '"+ telefono
                + "', correo='"+correo
                +"' where id = " +id;
        try{
            db.execSQL(actualizarContacto);
            correcto = true;
        }
        catch (Exception ex){
            ex.toString();
            correcto = false;
        }
        finally {
            db.close();
        }

        return correcto;
    }


}
