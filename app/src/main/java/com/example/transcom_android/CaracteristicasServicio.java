package com.example.transcom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CaracteristicasServicio extends AppCompatActivity {
    public String selectedRadious = null;
    public String selectedRadious1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caracteristicas_servicio);
        Button button7 = findViewById(R.id.button7);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupTipoPago);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroupTipoServicios);
        //Aquí seleccionamos el tipo de empresa
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton7:
                        selectedRadious = "1";
                        break;
                    case R.id.radioButton8:
                        selectedRadious = "2";
                        break;
                    case R.id.radioButton9:
                        selectedRadious = "3";
                        break;
                    default:
                        break;
                }
            }
        });
        //En este seleccionamos el tipo de servicio
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton4:
                        selectedRadious1 = "1";
                        break;
                    case R.id.radioButton5:
                        selectedRadious1 = "2";
                        break;
                    case R.id.radioButton6:
                        selectedRadious1 = "3";
                        break;
                    default:
                        break;
                }
            }
        });

        TextView d =  (TextView)findViewById(R.id.editTextNumber3);
        TextView h = (TextView) findViewById(R.id.editTextNumber4);
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RellenarServicio.class);
                intent.putExtra("TIPO_SERVICIO", selectedRadious1);
                intent.putExtra("TIPO_PAGO", selectedRadious);
                String desde =  d.getText().toString();
                String hasta = h.getText().toString();
                intent.putExtra("PRECIO_DESDE", desde);
                intent.putExtra("PRECIO_HASTA", hasta);
                startActivity(intent);
            }

        });
    }
}