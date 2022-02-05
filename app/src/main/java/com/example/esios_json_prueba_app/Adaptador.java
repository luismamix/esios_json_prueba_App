package com.example.esios_json_prueba_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.Item>{
    private List<String> dias;
    private int posicion_seleccionada =-1;
    private SQLiteDatabase db;

    public Adaptador(List<String> dias) {
        this.dias = dias;
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_datos, parent, false);
        Item item = new Item(v);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {

        if(position == 0){
            //comprobar si hay datos guardados. Sino los hay hace desaparecer el  boton "Datos Guardados"
            BaseDatosHelper bdhelper  = new BaseDatosHelper(holder.itemView.getContext(),"MI_GASTO_ELECTRICO",null,1);
            db = bdhelper.getReadableDatabase();
            Cursor c =db.rawQuery("SELECT COUNT(*) FROM GASTO_ELECTRICO", null);
            c.moveToFirst();
            int count = c.getInt(0);
            System.out.println( "Gastos guardados: " + count);
            if( count > 0){
                holder.itemView.setVisibility(View.VISIBLE);
            } else{
                holder.itemView.setVisibility(View.GONE);
            }
            c.close();
        }


        holder.textDia.setText(dias.get(position));
        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicion_seleccionada = position;
                notifyDataSetChanged();
            }
        });

        if(posicion_seleccionada == position){
            holder.imgDia.setImageResource(R.drawable.ic_menu_alarm);
            holder.textDia.setTextColor(Color.parseColor("#C7F68E"));
            Parametros parametros = new Parametros();
            parametros.setDia(holder.textDia.getText().toString());
            if(holder.textDia.getText().toString().equals("Gastos guardados")){
                Intent i = new Intent(holder.itemView.getContext(),Ventana_Mostrar_Gastos_guardados.class);
                holder.itemView.getContext().startActivity(i);
            } else{
                Intent i = new Intent(holder.itemView.getContext(),Ventana_seleccionar_tarifa.class);
                i.putExtra("ronda1", parametros);
                holder.itemView.getContext().startActivity(i);
            }

        }else{
            holder.imgDia.setImageResource(R.drawable.ic_grey_menu_alarm);
            holder.textDia.setTextColor(Color.parseColor("#FFEBEFE6"));
        }

    }

    @Override
    public int getItemCount() {
        return dias.size();
    }

    public static class Item extends RecyclerView.ViewHolder {

        private TextView textDia;
        private ImageView imgDia;
        private CardView cardView1;

        public Item(@NonNull View itemView) {
            super(itemView);
            textDia = itemView.findViewById(R.id.txt_calcular_consumo_hogar);
            imgDia = itemView.findViewById(R.id.imgDia);
            cardView1 = itemView.findViewById(R.id.bconsumohogar);

        }
    }
}
