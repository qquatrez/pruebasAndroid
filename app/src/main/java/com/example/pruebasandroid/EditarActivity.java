package com.example.pruebasandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebasandroid.db.DbContactos;
import com.example.pruebasandroid.entidades.Contactos;

public class EditarActivity extends AppCompatActivity {
    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar;
    boolean correcto = false;

    Contactos contacto;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre=findViewById(R.id.txtNombre);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtCorreo=findViewById(R.id.txtCorreo);
        btnGuardar=findViewById(R.id.btnGuardar);

        if(savedInstanceState ==null){
            Bundle extras = getIntent().getExtras();

            if(extras==null){
                id = Integer.parseInt(null);
            }
            else{
                id = extras.getInt("ID");
            }
        }
        else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos = new DbContactos(EditarActivity.this);
        contacto = dbContactos.verContacto(id);

        if(contacto!=null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo());

        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txtNombre.getText().toString().equals("")&& !txtTelefono.getText().toString().equals("")&&  !txtCorreo.getText().toString().equals("")){
                    correcto= dbContactos.editarContacto(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "registro actualizado", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }
                    else{
                        Toast.makeText(EditarActivity.this, "Error al actualizar registro", Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(EditarActivity.this, "Se deben cargar todos los campos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

}