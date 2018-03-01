package com.example.u4firebasedni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FormularioActivity extends AppCompatActivity {

    EditText etNombre, etDNI, etProfesion;
    Button btnInsertar, btnModificar;

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = (EditText) findViewById(R.id.etFormNombre);
        etDNI = (EditText) findViewById(R.id.etFormDNI);
        etProfesion = (EditText) findViewById(R.id.etFormProfesion);
        btnInsertar = (Button) findViewById(R.id.botonInsertar);
        btnModificar = (Button) findViewById(R.id.botonModificar);

        Bundle b = getIntent().getExtras();
        if (b != null) {

            btnInsertar.setEnabled(false);
            etDNI.setEnabled(false);

            CEmpleado e = b.getParcelable(MainActivity.EXTRA_EMPLEADO);

            etNombre.setText(e.getNombre());
            etDNI.setText(e.getDni());
            etProfesion.setText(e.getProfesion());
        } else {
            btnModificar.setEnabled(false);
        }

    }

    public void btnInsertar(View view) {
        String nombre = etNombre.getText().toString();
        String dni = etDNI.getText().toString();
        String profesion = etProfesion.getText().toString();


        if (nombre.equals("") || dni.equals("") || profesion.equals("")) {
            Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            CEmpleado nuevoempleado = new CEmpleado(nombre, dni, profesion);
            dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

            //String nueva_clave=dbRef.push().setValue(nuevoJugador, new DatabaseReference.CompletionListener(){
            dbRef.child(dni).setValue(nuevoempleado, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        Toast.makeText(getApplicationContext(), "Insertado correctamente", Toast.LENGTH_SHORT).show();
                        limpiarFormulario();
                    } else {
                        Toast.makeText(getApplicationContext(), "No se puede insertar el empleado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void btnModificar(View view) {
        String nombre = etNombre.getText().toString();
        String dni = etDNI.getText().toString();
        String profesion = etProfesion.getText().toString();

        if (nombre.equals("") || dni.equals("") || profesion.equals("")) {
            Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            CEmpleado nuevoempleado = new CEmpleado(nombre, dni, profesion);
            dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

            //String nueva_clave=dbRef.push().setValue(nuevoJugador, new DatabaseReference.CompletionListener(){
            dbRef.child(dni).setValue(nuevoempleado, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        Toast.makeText(getApplicationContext(), "Insertado correctamente", Toast.LENGTH_SHORT).show();
                        limpiarFormulario();
                    } else {
                        Toast.makeText(getApplicationContext(), "No se puede modificar el empleado", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etDNI.setText("");
        etProfesion.setText("");
    }

}
