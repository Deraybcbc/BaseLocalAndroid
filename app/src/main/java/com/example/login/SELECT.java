package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SELECT extends AppCompatActivity {

    private RecyclerView recyclerViewClientes;
    private RecyclerViewAdaptadorClientes adaptadorClientes;
    private DatabaseHelper databaseHelper;
    private List<Clientes> clientes;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        //MENU
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtener una instancia de la base de datos
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        FloatingActionButton boton = (FloatingActionButton) findViewById(R.id.FiltrarID);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Buscarid = (EditText) findViewById(R.id.BuscarID);
                String idTexto = Buscarid.getText().toString();
                System.out.println("ID: " + idTexto);
                if (!idTexto.isEmpty()) {
                    int id = Integer.parseInt(idTexto);

                    clientes = (List<Clientes>) dbHelper.SelectFiltro(id);

                    if(!clientes.isEmpty()){
                        //Para mostrar los Productos con el RecyclerView
                        recyclerViewClientes = (RecyclerView) findViewById(R.id.Form);
                        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(SELECT.this));
                        adaptadorClientes = new RecyclerViewAdaptadorClientes(clientes);
                        recyclerViewClientes.setAdapter(adaptadorClientes);
                    }else{
                        Toast.makeText(SELECT.this, "No hay ningun usuario con ese ID", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(SELECT.this, "Ingrese un id", Toast.LENGTH_SHORT).show();
                }

            }
        });

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