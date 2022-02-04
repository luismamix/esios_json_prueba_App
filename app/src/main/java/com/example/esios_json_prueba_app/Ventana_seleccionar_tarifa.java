package com.example.esios_json_prueba_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Ventana_seleccionar_tarifa extends AppCompatActivity {
    private TextView v2_titulo;
    private TextView textcanarias;
    private TextView textbaleares;
    private TextView textpeninsula;
    private TextView textceuta;
    private TextView textmelilla;
    private ImageButton v2_bsalir;

    private ImageButton v2_bpeninsula;
    private ImageButton v2_bcanarias;
    private ImageButton v2_bbaleares;
    private ImageButton v2_bceuta;
    private ImageButton v2_bmelilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_seleccionar_tarifa);

        v2_titulo = findViewById(R.id.v2_titulo);
        v2_bpeninsula = findViewById(R.id.v2_bpeninsula);
        v2_bcanarias = findViewById(R.id.v2_bcanarias);
        v2_bbaleares = findViewById(R.id.v2_bbaleares);
        v2_bceuta = findViewById(R.id.v2_bceuta);
        v2_bmelilla = findViewById(R.id.v2_bmelilla);
        v2_bsalir = findViewById(R.id.v2_bsalir);
        textcanarias = findViewById(R.id.textcanarias);
        textbaleares = findViewById(R.id.textbaleares);
        textpeninsula = findViewById(R.id.textpeninsula);
        textceuta = findViewById(R.id.textceuta);
        textmelilla = findViewById(R.id.textmelilla);

        //recoger 1er parametro
        Bundle bundle =  getIntent().getExtras();
        Parametros p = (Parametros) bundle.getSerializable("ronda1");
        v2_titulo.setText("Tarifas " + p.getDia());

        v2_bpeninsula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setear el 2nd parametro
                //p.setZona(textpeninsula.getText().toString());
                p.setZona("Península");
                Intent i = new Intent(v.getContext(),Ventana_MostrarDetalle.class);
                i.putExtra("ronda2", p);
                v.getContext().startActivity(i);
                //cambiar el color del boton , texto a verde y el resto a gris
                cambiarColorImagen(R.id.v2_bpeninsula,R.drawable.ic_menu_light);
                cambiarColorTexto(R.id.textpeninsula, "#C7F68E");
                cambiarColorImagen(R.id.v2_bcanarias,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textcanarias, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bbaleares,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textbaleares, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bceuta,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textceuta, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bmelilla,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textmelilla, "#FFEBEFE6");
            }
        });

        v2_bcanarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setear el 2nd parametro
                p.setZona(textcanarias.getText().toString());
                Intent i = new Intent(v.getContext(),Ventana_MostrarDetalle.class);
                i.putExtra("ronda2", p);
                v.getContext().startActivity(i);
                //cambiar el color del boton , texto a verde y el resto a gris
                cambiarColorImagen(R.id.v2_bpeninsula,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textpeninsula, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bcanarias,R.drawable.ic_menu_light);
                cambiarColorTexto(R.id.textcanarias, "#C7F68E");
                cambiarColorImagen(R.id.v2_bbaleares,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textbaleares, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bceuta,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textceuta, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bmelilla,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textmelilla, "#FFEBEFE6");
            }
        });

        v2_bbaleares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setear el 2nd parametro
                p.setZona(textbaleares.getText().toString());
                Intent i = new Intent(v.getContext(),Ventana_MostrarDetalle.class);
                i.putExtra("ronda2", p);
                v.getContext().startActivity(i);
                //cambiar el color del boton , texto a verde y el resto a gris
                cambiarColorImagen(R.id.v2_bpeninsula,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textpeninsula, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bcanarias,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textcanarias, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bbaleares,R.drawable.ic_menu_light);
                cambiarColorTexto(R.id.textbaleares, "#C7F68E");
                cambiarColorImagen(R.id.v2_bceuta,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textceuta, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bmelilla,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textmelilla, "#FFEBEFE6");
            }
        });

        v2_bceuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setear el 2nd parametro
                p.setZona(textceuta.getText().toString());
                Intent i = new Intent(v.getContext(),Ventana_MostrarDetalle.class);
                i.putExtra("ronda2", p);
                v.getContext().startActivity(i);
                //cambiar el color del boton , texto a verde y el resto a gris
                cambiarColorImagen(R.id.v2_bpeninsula,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textpeninsula, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bcanarias,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textcanarias, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bbaleares,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textbaleares, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bceuta,R.drawable.ic_menu_light);
                cambiarColorTexto(R.id.textceuta, "#C7F68E");
                cambiarColorImagen(R.id.v2_bmelilla,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textmelilla, "#FFEBEFE6");
            }
        });

        v2_bmelilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setear el 2nd parametro
                p.setZona(textmelilla.getText().toString());
                Intent i = new Intent(v.getContext(),Ventana_MostrarDetalle.class);
                i.putExtra("ronda2", p);
                v.getContext().startActivity(i);
                //cambiar el color del boton , texto a verde y el resto a gris
                cambiarColorImagen(R.id.v2_bpeninsula,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textpeninsula, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bcanarias,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textcanarias, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bbaleares,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textbaleares, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bceuta,R.drawable.ic_grey_menu_light);
                cambiarColorTexto(R.id.textceuta, "#FFEBEFE6");
                cambiarColorImagen(R.id.v2_bmelilla,R.drawable.ic_menu_light);
                cambiarColorTexto(R.id.textmelilla, "#C7F68E");
            }
        });

        v2_bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void cambiarColorImagen( int id,int imagen){
        ImageButton boton = findViewById(id);
        boton.setBackgroundResource(imagen);
    }
    private void cambiarColorTexto( int id,String color){
        TextView textview = findViewById(id);
       textview.setTextColor(Color.parseColor(color));
    }
}