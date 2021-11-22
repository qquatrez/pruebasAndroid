package com.example.pruebasandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pruebasandroid.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {
    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre=findViewById(R.id.txtNombre);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtCorreo=findViewById(R.id.txtCorreo);
        btnGuardar= findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());
                if(id>0){
                    Toast.makeText(NuevoActivity.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                }
                else{
                    Toast.makeText(NuevoActivity.this, "Error al Guardar", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

    }
}