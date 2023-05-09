package com.example.transcom_android;

import static android.util.Log.println;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class RegistroCliente extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CreateUser();
            }
        });
    }

    private void CreateUser() {
        EditText nombre = findViewById(R.id.editTextTextPersonName);
        String nombre1 = String.valueOf(nombre.getText());
        EditText apellidos = findViewById(R.id.editTextTextPersonName2);
        String apellidos1 = String.valueOf(apellidos.getText());
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        String email1 = String.valueOf(email.getText());
        EditText contrasenya = findViewById(R.id.editTextTextPassword);
        String contrasenya1 = String.valueOf(contrasenya.getText());

        String url = "http://192.168.1.35:80/CreateCliente";

        JSONObject postDataParams = new JSONObject();
        try {
            postDataParams.put("nombre", nombre1);
            postDataParams.put("apellidos", apellidos1);
            postDataParams.put("email", email1);
            postDataParams.put("password", contrasenya1);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RequestQueue volleyQueue = Volley.newRequestQueue(RegistroCliente.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postDataParams,
                (Response.Listener<JSONObject>) response -> {
                    try {
                        int id = Integer.parseInt(response.getString("id"));

                        Toast.makeText(RegistroCliente.this, "Cliente creado con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), FiltrosCliente.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error -> {
                    Toast.makeText(RegistroCliente.this, "Error al crear cliente", Toast.LENGTH_SHORT).show();
                }
        );
        volleyQueue.add(jsonObjectRequest);
    }

}

