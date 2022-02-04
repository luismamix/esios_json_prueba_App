package com.example.esios_json_prueba_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ventana_Calcular_Gasto_Electrico extends AppCompatActivity {
    //controles de la Activity

    private List<Electrodomestico> electrodomesticos;
    private Parametros p;
    private CardView bcalcularconsumohogar;
    private CardView brecargarconsumohogar;
    private CardView bguardarconsumohogar;
    private TextView txt_electrodomesticos_encendidos;
    private TextView txt_consumo_diario;
    private TextView txt_gasto_diario;
    private TextView txt_precio_medio;
    private TextView txt_dia;
    private TextView txt_tarifa;
    private ImageButton v4_bsalir;
    private double consumo_diario;
    private double gasto_diario;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_calcular_gasto_electrico);

        txt_electrodomesticos_encendidos = findViewById(R.id.txt_electrodomesticos_encendidos);
        txt_consumo_diario = findViewById(R.id.txt_consumo_diario);
        txt_gasto_diario = findViewById(R.id.txt_gasto_diario);
        txt_precio_medio = findViewById(R.id.txt_precio_medio);
        txt_dia = findViewById(R.id.txt_dia);
        txt_tarifa = findViewById(R.id.txt_tarifa);
        v4_bsalir = findViewById(R.id.v4_bsalir);
        v4_bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //electrodomesticos encendidos
        electrodomesticos= new ArrayList<>();


        //recoger 3er parametro
        Bundle bundle = getIntent().getExtras();
        p = (Parametros) bundle.getSerializable("ronda3");
        //txt_dummy.setText(String.valueOf(p.getPreciomedio()));

        //calcular gasto electrico
        bguardarconsumohogar = findViewById(R.id.bguardarconsumohogar);
        bcalcularconsumohogar = findViewById(R.id.bcalcularconsumohogar);
        bcalcularconsumohogar.setVisibility(View.VISIBLE);
        bcalcularconsumohogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calculo
                DecimalFormat formateador = new DecimalFormat("####.###");
                DecimalFormat formateador_corto = new DecimalFormat("####.#");
                int lavavajillas=0;
                int nevera=0;
                int vitroceramica=0;
                int congelador =0;
                int lavadora = 0;
                int horno =0;
                int radiador= 0;
                int aspiradora=0;
                int microondas =0;
                int television=0;
                int bombilla_normal=0;
                int bombilla_led=0;
                int portatil = 0;
                int pc = 0;
                int videoconsola =0;
                int equipo_sonido=0;
                consumo_diario=0;
                gasto_diario=0;
                for (Electrodomestico e: electrodomesticos) {
                    switch (e.getTipo_electrodomestico()){
                        case"lavavajillas":
                            lavavajillas++;
                            break;
                        case"nevera":
                            nevera++;
                            break;
                        case"vitroceramica":
                            vitroceramica++;
                            break;
                        case"congelador":
                            congelador++;
                            break;
                        case"lavadora":
                            lavadora++;
                            break;
                        case"horno":
                            horno++;
                            break;
                        case"radiador":
                            radiador++;
                            break;
                        case"aspiradora":
                            aspiradora++;
                            break;
                        case"microondas":
                            microondas++;
                            break;
                        case"television":
                            television++;
                            break;
                        case"bombilla_normal":
                            bombilla_normal++;
                            break;
                        case"bombilla_led":
                            bombilla_led++;
                            break;
                        case"portatil":
                            portatil++;
                            break;
                        case"pc":
                            pc++;
                            break;
                        case"videoconsola":
                            videoconsola++;
                            break;
                        case"equipo_sonido":
                            equipo_sonido++;
                            break;
                    }
                    consumo_diario += e.consumoDiario();
                    //calculado a partir del precio medio del dia.
                    gasto_diario += e.gastoDiario(p.getPreciomedio());
                }

                String salida="";
                //pintar
                if(lavavajillas > 0)
                    salida += "(" + lavavajillas + ") lavavajillas \n";
                if(nevera > 0)
                    salida += "(" + nevera + ") nevera \n";
                if(vitroceramica > 0)
                    salida += "(" + vitroceramica + ") vitroceramica \n";
                if(congelador > 0)
                    salida += "(" + congelador  + ") congelador \n";
                if(lavadora > 0)
                    salida += "(" + lavadora  + ") lavadora \n";
                if(horno > 0)
                    salida += "(" + horno  + ") horno \n";
                if(radiador > 0)
                    salida += "(" + radiador  + ") radiador \n";
                if(aspiradora > 0)
                    salida += "(" + aspiradora + ") aspiradora \n";
                if(microondas > 0)
                    salida += "(" + microondas + ") microondas \n";
                if(television > 0)
                    salida += "(" + television + ") television \n";
                if(bombilla_normal > 0)
                    salida += "(" + bombilla_normal + ") bombilla normal \n";
                if(bombilla_led > 0)
                    salida += "(" + bombilla_led + ") bombilla led \n";
                if(portatil > 0)
                    salida += "(" + portatil + ") portatil \n";
                if(pc > 0)
                    salida += "(" + pc + ") PC \n";
                if(videoconsola > 0)
                    salida += "(" + videoconsola + ") Videoconsola \n";
                if(equipo_sonido > 0)
                    salida += "(" + equipo_sonido + ") Equipo Sonido \n";
                //pintar nº electrodomesticos encendidos, consumo diario y gasto diario.
                // mostrar / esconder botones calcularconsumohogar, recargarconsumohogar, guardarconsumohogar.
                txt_electrodomesticos_encendidos.append( salida);
                txt_consumo_diario.append(formateador_corto.format(consumo_diario) + " Kwh");
                txt_gasto_diario.append( (formateador.format(gasto_diario)) + " €");
                txt_precio_medio.append(formateador.format(p.getPreciomedio()) + "€ Kwh");
                txt_dia.append(p.getDia());
                txt_tarifa.append((p.getZona()));
                bcalcularconsumohogar.setVisibility(View.GONE);
                brecargarconsumohogar.setVisibility(View.VISIBLE);
                //comprobar q el usr ha clickeado algun electrodomestico antes de guardarlo.
                if(consumo_diario > 0 && gasto_diario >0)
                bguardarconsumohogar.setVisibility(View.VISIBLE);
            }
        });

        // recargar ventana
        brecargarconsumohogar = findViewById(R.id.brecargarconsumohogar);
        brecargarconsumohogar.setVisibility(View.GONE);
        brecargarconsumohogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recargarVentana_Calcular_Gasto_Electrico();
            }
        });

        // guardar gasto electrico en la bd local
        bguardarconsumohogar.setVisibility(View.GONE);
        bguardarconsumohogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat formateador = new DecimalFormat("####.###");
                DecimalFormat formateador_corto = new DecimalFormat("####.#");
                BaseDatosHelper bdhelper  = new BaseDatosHelper(getApplicationContext(),"MI_GASTO_ELECTRICO",null,1);
                db = bdhelper.getWritableDatabase();

                    LocalDate fecha=LocalDate.now();
                    if(p.getDia().equals("Hoy")){
                        fecha=LocalDate.now();
                    }else if(p.getDia().equals("Ayer")){
                        fecha=LocalDate.now().minusDays(1);
                    }else if(p.getDia().equals("Mañana")){
                        fecha =  LocalDate.now().plusDays(1);
                    }

                if(db != null){
                    ContentValues nuevoreg =  new ContentValues();
                    nuevoreg.put("dia", fecha.format(DateTimeFormatter.ofPattern("d/M/yyyy")));
                    nuevoreg.put("tarifa", p.getZona());
                    nuevoreg.put("electrodomesticos_encendidos", electrodomesticos.size());
                    nuevoreg.put("consumo_diario", Math.round(consumo_diario*100.0)/100.0);
                    nuevoreg.put("gasto_diario",  Math.round(gasto_diario*100.0)/100.0);
                    nuevoreg.put("precio_medio_dia", p.getPreciomedio());

                    //si s ha guardado correctamente , s notifica, y escondemos el boton de guardar.
                    if(db.insert("GASTO_ELECTRICO", null,nuevoreg) > 0){
                        System.out.println("INSERTADO");
                        bguardarconsumohogar.setVisibility(View.INVISIBLE);
                        Toast mensaje = new Toast(v.getContext());

                        LayoutInflater inflater = getLayoutInflater();

                        View layout = inflater.inflate(R.layout.toast_personalizado,
                                (ViewGroup) findViewById(R.id.tostada));

                        mensaje.setDuration(Toast.LENGTH_LONG);
                        mensaje.setGravity(Gravity.CENTER,0,0);
                        mensaje.setView(layout);
                        mensaje.show();

                    } else{
                        System.out.println("OH SHIT");
                    }

                }
            }
        });

        //referenciar controles y añadir listeners
        rowControlsCharger(R.id.txt_uds_11,R.id.b_add_electr_14,R.id.txt_pot_13,0, R.id.txt_tiempo_14, R.id.b_incr_14, R.id.b_dcr_14,R.id.txt_nombre_electr_12,1);
        rowControlsCharger(R.id.txt_uds_21,R.id.b_add_electr_24,R.id.txt_pot_23,0, R.id.txt_tiempo_24, 0, 0,R.id.txt_nombre_electr_22,2);
        rowControlsCharger(R.id.txt_uds_31,R.id.b_add_electr_34,R.id.txt_pot_33,R.id.rbg_pot_33, R.id.txt_tiempo_34, R.id.b_incr_34, R.id.b_dcr_34,R.id.txt_nombre_electr_32,3);
        rowControlsCharger(R.id.txt_uds_41,R.id.b_add_electr_44,R.id.txt_pot_43,0, R.id.txt_tiempo_44, 0, 0,R.id.txt_nombre_electr_42,4);
        rowControlsCharger(R.id.txt_uds_51,R.id.b_add_electr_54,R.id.txt_pot_53,R.id.rbg_pot_53, R.id.txt_tiempo_54, R.id.b_incr_54, R.id.b_dcr_54,R.id.txt_nombre_electr_52,5);
        rowControlsCharger(R.id.txt_uds_61,R.id.b_add_electr_64,R.id.txt_pot_63,R.id.rbg_pot_63, R.id.txt_tiempo_64, R.id.b_incr_64, R.id.b_dcr_64,R.id.txt_nombre_electr_62,6);
        rowControlsCharger(R.id.txt_uds_71,R.id.b_add_electr_74,R.id.txt_pot_73,R.id.rbg_pot_73, R.id.txt_tiempo_74, R.id.b_incr_74, R.id.b_dcr_74,R.id.txt_nombre_electr_72,7);
        rowControlsCharger(R.id.txt_uds_81,R.id.b_add_electr_84,R.id.txt_pot_83, 0,R.id.txt_tiempo_84, R.id.b_incr_84, R.id.b_dcr_84,R.id.txt_nombre_electr_82,8);
        rowControlsCharger(R.id.txt_uds_91,R.id.b_add_electr_94,R.id.txt_pot_93, 0,R.id.txt_tiempo_94, R.id.b_incr_94, R.id.b_dcr_94,R.id.txt_nombre_electr_92,9);
        rowControlsCharger(R.id.txt_uds_101,R.id.b_add_electr_104,R.id.txt_pot_103, 0,R.id.txt_tiempo_104, R.id.b_incr_104, R.id.b_dcr_104,R.id.txt_nombre_electr_102,10);
        rowControlsCharger(R.id.txt_uds_111,R.id.b_add_electr_114,R.id.txt_pot_113, 0,R.id.txt_tiempo_114, R.id.b_incr_114, R.id.b_dcr_114,R.id.txt_nombre_electr_112,11);
        rowControlsCharger(R.id.txt_uds_121,R.id.b_add_electr_124,R.id.txt_pot_123, 0,R.id.txt_tiempo_124, R.id.b_incr_124, R.id.b_dcr_124,R.id.txt_nombre_electr_122,12);
        rowControlsCharger(R.id.txt_uds_131,R.id.b_add_electr_134,R.id.txt_pot_133, 0,R.id.txt_tiempo_134, R.id.b_incr_134, R.id.b_dcr_134,R.id.txt_nombre_electr_132,13);
        rowControlsCharger(R.id.txt_uds_141,R.id.b_add_electr_144,R.id.txt_pot_143, 0,R.id.txt_tiempo_144, R.id.b_incr_144, R.id.b_dcr_144,R.id.txt_nombre_electr_142,14);
        rowControlsCharger(R.id.txt_uds_151,R.id.b_add_electr_154,R.id.txt_pot_153, 0,R.id.txt_tiempo_154, R.id.b_incr_154, R.id.b_dcr_154,R.id.txt_nombre_electr_152,15);
        rowControlsCharger(R.id.txt_uds_161,R.id.b_add_electr_164,R.id.txt_pot_163, 0,R.id.txt_tiempo_164, R.id.b_incr_164, R.id.b_dcr_164,R.id.txt_nombre_electr_162,16);

    }

    public void rowControlsCharger(int id_uds, int id_add_electr, int id_pot, int id_rbg_pot, int id_tiempo, int id_incr, int id_dcr, int id_nombre_electr, int row ){

        //referencias a los controles
        TextView txt_uds = findViewById(id_uds);
        ImageButton b_add_electr = findViewById(id_add_electr);
        TextView txt_pot = findViewById(id_pot);
        TextView txt_tiempo = findViewById(id_tiempo) ;
        ImageButton b_incr = findViewById(id_incr);
        ImageButton b_dcr = findViewById(id_dcr);
        TextView txt_nombre_electr = findViewById(id_nombre_electr);
        RadioGroup rbg = findViewById(id_rbg_pot);

        switch (row){
            case 1:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                b_incr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double incr = Double.parseDouble(txt_tiempo.getText().toString()) + 0.5;
                        txt_tiempo.setText(String.valueOf(incr));
                    }
                });
                b_dcr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Double.parseDouble(txt_tiempo.getText().toString()) > 0){
                            double dcr = Double.parseDouble(txt_tiempo.getText().toString()) - 0.5;
                            txt_tiempo.setText(String.valueOf(dcr));
                        }
                    }
                });
                b_add_electr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Electrodomestico e = new Electrodomestico();
                        e.setTipo_electrodomestico(txt_nombre_electr.getText().toString());
                        e.setPotencia(Double.parseDouble(txt_pot.getText().toString()));
                        e.setHoras(Double.parseDouble(txt_tiempo.getText().toString()));
                        electrodomesticos.add(e);
                        int uds = Integer.parseInt(txt_uds.getText().toString()) + 1;
                        txt_uds.setText(String.valueOf(uds));
                    }
                });
                break;
                //nevera y arcon, no tienen botones ya q estan puestos all dias.
            case 2:
            case 4:
                b_add_electr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Electrodomestico e = new Electrodomestico();
                        e.setTipo_electrodomestico(txt_nombre_electr.getText().toString());
                        e.setPotencia(Double.parseDouble(txt_pot.getText().toString()));
                        e.setHoras(Double.parseDouble(txt_tiempo.getText().toString()));
                        electrodomesticos.add(e);
                        int uds = Integer.parseInt(txt_uds.getText().toString()) + 1;
                        txt_uds.setText(String.valueOf(uds));
                    }
                });
                break;
                //vitro, lavadora, horno, radiador se puede establecer la potencia utilizada.
            case 3:
            case 5:
            case 6:
            case 7:
                b_incr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double incr = Double.parseDouble(txt_tiempo.getText().toString()) + 0.5;
                        txt_tiempo.setText(String.valueOf(incr));
                    }
                });
                b_dcr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Double.parseDouble(txt_tiempo.getText().toString()) > 0){
                            double dcr = Double.parseDouble(txt_tiempo.getText().toString()) - 0.5;
                            txt_tiempo.setText(String.valueOf(dcr));
                        }
                    }
                });
                b_add_electr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Electrodomestico e = new Electrodomestico();
                        e.setTipo_electrodomestico(txt_nombre_electr.getText().toString());
                        RadioButton op = findViewById(rbg.getCheckedRadioButtonId());
                        e.setPotencia(Double.parseDouble(op.getText().toString()));
                        e.setHoras(Double.parseDouble(txt_tiempo.getText().toString()));
                        electrodomesticos.add(e);
                        int uds = Integer.parseInt(txt_uds.getText().toString()) + 1;
                        txt_uds.setText(String.valueOf(uds));
                    }
                });
                break;
        }
    }


    //reload activity
    public void recargarVentana_Calcular_Gasto_Electrico(){
        Intent refresh = new Intent(this,Ventana_Calcular_Gasto_Electrico.class);
        refresh.putExtra("ronda3", p);
        startActivity(refresh);
        this.finish();
    }


}