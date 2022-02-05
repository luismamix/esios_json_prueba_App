package com.example.esios_json_prueba_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Ventana_Mostrar_Gastos_guardados extends AppCompatActivity {
    private ImageButton v2_bsalir;
    private SQLiteDatabase db;
    private TableLayout tabla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_mostrar_gastos_guardados);
        v2_bsalir = findViewById(R.id.v2_bsalir);
        tabla = findViewById(R.id.tabla_gastos_guardados);
        v2_bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                //finish();
            }
        });

        int filas =0;
        //titulos de tabla.
        String[] cabeceras = new String[]{ "Id", "Dia", "Tarifa", "Nº Elec", "Kwh", "€", "Borrar"};

        BaseDatosHelper bdhelper  = new BaseDatosHelper(getApplicationContext(),"MI_GASTO_ELECTRICO",null,1);
        db = bdhelper.getReadableDatabase();
        Cursor c =  db.rawQuery("SELECT * FROM GASTO_ELECTRICO",null);
        //mapeo a una matriz
        String[][] listado = new String [c.getCount()][cabeceras.length];
        if(c.moveToFirst()){
            do {
                listado[filas][0]= c.getString(0);
                listado[filas][1]=  c.getString(1);
                listado[filas][2]=  c.getString(2);
                listado[filas][3]=  c.getString(3);
                listado[filas][4]=  c.getString(4);
                listado[filas][5]=  c.getString(5);
                filas++;
            }while(c.moveToNext());
        }else{
            System.out.println("No hay resultados!!!");
        }

        // ## Rellenar el TableLayout (construir filas y celdas) con los Datos.
        //    Darle LayoutParams, Margins ##
        TableLayout.LayoutParams tablaparams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        tablaparams.setMargins(15,0,15,0);
        tabla.setLayoutParams(tablaparams);
        tabla.setColumnStretchable(1, true);

        // fila Cabecera de la tabla
        TableRow cabecera = new TableRow(getApplicationContext());
        cabecera.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        //darle round corners a la cabecera, para ello hay q darle un drawable.xml con propiedades round corner.
        cabecera.setBackgroundResource(R.drawable.shape_1er_fila);
        tabla.addView(cabecera);

        // dar formato a los Textos de la cabecera
        for (int i = 0; i < cabeceras.length; i++)
        {
            TextView celda = new TextView(getApplicationContext());
            celda.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            celda.setText(cabeceras[i]);
            celda.setTextColor(Color.parseColor("#C7F68E"));
            celda.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            celda.setTypeface(Typeface.DEFAULT_BOLD);
            celda.setGravity(Gravity.CENTER);
            celda.setPadding(8, 8, 8, 8);
            cabecera.addView(celda);
        }

        // Filas de datos
        for(int i=0; i < filas; i++){ //filas
            TableRow fila = new TableRow(getApplicationContext());
            //es ultima fila? 1 : 2;
            if(i == filas -1){
                // 1) darle round corners y color fondo gris a la ult fila .
                fila.setBackgroundResource(R.drawable.shape_ult_fila);
            } else if(i % 2 != 0){
                //2) dar color fondo gris a las filas impares
                fila.setBackgroundColor(Color.parseColor("#F2F2F2"));
            }

            for(int j=0; j < cabeceras.length; j++){ //celdas = columnas
                TextView celda = new TextView(getApplicationContext());
                celda.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                celda.setTextColor(Color.parseColor("#303030"));
                celda.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                celda.setGravity(Gravity.CENTER);
                celda.setPadding(8, 8, 8, 8);

                if(j == 0){  //si es la primera columna metemos un texview(celda) a la fila.
                    celda.setText(listado[i][j]);
                    celda.setTypeface(Typeface.DEFAULT_BOLD);
                    fila.addView(celda);
                } else if(j == cabeceras.length-1){ //si es la ult columna metemos un boton a la fila , al hacer click borra el gasto y borra la fila del la tabla.
                   Button b = new Button(getApplicationContext());
                    b.setBackgroundResource(R.drawable.b_eliminar);
                    b.setLayoutParams(new TableRow.LayoutParams(125, 125));
                    fila.addView(b);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BaseDatosHelper bdhelper  = new BaseDatosHelper(getApplicationContext(),"MI_GASTO_ELECTRICO",null,1);
                            db = bdhelper.getWritableDatabase();

                            //obtener la fila donde se encuentra el boton, luego obtenemos el texto del textview de la primera columna. (el gasto_id)
                            if (v.getParent() instanceof TableRow){
                                TableRow fila = (TableRow) v.getParent();
                              TextView celda= (TextView)  fila.getChildAt(0);//primer textview d la fila
                              String id = celda.getText().toString();
                              //1.borrar el gasto por id de la BD.
                              //2.borrar la fila del layout.
                                if(db.delete("GASTO_ELECTRICO", "gasto_id=?", new String[]{id})>0){
                                    tabla.removeView(fila);
                                }
                            }
                        }
                    });

                } else{ // resto de columnas
                    celda.setText(listado[i][j]);
                    fila.addView(celda);
                }

                //fila.addView(celda);
            }
            tabla.addView(fila);
        }

    }
}