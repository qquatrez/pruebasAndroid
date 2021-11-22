package com.example.pruebasandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pruebasandroid.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCrear=findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db!=null){
                    Toast.makeText(MainActivity.this, "Base de datos creado", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "error al crear base ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    public boolean onOptionsSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoContacto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoContacto(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

}