package com.example.u4firebasedni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    EditText etNombre, etDNI, etProfesion;
    Button btnInsertar, btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre=(EditText)findViewById(R.id.etFormNombre);
        etDNI=(EditText)findViewById(R.id.etFormDNI);
        etProfesion=(EditText)findViewById(R.id.etFormProfesion);
        btnInsertar=(Button)findViewById(R.id.botonInsertar);
        btnModificar=(Button)findViewById(R.id.botonModificar);

        Bundle b = getIntent().getExtras();
        if (b!=null){

            btnInsertar.setEnabled(false);

            String nombre=b.getString(MainActivity.EXTRA_NOMBRE);
            String dni=b.getString(MainActivity.EXTRA_DNI);
            String profesion=b.getString(MainActivity.EXTRA_PROFESION);

            etNombre.setText(nombre);
            etDNI.setText(dni);
            etProfesion.setText(profesion);
        }else{
            btnModificar.setEnabled(false);
        }

    }

    public void btnInsertar (View view){

    }

    public void btnModificar (View view){

    }

}
