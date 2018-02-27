package com.example.u4firebasedni;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DIDACT on 27/02/2018.
 */

public class AdaptadorEmpleado extends ArrayAdapter<CEmpleado> {

    ArrayList<CEmpleado> empleados;
    Context c;

    public AdaptadorEmpleado (Context c, ArrayList<CEmpleado> empleados){
        super(c,R.layout.item_empleado, empleados);
        this.c = c;
        this.empleados = empleados;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_empleado,null);

        //TextView Nombre
        TextView tvnombre=(TextView)item.findViewById(R.id.tvFormNombre);
        tvnombre.setText(empleados.get(position).getNombre());

        //TextView DNI
        TextView tvdni=(TextView)item.findViewById(R.id.tvFormDNI);
        tvdni.setText(""+empleados.get(position).getDni());

        //TextView Profesión
        TextView tvprofesion=(TextView)item.findViewById(R.id.tvFormProfesion);
        tvprofesion.setText(empleados.get(position).getProfesion());

        return item;
    }
}
