package com.example.u4firebasedni;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String EXTRA_EMPLEADO = "EMPLEADO";

    ListView lvempleados;
    ArrayList<CEmpleado> lista_empleados = new ArrayList<CEmpleado>();

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvempleados = (ListView) findViewById(R.id.lvEmpleados);

        cargarDatosFirebase();
    }

    public void btnNuevoEmpleado (View view){
        Intent i=new Intent(getApplicationContext(),FormularioActivity.class);
        startActivity(i);
    }

    private void dialogEliminar (){
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogEliminar dialogo = new DialogEliminar();
        dialogo.show(fragmentManager, "dialogEliminar");
    }

    private void cargarDatosFirebase(){
        //Conexión con la base de datos
        dbRef = FirebaseDatabase.getInstance().getReference().child("empleados"); //Nombre referente a la clase añadida en Firebase
        //Añadimos el evento que nos devolverá los valores
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_empleados.clear();
                for (DataSnapshot empleadosDataSnapshot: dataSnapshot.getChildren()) {
                    cargarListView(empleadosDataSnapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("MainActivity", "DATABASE ERROR");
            }
        };
        dbRef.addValueEventListener(valueEventListener); //Para cargar datos en tiempo real
    }

    private void cargarListView (DataSnapshot dataSnapshot){

        lista_empleados.add(dataSnapshot.getValue(CEmpleado.class));
        AdaptadorEmpleado adaptadorEmpleado = new AdaptadorEmpleado(this, lista_empleados);
        lvempleados.setAdapter(adaptadorEmpleado);

        lvempleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CEmpleado empleadoClicado = (CEmpleado)parent.getItemAtPosition(position);

                Intent i=new Intent(getApplicationContext(), FormularioActivity.class);
                i.putExtra(EXTRA_EMPLEADO,empleadoClicado);

                startActivity(i);
            }
        });

        lvempleados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogEliminar();

                CEmpleado empleadoClicado = (CEmpleado)parent.getItemAtPosition(position);


                dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

                dbRef.child(empleadoClicado.getDni()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            Toast.makeText(getApplicationContext(), "Insertado correctamente", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "No se puede modificar el jugador", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true; //True ya que hay dos eventos de click
            }
        });
    }


}
