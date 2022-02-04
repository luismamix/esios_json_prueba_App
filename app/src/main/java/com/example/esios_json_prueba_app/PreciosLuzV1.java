package com.example.esios_json_prueba_app;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreciosLuzV1 {
    int SDK_INT = android.os.Build.VERSION.SDK_INT;
    //parametros entrada
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private LocalDate dia;
    private String indicator;
    private String groupby;
    private String geoids;
    //cocinar / pintar
    private URL peticion;
    private List<Double> precios;
    private double precioACTUAL;
    private double precioMIN;
    private int horaprecioMIN;
    private int horaprecioMAX;
    private double precioMAX;
    private double precioMEDIA;
    private String geo_name;

    public PreciosLuzV1(LocalDateTime start_date, LocalDateTime end_date, LocalDate dia, String indicator, String groupby, String geoids) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.dia = dia;
        this.indicator = indicator;
        this.groupby = groupby;
        this.geoids = geoids;
        this.precios = new ArrayList<>();

    }

    public void conexionESIOSREE(){
        //forzar la peticion URL por el threat principal.
        if (this.SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
     try {

        //Construir la URL
        this.peticion = new URL("https://www.esios.ree.es/indicators/" + indicator +"?" +
                "start_date=" + String.valueOf(start_date) +
                "&end_date=" + String.valueOf(end_date) +
                "&groupby=" + groupby +
                "&geoids=" + geoids);

        //Conectar con el Web API de ESIOS
         HttpURLConnection conexion = (HttpURLConnection) peticion.openConnection();
         conexion.setRequestMethod("GET");
         conexion.setRequestProperty("Accept", "application/json; application/vnd.esios-api-v2+json");
         conexion.setRequestProperty("Content-Type", "application/json");
         conexion.setRequestProperty("Host", "api.esios.ree.es");
         conexion.setRequestProperty("Authorization", "Token token=8bb6e470df6ac0289c373b08158fcfc1be4bcaea2356bd1091a1e17ffb9d435a");

         //Utilizar varios obj de la familia StreamReader para meter el "response".
         InputStream in = conexion.getInputStream();

         BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
         StringBuilder responseStrBuilder = new StringBuilder();

         //meter en una cadena el contenido del stream.
         String inputStr;
         while ((inputStr = streamReader.readLine()) != null)
             responseStrBuilder.append(inputStr);
         streamReader.close();

         //parsear el texto del "response" en un JSONObject
         JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
         JSONObject mogollon = jsonObject.getJSONObject("indicator");
         //recorrer el JSON para sacar el array de precios.
         JSONArray valores =  mogollon.getJSONArray("values");

         for (int i=0; i < valores.length(); i++) {

             JSONObject elemento =    valores.getJSONObject(i);
             if((Integer)elemento.get("geo_id") == Integer.parseInt(geoids)){
                 precios.add(elemento.getDouble("value")/1000);
                 //establecer el geo_name
                 JSONObject elem =    valores.getJSONObject(i);
                 this.geo_name = String.valueOf(elem.get("geo_name"));
             }
         }

         //establecer precioMEDIA
         double media=0.00;
         for (Double valor:precios) {
             media += valor;
         }
         media /= 24;
         this.precioMEDIA = media;
         //establecer precioMIN y horaprecioMIN
         int minIndex = precios.indexOf(Collections.min(precios));
         this.horaprecioMIN = minIndex;
         this.precioMIN=  precios.get(minIndex);
         //establecer precioMAX Y horaprecioMAX
         int maxIndex = precios.indexOf(Collections.max(precios));
         this.horaprecioMAX = maxIndex;
         this.precioMAX = precios.get(maxIndex);
         //establecer precioACTUAL
         int actIndex=LocalTime.now().getHour();
         this.precioACTUAL = precios.get(actIndex);



     } catch (Exception e){
         System.out.println("@@@@@@@@@@@");
         e.printStackTrace();
         System.out.println("Petacion en la conexion");
     }

    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public LocalDate getDia() {
        return dia;
    }

    public String getIndicator() {
        return indicator;
    }

    public String getGroupby() {
        return groupby;
    }

    public String getGeoids() {
        return geoids;
    }

    public List<Double> getPrecios() {
        return precios;
    }

    public double getPrecioACTUAL() {
        return precioACTUAL;
    }

    public double getPrecioMIN() {
        return precioMIN;
    }

    public int getHoraprecioMIN() {
        return horaprecioMIN;
    }

    public int getHoraprecioMAX() {
        return horaprecioMAX;
    }

    public double getPrecioMAX() {
        return precioMAX;
    }

    public double getPrecioMEDIA() {
        return precioMEDIA;
    }

    public String getGeo_name() {
        return geo_name;
    }
}
