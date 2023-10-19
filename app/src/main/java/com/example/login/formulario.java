package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class formulario extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        SpinnerPais();
    }

    public void SpinnerPais(){
        spinner = findViewById(R.id.paises);
        TextView texto = (TextView) findViewById(R.id.txt);


        ArrayList<Paises> listaPaises = new ArrayList<Paises>();

        listaPaises.add(new Paises("España"));
        listaPaises.add(new Paises("China"));
        listaPaises.add(new Paises("Holanda"));
        listaPaises.add(new Paises("Noruega"));
        listaPaises.add(new Paises("Bélgica"));
        listaPaises.add(new Paises("Hungría"));
        listaPaises.add(new Paises("Argentina"));

        ArrayAdapter<Paises> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listaPaises);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtiene el país seleccionado del Spinner
                String paisSeleccionado = listaPaises.get(position).getNom();

                // Actualiza el TextView con el país seleccionado
                texto.setText(paisSeleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // En caso de que no se seleccione nada
            }
        });
    }
}