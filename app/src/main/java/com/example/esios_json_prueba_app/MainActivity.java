package com.example.esios_json_prueba_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RecyclerView miRecicler;
    private RecyclerView.Adapter miAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /*        txtprecios = findViewById(R.id.txt_precios);

        LocalDate fechaHOY = LocalDate.now();
        LocalDate fechaAYER = LocalDate.now().minusDays(1);
        LocalDate fechaMAÑANANA =  LocalDate.now().plusDays(1);
        LocalTime horaINICIO = LocalTime.of(0,0,0);
        LocalTime horaFIN =  LocalTime.of(23,0,0);
        LocalDateTime start_date = LocalDateTime.of(fechaMAÑANANA, horaINICIO);
        LocalDateTime end_date = LocalDateTime.of(fechaMAÑANANA, horaFIN);

        PreciosLuzV1 canariasHOY = new PreciosLuzV1(start_date,end_date,fechaMAÑANANA,"1001","hour","8741");
        canariasHOY.conexionESIOSREE();

        DecimalFormat formateador = new DecimalFormat("####.###");
        List<Double> precios = canariasHOY.getPrecios();
        String listado ="";
        int i= 0;
        for (double valor: precios) {
            listado += i +"h : " + formateador.format(valor) + "€ Kwh \n";
            i++;
        }
        String fechas = "start_date: " + canariasHOY.getStart_date().toString() + "\n" +
                        "end_date: " + canariasHOY.getEnd_date().toString() + "\n";
        txtprecios.setText("LISTADO PRECIOS : " + canariasHOY.getGeo_name() +" "+ canariasHOY.getDia().format(DateTimeFormatter.ofPattern("d/M/yyyy")) + "\n" +
                            "Precio Actual: " + formateador.format(canariasHOY.getPrecioACTUAL()) + "€ Kwh" + "\n" +
                            "Precio Mínimo : " + canariasHOY.getHoraprecioMIN() + "h : " + formateador.format(canariasHOY.getPrecioMIN()) + "€ Kwh" + "\n" +
                            "Precio Máximo : " + canariasHOY.getHoraprecioMAX() + "h : " + formateador.format(canariasHOY.getPrecioMAX())  + "€ Kwh" + "\n" +
                            "Precio Medio: " + formateador.format(canariasHOY.getPrecioMEDIA()) + "€ Kwh" + "\n" +
                            listado);*/

        miRecicler = findViewById(R.id.RV1);
        miRecicler.setHasFixedSize(true);
        miRecicler.setLayoutManager(new LinearLayoutManager(this));
        miAdapter = new Adaptador(dias());
        miRecicler.setAdapter(miAdapter);
    }

    private List<String> dias(){

        List<String> dias = new ArrayList<>();
        dias.add("Ayer");
        dias.add("Hoy");
        //si estas en el rango de 20h a 23:59h
        if(LocalTime.now().getHour() >= 20 && LocalTime.now().getHour() < 24)
            dias.add("Mañana");
        dias.add("Gastos guardados");

        return dias;
    }
}