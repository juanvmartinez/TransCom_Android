package com.example.transcom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FiltrosCliente extends AppCompatActivity {
    public String selectedRadious = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros_cliente);
        Button button5 = findViewById(R.id.button5);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupTipoServicios);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton:
                        selectedRadious = "1";
                        break;
                    case R.id.radioButton2:
                        selectedRadious = "2";
                        break;
                    case R.id.radioButton3:
                        selectedRadious = "3";
                        break;
                    default:
                        break;
                }
            }
        });
        TextView d =  (TextView)findViewById(R.id.editTextNumber);
        TextView h = (TextView) findViewById(R.id.editTextNumber2);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaServicios.class);
                intent.putExtra("TIPO_SERVICIO", selectedRadious);
                String desde =  d.getText().toString();
                String hasta = h.getText().toString();
                intent.putExtra("PRECIO_DESDE", desde);
                intent.putExtra("PRECIO_HASTA", hasta);
                startActivity(intent);
            }

        });
    }
}