package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SELECT extends AppCompatActivity {

    private RecyclerView recyclerViewClientes;
    private RecyclerViewAdaptadorClientes adaptadorClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);


        //Para mostrar los Productos con el RecyclerView
        recyclerViewClientes = (RecyclerView) findViewById(R.id.Form);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(SELECT.this));
        adaptadorClientes = new RecyclerViewAdaptadorClientes();
        recyclerViewClientes.setAdapter(adaptadorClientes);

    }
}