package com.example.transcom_android.adaptador;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.transcom_android.R;
import com.example.transcom_android.data.model.Servicio;

import java.util.ArrayList;

public class AdaptadorListaServicios extends ArrayAdapter<Servicio> {
    private ArrayList<Servicio> servicios;
    private Context context;
    private int layoutId;

    public AdaptadorListaServicios(Context context, int resource, ArrayList<Servicio> servicios){
        super(context,resource,servicios);
        this.layoutId = resource;
        this.servicios = servicios;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;
        final ViewHolder viewHolder;
        if (item == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            item = inflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder();

            item.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) item.getTag();
        }


        viewHolder.textViewServicioTitulo = item.findViewById(R.id.fila_servicio_nombre);
        viewHolder.textViewServicioDescripcion = item.findViewById(R.id.fila_servicio_descripcion);
        viewHolder.textViewServicioEmpresa = item.findViewById(R.id.fila_servicio_empresa);

        final Servicio servicio = servicios.get(position);

        viewHolder.textViewServicioTitulo.setText("Nombre: " + servicio.titulo);
        viewHolder.textViewServicioDescripcion.setText("Descripción: " + servicio.descripcion);
        viewHolder.textViewServicioEmpresa.setText("Empresa: " + servicio.empresaNombre);

        return item;
    }


    static class ViewHolder{
        TextView textViewServicioTitulo;
        TextView textViewServicioDescripcion;
        TextView textViewServicioEmpresa;

    }
}
