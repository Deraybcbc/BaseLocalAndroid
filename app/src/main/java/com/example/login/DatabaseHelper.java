package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AndroidBase";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla CLIENTE
        db.execSQL("CREATE TABLE IF NOT EXISTS CLIENTE (" +
                "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_cliente TEXT," +
                "apellido TEXT," +
                "direccion VARCHAR(100)," +
                "genero VARCHAR(100)," +
                "pais VARCHAR(100)," +
                "telefono VARCHAR(15)," +
                "fecha DATE UNIQUE," +
                "fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertarCliente(String nombre, String apellido, String direccion, String genero, String Pais, String telefono, String fecha) {
        try {
            SQLiteDatabase db = this.getWritableDatabase(); // Obtener una instancia de base de datos escribible

            ContentValues values = new ContentValues();
            values.put("nombre_cliente", nombre);
            values.put("apellido", apellido);
            values.put("apellido", apellido);
            values.put("direccion", direccion);
            values.put("genero", genero);
            values.put("pais", Pais);
            values.put("telefono", telefono);
            values.put("fecha", fecha);

            long idCliente = db.insert("CLIENTE", null, values);

            if (idCliente != -1) {
                // La inserción fue exitosa
                // "idCliente" contiene el valor de la clave primaria del nuevo cliente
                Log.d("INSERT", "INSERT EXITOSO");
            } else {
                // La inserción falló
                Log.d("INSERT", "INSERT FALLIOD");
            }
        } catch (SQLException e) {
            // Manejar excepciones, por ejemplo, si hay un error de base de datos
            e.printStackTrace();
        }
    }

    public List<Clientes> SelectFiltro(int id) {

        List<Clientes> clientes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // Definir las columnas que deseas recuperar
        String[] datos = {
                "id_cliente",
                "nombre_cliente",
                "apellido",
                "direccion",
                "genero",
                "pais",
                "telefono",
                "fecha",
                "fechaRegistro",
        };

        // Especificar la condición WHERE con el ID del cliente
        String filtro = "id_cliente = ?";
        String[] selects = {String.valueOf(id)};

        // Realizar la consulta
        Cursor cursor = db.query(
                "CLIENTE", // Nombre de la tabla
                datos,          // Columnas que deseas recuperar
                filtro,         // Cláusula WHERE
                selects,        // Argumentos de la cláusula WHERE
                null, null, null
        );

        // Verificar si se obtuvieron resultados
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Obtener los datos del cursor
                int idCli = cursor.getInt(cursor.getColumnIndexOrThrow("id_cliente"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre_cliente"));
                String apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido"));
                String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
                String genero = cursor.getString(cursor.getColumnIndexOrThrow("genero"));
                String pais = cursor.getString(cursor.getColumnIndexOrThrow("pais"));
                String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                String fechaRegistro = cursor.getString(cursor.getColumnIndexOrThrow("fechaRegistro"));

                // Crear y devolver un objeto Clientes con los datos obtenidos
                clientes.add(new Clientes(idCli, nombre, apellido, direccion, genero, pais, telefono, fecha, fechaRegistro));
            } while (cursor.moveToNext());
        }

        // Cerrar el cursor después de utilizarlo
        if (cursor != null) {
            cursor.close();
        }

        // Devolver null si no se encontraron resultados
        return clientes;
    }

    public List<Clientes> SelectAll() {
        List<Clientes> clientes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // Definir las columnas que deseas recuperar
        String[] datos = {
                "id_cliente",
                "nombre_cliente",
                "apellido",
                "direccion",
                "genero",
                "pais",
                "telefono",
                "fecha",
                "fechaRegistro",
        };

        // Realizar la consulta
        Cursor cursor = db.query(
                "CLIENTE", // Nombre de la tabla
                datos,          // Columnas que deseas recuperar
                null,         // Cláusula WHERE
                null,        // Argumentos de la cláusula WHERE
                null, null, null
        );

        if(cursor != null && cursor.moveToFirst()){
            do{
                // Obtener los datos del cursor
                int idCli = cursor.getInt(cursor.getColumnIndexOrThrow("id_cliente"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre_cliente"));
                String apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido"));
                String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
                String genero = cursor.getString(cursor.getColumnIndexOrThrow("genero"));
                String pais = cursor.getString(cursor.getColumnIndexOrThrow("pais"));
                String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                String fechaRegistro = cursor.getString(cursor.getColumnIndexOrThrow("fechaRegistro"));

                // Crear y devolver un objeto Clientes con los datos obtenidos
                clientes.add(new Clientes(idCli, nombre, apellido, direccion, genero, pais, telefono, fecha, fechaRegistro));
            }while (cursor.moveToNext());
        }

        if(cursor != null){
            cursor.close();
        }

        return clientes;
    }

}
