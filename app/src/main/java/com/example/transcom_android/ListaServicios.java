package com.example.transcom_android;

import static java.lang.String.format;
import java.text.DecimalFormat;

import android.animation.FloatArrayEvaluator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.transcom_android.adaptador.AdaptadorListaServicios;
import com.example.transcom_android.data.model.Servicios;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.transcom_android.databinding.ActivityListaServiciosBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class ListaServicios extends AppCompatActivity {

    private ActivityListaServiciosBinding binding;
    private AdaptadorListaServicios adaptador;
    public ListView listView;
    public Servicios servicios;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_servicios);
        context = this;
        listView = findViewById(R.id.listview_servicios);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String tipo_servicio = null;
        String desde = null;
        String hasta =  null;
        if(extras != null){
            tipo_servicio = extras.getString("TIPO_SERVICIO");
            desde = extras.getString("PRECIO_DESDE");
            hasta = extras.getString("PRECIO_HASTA");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                try {

                                                    Intent intent = new Intent(context, DetallesServicio.class);
                                                    intent.putExtra("ID_SERVICIO", servicios.getServicios().get(position).id);
                                                    startActivity(intent);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
        );

        CargarServiciosFiltrados(tipo_servicio,desde,hasta);

    }

    private void CargarServiciosFiltrados(String tipoServicio, String desde, String hasta){
        String url = "http://192.168.1.35:80/GetServiciosFiltrados";

        JSONObject postDataParams = new JSONObject();
        try {
            if(tipoServicio != null){
                postDataParams.put("TipoServicio", Integer.valueOf(tipoServicio));
            } else {
                postDataParams.put("TipoServicio", null);
            }
            if(desde !=null){
                postDataParams.put("DesdePrecio", Float.valueOf(desde));

            }else{
                postDataParams.put("DesdePrecio", null);
            }
            if(hasta != null) {
                postDataParams.put("HastaPrecio", Float.valueOf(hasta));

            }else{
                postDataParams.put("HastaPrecio", null);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RequestQueue volleyQueue = Volley.newRequestQueue(ListaServicios.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postDataParams,
                (Response.Listener<JSONObject>) response -> {
                    try {
                        Gson gson = new GsonBuilder().create();
                        servicios = gson.fromJson(String.valueOf(response), Servicios.class);

                        adaptador = new AdaptadorListaServicios(context, R.layout.fila_servicio, servicios.servicios);
                        listView.setAdapter(adaptador);
                        listView.setScrollingCacheEnabled(true);
                        adaptador.notifyDataSetChanged();

                        Toast.makeText(ListaServicios.this, "Se han obtenido ", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error -> {
                    Toast.makeText(ListaServicios.this, "Error al obtener servicios", Toast.LENGTH_SHORT).show();
                }
        );
        volleyQueue.add(jsonObjectRequest);
    }
}