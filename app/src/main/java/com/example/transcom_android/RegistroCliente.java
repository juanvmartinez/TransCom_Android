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

                new LoginEmpresaAsync().execute();
                Intent intent = new Intent(getApplicationContext(), FiltrosCliente.class);
                startActivity(intent);
            }

            });
        }
    private class LoginEmpresaAsync extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            EditText nombre = findViewById(R.id.editTextTextPersonName);
            String nombre1 = String.valueOf(nombre.getText());
            EditText apellidos = findViewById(R.id.editTextTextPersonName2);
            String apellidos1 = String.valueOf(apellidos.getText());
            EditText email = findViewById(R.id.editTextTextEmailAddress);
            String email1 = String.valueOf(email.getText());
            EditText contrasenya = findViewById(R.id.editTextTextPassword);
            String contrasenya1 = String.valueOf(contrasenya.getText());

            Log.e("Nombre de la persona;", nombre1);
            Log.e("Nombre de la apellidos;", apellidos1);
            Log.e("Nombre de la email;", email1);
            Log.e("Nombre de la contraseña;", contrasenya1);
            System.out.println(contrasenya);
            String url = "https://192.168.56.1:7024/CreateCliente";
            StringBuilder response = new StringBuilder();
            HashMap<String, String> map = new HashMap<>();
            JSONObject postDataParams = new JSONObject();
            try {
                postDataParams.put("nombre", nombre1);
                postDataParams.put("apellidos", apellidos1);
                postDataParams.put("email", email1);
                postDataParams.put("password", contrasenya1);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            Log.e("Nombre de la persona;", String.valueOf(postDataParams));
            getApplicationContext();
            try {
                URL urlConnection  = new URL(url);


                Log.e("Nombre de la persona;", String.valueOf(urlConnection));
                HttpURLConnection conn = (HttpURLConnection)urlConnection.openConnection();

                int responseCode = conn.getResponseCode();
                Log.e("HOW HOW HOW", String.valueOf(responseCode));
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    Log.e("ERES EL MEJOR", String.valueOf(urlConnection));
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                } else {
                    response = new StringBuilder();
                }
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, StandardCharsets.UTF_8));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                conn.disconnect();
            } catch (ProtocolException e) {
                Log.e(" ProtocolException HOW HOW HOW", String.valueOf(e));
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                Log.e(" MalformedURL  HOW HOW HOW", String.valueOf(e));
                throw new RuntimeException(e);
            } catch (IOException e) {
                Log.e(" IOException HOW HOW HOW", String.valueOf(e));
                throw new RuntimeException(e);
            } catch (Exception e) {
                Log.e(" Exception HOW HOW HOW", String.valueOf(e));
                throw new RuntimeException(e);
            }

            return null;
        }
        }
    public String getPostDataString(JSONObject params) throws Exception
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext())
        {
            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}
