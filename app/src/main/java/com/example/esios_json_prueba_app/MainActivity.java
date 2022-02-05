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

        miRecicler = findViewById(R.id.RV1);
        miRecicler.setHasFixedSize(true);
        miRecicler.setLayoutManager(new LinearLayoutManager(this));
        miAdapter = new Adaptador(dias());
        miRecicler.setAdapter(miAdapter);
    }

    private List<String> dias(){

        List<String> dias = new ArrayList<>();
        dias.add("Gastos guardados");
        dias.add("Ayer");
        dias.add("Hoy");
        //si estas en el rango de 20h a 23:59h
        if(LocalTime.now().getHour() >= 20 && LocalTime.now().getHour() < 24)
            dias.add("Mañana");


        return dias;
    }
}