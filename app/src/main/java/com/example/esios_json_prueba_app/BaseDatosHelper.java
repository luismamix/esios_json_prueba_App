package com.example.esios_json_prueba_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatosHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE GASTO_ELECTRICO (gasto_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    "dia DATE , " +
                                                    "tarifa TEXT, " +
                                                    "electrodomesticos_encendidos INTEGER, " +
                                                    "consumo_diario NUMERIC, " +
                                                    "gasto_diario NUMERIC," +
                                                    "precio_medio_dia NUMERIC )";

    //Contexto : mi app.
    //name: nombre de la bd.
    //factory: familia de la bd.
    //version: version de la bd.
    public BaseDatosHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //SQLiteDatabase db: La bd q acabamos de crear.
    public void onCreate(SQLiteDatabase db) {
        //Solo se ejecuta la primera vez q se crea la base de datos
        //Se ejecuta la sentencia SQL de creación de la tabla.

        // Traga cualquier sentencia sql del ANSI SQL del año 96.
        // execSQL(sqlCONSULTA);consultas de accion o de creacion INSERT, UPDATE, DELETE.
        //String sentencia = "CREATE TABLE Cursos (id_cod_curso INTEGER PRIMARY KEY, nombre TEXT)";

        //db.execSQL(sqlCreate);
        //String sentencia = "CREATE TABLE Cursos (id_cod_curso INTEGER PRIMARY KEY, nombre TEXT)";
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.
        //Se elimina la versión anterior de la tabla

        db.execSQL("DROP TABLE IF EXISTS DEPT");

        //Se crea la nueva versión de la tabla
        // execSQL(sqlCONSULTA);consultas de accion o de creacion INSERT, UPDATE, DELETE, CREATE.
        db.execSQL(sqlCreate);
    }
}
