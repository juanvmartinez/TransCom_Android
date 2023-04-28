package com.example.transcom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DetallesServicio extends AppCompatActivity {
    private Button BtAñadir;
    String a[] ={"Empresa_1", "Empresa_2","Empresa_3"};
    private List<String> lista = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView ListView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String juan =  "Juan";
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalles_servicio);

       // ListView = findViewById(R.id.dynamic1);
       lista.add(juan);
       //adapter = new ArrayAdapter<>(this,android.R.layout.id.dynamic1,lista);

    }
}