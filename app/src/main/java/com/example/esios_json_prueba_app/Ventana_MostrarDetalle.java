package com.example.esios_json_prueba_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ventana_MostrarDetalle extends AppCompatActivity {
    private TextView txt_precio_actual;
    private TextView txt_precio_min;
    private TextView txt_precio_max;
    private TextView txt_precio_medio;
    private TextView txt_grafico_barras;
    private TextView txt_listado_precios;
    private TextView txt_resumen_precios;
    private TextView txt_calcular_consumo_hogar;
    private TableLayout tabla;
    private BarChart barChart1;
    private PreciosLuzV1 datos;
    private CardView bconsumohogar;
    private ImageButton v3_bsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_mostrar_detalle);

        v3_bsalir = findViewById(R.id.v3_bsalir);
        v3_bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_precio_actual = findViewById(R.id.txt_precio_actual);
        txt_precio_min = findViewById(R.id.txt_precio_min);
        txt_precio_max = findViewById(R.id.txt_precio_max);
        txt_precio_medio = findViewById(R.id.txt_precio_medio);
        txt_grafico_barras = findViewById((R.id.txt_grafico_barras));
        txt_listado_precios = findViewById(R.id.txt_listado_precios);
        txt_resumen_precios = findViewById(R.id.txt_resumen_precios);
        txt_calcular_consumo_hogar = findViewById(R.id.txt_calcular_consumo_hogar);
        bconsumohogar = findViewById(R.id.bconsumohogar);

        txt_grafico_barras.setText(Html.fromHtml("<u>Evolución gráfica de los precios:</u>"));
        txt_listado_precios.setText(Html.fromHtml("<u>Precios de la electricidad cada hora:</u>"));
        tabla = findViewById(R.id.tabla);
        //fuente: https://medium.com/@clyeung0714/using-mpandroidchart-for-android-application-barchart-540a55b4b9ef
        barChart1 = findViewById(R.id.barChart1);
        LocalDate diaD = null;
        String zona = null;
        
        //## recoger 1er y 2nd parametro ##
        Bundle bundle =  getIntent().getExtras();
        Parametros p = (Parametros) bundle.getSerializable("ronda2");
        switch (p.getDia()){
            case ("Ayer"):
                diaD = LocalDate.now().minusDays(1);
                break;
            case("Hoy"):
                diaD = LocalDate.now();
                break;
            case("Mañana"):
                diaD = LocalDate.now().plusDays(1);
                break;
        }
        switch (p.getZona()){
            case("Península"):
                zona = "8741";
                break;
            case("Canarias"):
                zona = "8742";
                break;
            case("Baleares"):
                zona = "8743";
                break;
            case("Ceuta"):
                zona = "8744";
                break;
            case("Melilla"):
                zona = "8745";
                break;
        }
      
       /* LocalDate fechaHOY = LocalDate.now();
        LocalDate fechaAYER = LocalDate.now().minusDays(1);
        LocalDate fechaMAÑANANA =  LocalDate.now().plusDays(1);*/
        
        //## construir la fecha inicio y fecha fin. ##
        LocalTime horaINICIO = LocalTime.of(0,0,0);
        LocalTime horaFIN =  LocalTime.of(23,0,0);
        LocalDateTime start_date = LocalDateTime.of(diaD, horaINICIO);
        LocalDateTime end_date = LocalDateTime.of(diaD, horaFIN);

        //inicializar los parametros de entrada al Web API
        datos = new PreciosLuzV1(start_date,end_date,diaD,"1001","hour",zona);
        //conectar con el Web API y recuperar datos.
        datos.conexionESIOSREE();
        // formato de salida de 3 decimales.
        DecimalFormat formateador = new DecimalFormat("####.###");
        List<Double> precios = datos.getPrecios();

        // ## Si queremos sacar los datos en texto por consola ##
        /*  String listado ="";
            int k= 0;
            for (double valor: precios) {
                listado += k +"h : " + formateador.format(valor) + "€ Kwh \n";
                k++;
            }

          String fechas = "start_date: " + datos.getStart_date().toString() + "\n" +
                        "end_date: " + datos.getEnd_date().toString() + "\n";
          System.out.println("LISTADO PRECIOS : " + datos.getGeo_name() +" "+ datos.getDia().format(DateTimeFormatter.ofPattern("d/M/yyyy")) + "\n" +
                            "Precio Actual: " + formateador.format(datos.getPrecioACTUAL()) + "€ Kwh" + "\n" +
                            "Precio Mínimo : " + datos.getHoraprecioMIN() + "h : " + formateador.format(datos.getPrecioMIN()) + "€ Kwh" + "\n" +
                            "Precio Máximo : " + datos.getHoraprecioMAX() + "h : " + formateador.format(datos.getPrecioMAX())  + "€ Kwh" + "\n" +
                            "Precio Medio: " + formateador.format(datos.getPrecioMEDIA()) + "€ Kwh" + "\n" +
                            listado);*/

        //portfolio de precios
        txt_resumen_precios.setText(Html.fromHtml("<u>Listado Precios " + datos.getGeo_name() +" "+ datos.getDia().format(DateTimeFormatter.ofPattern("d/M/yyyy")) +":</u>"));
        txt_precio_actual.setText(Html.fromHtml("<strong>Precio Actual : </strong> " + formateador.format(datos.getPrecioACTUAL()) + "€ Kwh"));
        txt_precio_min.setText(Html.fromHtml("<strong>Precio MIN : </strong> " + String.valueOf(datos.getHoraprecioMIN()) + "h " +  formateador.format(datos.getPrecioMIN()) + "€ Kwh"));
        txt_precio_max.setText(Html.fromHtml("<strong>Precio MAX : </strong> " + String.valueOf(datos.getHoraprecioMAX()) + "h " +  formateador.format(datos.getPrecioMAX()) + "€ Kwh"));
        txt_precio_medio.setText( Html.fromHtml("<strong>Precio Medio : </strong> " +formateador.format(datos.getPrecioMEDIA()) + "€ Kwh"));


        //titulos de tabla.
        String cabeceras[] = { "Hora", "Precio"};

        // ## Rellenar el TableLayout (construir filas y celdas) con los Datos.
        //    Darle LayoutParams, Margins ##
        TableLayout.LayoutParams tablaparams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        tablaparams.setMargins(25,0,25,0);
        tabla.setLayoutParams(tablaparams);
        tabla.setColumnStretchable(1, true);

        // fila Cabecera de la tabla
        TableRow cabecera = new TableRow(this);
        cabecera.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        //darle round corners a la cabecera, para ello hay q darle un drawable.xml con propiedades round corner.
        cabecera.setBackgroundResource(R.drawable.shape_1er_fila);
        tabla.addView(cabecera);

        // dar formato a los Textos de la cabecera
        for (int i = 0; i <= 1; i++)
        {
            TextView celda = new TextView(this);
            celda.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            celda.setText(cabeceras[i]);
            celda.setTextColor(Color.parseColor("#C7F68E"));
            celda.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            celda.setTypeface(Typeface.DEFAULT_BOLD);
            celda.setGravity(Gravity.CENTER);
            celda.setPadding(50, 5, 5, 5);
            cabecera.addView(celda);
        }

        // Filas de datos
        for(int i=0; i <= precios.size() -1; i++){ //filas
            TableRow fila = new TableRow(this);
            //es ultima fila? 1 : 2;
            if(i == precios.size() -1){
                // 1) darle round corners y color fondo gris a la ult fila .
                fila.setBackgroundResource(R.drawable.shape_ult_fila);
            } else if(i % 2 != 0){
                //2) dar color fondo gris a las filas impares
                fila.setBackgroundColor(Color.parseColor("#F2F2F2"));
            }

            for(int j=0; j <= 1; j++){ //celdas = columnas
                TextView celda = new TextView(this);
                celda.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                celda.setTextColor(Color.parseColor("#303030"));
                celda.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                celda.setGravity(Gravity.CENTER);
                celda.setPadding(25, 5, 5, 5);
                //si es la primera columna.
                if(j == 0)
                    celda.setText(String.valueOf(i) + " h");
                else
                    celda.setText(formateador.format(precios.get(i)) + " € Kwh");
                fila.addView(celda);
            }
            tabla.addView(fila);
        }

        // ## Rellenar el control BarChart.##
        initBarChart();
        showBarChart();

        //pasar el precio medio € kwh a la ventana de consumo hogar
        bconsumohogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               txt_calcular_consumo_hogar.setTextColor(Color.parseColor("#C7F68E"));
                p.setPreciomedio(datos.getPrecioMEDIA());
                Intent i = new Intent(v.getContext(),Ventana_Calcular_Gasto_Electrico.class);
                i.putExtra("ronda3",p);
                startActivity(i);


            }
        });
    }

    private void initBarChart() {
        barChart1 = findViewById(R.id.barChart1);
        // oculta el fondo gris del gráfico, por defecto es falso si no está establecido
        barChart1.setDrawGridBackground(false);
        // elimina la sombra de la barra, por defecto es falso si no se establece
        barChart1.setDrawBarShadow(false);
        // eliminar el borde del gráfico, por defecto es falso si no se establece
        barChart1.setDrawBorders(false);

        // eliminar el texto de la etiqueta de descripción ubicado en la esquina inferior derecha
        Description descripcion = new Description();
        descripcion.setEnabled(false);
        barChart1.setDescription(descripcion);

        // configurando la animación para el eje y, la barra saldrá de 0 a su valor dentro del tiempo que establezcamos
        barChart1.animateY(1000);
        // configurando la animación para el eje x, la barra aparecerá por separado dentro del tiempo que establezcamos
        barChart1.animateX(1000);


        XAxis xAxis = barChart1.getXAxis();
        //aplicar el formato "# h" fuen te:https://weeklycoding.com/mpandroidchart-documentation/formatting-data-values/
        xAxis.setValueFormatter(new MyXAxisValueFormatter());
        // cambia la posición del eje x a la parte inferior
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM );
        // establece la distancia horizontal de la línea de la cuadrícula
        xAxis.setGranularity(1f);
        // ocultando la línea del eje x, por defecto es verdadero si no se establece
        xAxis.setDrawAxisLine(false);
        // ocultando las líneas verticales de la cuadrícula, por defecto es verdadero si no se establece
        xAxis.setDrawGridLines(false);
        // cambiar tamaño letra eje x
        xAxis.setTextSize(16f);
        //xAxis.setTypeface(Typeface.DEFAULT_BOLD);

        YAxis leftAxis = barChart1.getAxisLeft();
        //aplicar el formato "####.## € Kwh" fuente: https://weeklycoding.com/mpandroidchart-documentation/formatting-data-values/
        leftAxis.setValueFormatter(new MyYAxisValueFormatter());
        // ocultando la línea izquierda del eje y, por defecto es verdadero si no se establece
        leftAxis.setDrawAxisLine(false);
        leftAxis.setTextSize(16f);
        //leftAxis.setTypeface(Typeface.DEFAULT_BOLD);

        YAxis rightAxis = barChart1.getAxisRight();
        //ocultar el eje y derecho para ganar visibilidad.
        rightAxis.setEnabled(false);
        // ocultando la línea derecha del eje y, por defecto es verdadero si no se establece
        //rightAxis.setDrawAxisLine(false);
        //rightAxis.setTextSize(16f);


        Legend legend = barChart1.getLegend();
        // establecer la forma de la leyenda en circulo, forma cuadrada predeterminada
        legend.setForm(Legend.LegendForm.CIRCLE );
        // establecer el tamaño y grosor del texto de la leyenda
        legend.setTextSize(18f);
        legend.setTypeface(Typeface.DEFAULT_BOLD);
        // establecer la alineación de la leyenda hacia el gráfico
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM );
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT );
        // establecer la dirección de apilamiento de la leyenda
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL );
        // establecer la ubicación de la leyenda fuera del gráfico, por defecto es falso si no se establece
        legend.setDrawInside(false);

        //Customizar las leyendas del grafico.
        String[] titulos = {"MAX", "MIN", "Precios Luz"} ;
        int[] colors2 = {Color.RED,Color.GREEN,Color.parseColor("#303030")};
        LegendEntry[] entradaleyendas = new LegendEntry[3];
        for(int i=0; i< entradaleyendas.length; i++){
            LegendEntry entrada = new LegendEntry();
            entrada.formColor = colors2[i];
            entrada.label = titulos[i];
            entradaleyendas[i] = entrada;
        }
        legend.setCustom(entradaleyendas);
        legend.setXEntrySpace(20f);
    }


    private void showBarChart(){
        barChart1 = findViewById(R.id.barChart1);
        //ArrayList<Double> valueList = new ArrayList<Double>();
        List<Double> precios = datos.getPrecios();
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "Precios Luz";
        String[] titulos = {"Precio MAX", " Precio MIN", "Precios Luz"} ;
        List<Integer> colors = new ArrayList<>();
       /* List<Integer> colors2 = new ArrayList<>();
        colors2.add(Color.RED);
        colors2.add(Color.GREEN);
        colors2.add(Color.parseColor("#303030"));*/

        //fit the data into a bar
        for (int i = 0; i < precios.size(); i++) {
            //rellenar una lista con los colores q vamos a utilizar.
            if(i == datos.getHoraprecioMIN())
                //precio min
                colors.add(Color.GREEN);
            else if (i == datos.getHoraprecioMAX()){
                //precio max
                colors.add(Color.RED);
            } else {
                //resto
                colors.add(Color.parseColor("#303030"));
            }
            BarEntry barEntry = new BarEntry(i, precios.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);
        initBarDataSet(barDataSet, colors);
        BarData data = new BarData(barDataSet);
        barChart1.setData(data);
        barChart1.invalidate();

    }

    private void initBarDataSet(BarDataSet barDataSet,  List<Integer> colors){
        //pasarle la lista de colores al conjunto de barras.
        barDataSet.setColors(colors);
        //Setting the size of the form in the legend
        barDataSet.setFormSize(15f);
        //showing the value of the bar, default true if not set
        barDataSet.setDrawValues(false);
        //setting the text size of the value of the bar
        barDataSet.setValueTextSize(12f);
    }

    private class MyXAxisValueFormatter extends ValueFormatter {

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return (int)value + "h";
        }
    }


    private class MyYAxisValueFormatter extends ValueFormatter {
        private DecimalFormat formateador = new DecimalFormat("####.##");
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return formateador.format(value) + "€ Kwh";
        }
    }
}