package com.example.transcom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.transcom_android.adaptador.AdaptadorListaServicios;
import com.example.transcom_android.data.model.Servicios;
import com.example.transcom_android.databinding.ActivityListaServiciosBinding;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class RellenarServicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar_servicio);
        Context context;
        Button button8 = findViewById(R.id.button8);
        context = this;
         button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();
                String tipo_servicio = null;
                String tipo_pago = null;
                String desde = null;
                String hasta =  null;
                TextInputEditText db= findViewById(R.id.descripcionbreve);
                TextInputEditText d= findViewById(R.id.descripcion);
                String descripcionbreve = db.getText().toString();
                String descripcion = db.getText().toString();
                if(extras != null){
                    tipo_servicio = extras.getString("TIPO_SERVICIO");
                    tipo_pago = extras.getString("TIPO_PAGO");
                    desde = extras.getString("PRECIO_DESDE");
                    hasta = extras.getString("PRECIO_HASTA");
                }
                CrearService(tipo_servicio, tipo_pago, desde, hasta,descripcionbreve,descripcion);
            }

        });;
    }
    private void CrearService(String tipoServicio, String tipoPago,String desde, String hasta,String db, String d) {
        String url = "http://192.168.1.35:80/CreateServicio";

        JSONObject postDataParams = new JSONObject();
        try {
            if(tipoServicio != null){
                postDataParams.put("TipoServicio", Integer.valueOf(tipoServicio));
            } else {
                postDataParams.put("TipoServicio", null);
            }
            if(tipoPago != null){
                postDataParams.put("TipoPago", Integer.valueOf(tipoPago));
            } else {
                postDataParams.put("TipoPago", null);
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
            if(db != null) {
                postDataParams.put("Titulo",db);

            }else{
                postDataParams.put("Titulo", null);
            }
            if(d != null) {
                postDataParams.put("Descripcion", d);

            }else{
                postDataParams.put("Descripcion", null);
            }
            postDataParams.put("Empresa", Integer.valueOf(tipoServicio));
            postDataParams.put("Nombre", "Juanito");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        RequestQueue volleyQueue = Volley.newRequestQueue(RellenarServicio.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postDataParams,
                (Response.Listener<JSONObject>) response -> {
                    try {
                        int id = Integer.parseInt(response.getString("id"));

                        Toast.makeText(RellenarServicio.this, "Servicio creado con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ListaServicios.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error -> {
                    Toast.makeText(RellenarServicio.this, "Error al crear servicio", Toast.LENGTH_SHORT).show();
                }
        );
        volleyQueue.add(jsonObjectRequest);


    }

}