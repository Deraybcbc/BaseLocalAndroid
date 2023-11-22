package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class SelectAll extends AppCompatActivity {

    private RecyclerView recyclerViewAll;
    private RecyclerViewAdaptadorAll adaptadorAll;

    private List<Clientes> clientes;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_all);

        //MENU
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtener una instancia de la base de datos
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        clientes = dbHelper.SelectAll();

        recyclerViewAll = (RecyclerView) findViewById(R.id.All);
        recyclerViewAll.setLayoutManager(new LinearLayoutManager(this));
        adaptadorAll = new RecyclerViewAdaptadorAll(clientes);
        recyclerViewAll.setAdapter(adaptadorAll);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //ACCION DE FLECHA
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Mostrar y enviar a donde queremos ir
        switch (id) {
            case R.id.Formu:
                // Acción para la "Opción 1"
                Intent intent1 = new Intent(this, formulario.class);
                startActivity(intent1);
                return true;
            case R.id.Select:
                // Acción para la "Opción 1"
                Intent intent2 = new Intent(this, SELECT.class);
                startActivity(intent2);
                return true;
            case R.id.SelectAll:
                // Acción para la "Opción 1"
                Intent intent3 = new Intent(this, SelectAll.class);
                startActivity(intent3);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}