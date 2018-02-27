package com.example.u4firebasedni;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvempleados;
    ArrayList<CEmpleado> lista_empleados = new ArrayList<CEmpleado>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvempleados=(ListView)findViewById(R.id.lvEmpleados);
        AdaptadorEmpleado adaptadorEmpleado=new AdaptadorEmpleado(this, lista_empleados);
        lvempleados.setAdapter(adaptadorEmpleado);

        lvempleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent (getApplicationContext(),FormularioActivity.class);
                startActivity(i);
            }
        });

        lvempleados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogEliminar();
                return true; //True ya que hay dos eventos de click
            }
        });

        cargarDatos();
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

    public void cargarDatos (){
        lista_empleados.add(new CEmpleado("Pepe Martínez", 124536524+"Y", "Policía"));
        lista_empleados.add(new CEmpleado("María Perez", 23554123+"M", "Médica"));
        lista_empleados.add(new CEmpleado("Luis López", 63524894+"Z", "Bombero"));
        lista_empleados.add(new CEmpleado("Javier Orte", 54782459+"W", "Profesor"));
        lista_empleados.add(new CEmpleado("Luis López", 45876532+"S", "Chef"));
    }
}
