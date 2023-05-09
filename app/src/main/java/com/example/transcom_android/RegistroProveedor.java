package com.example.transcom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RegistroProveedor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_proveedor);
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CreateCompany();
            }

        });
    }

    private void CreateCompany() {
        EditText nombre = findViewById(R.id.editTextTextPersonName3);
        String nombre1 = String.valueOf(nombre.getText());
        EditText cif = findViewById(R.id.editTextTextPersonName4);
        String cif1 = String.valueOf(cif.getText());
        EditText email = findViewById(R.id.editTextTextEmailAddress2);
        String email1 = String.valueOf(email.getText());
        EditText contrasenya = findViewById(R.id.editTextTextPassword2);
        String contrasenya1 = String.valueOf(contrasenya.getText());

        String url = "http://192.168.1.35:80/CreateEmpresa";
        JSONObject postDataParams = new JSONObject();
        try {

            postDataParams.put("nombre", nombre1);
            postDataParams.put("cif", cif1);
            postDataParams.put("email", email1);
            postDataParams.put("password", contrasenya1);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RequestQueue volleyQueue = Volley.newRequestQueue(RegistroProveedor.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postDataParams,
                (Response.Listener<JSONObject>) response -> {
                    try {
                        int id = Integer.parseInt(response.getString("id"));

                        Toast.makeText(RegistroProveedor.this, "Empresa creada con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CaracteristicasServicio.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error -> {
                    Toast.makeText(RegistroProveedor.this, "Error al crear empresa", Toast.LENGTH_SHORT).show();
                }
        );
        volleyQueue.add(jsonObjectRequest);
    }


}