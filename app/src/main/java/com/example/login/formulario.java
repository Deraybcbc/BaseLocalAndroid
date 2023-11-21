package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class formulario extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner;
    private ImageButton botonCalendar;
    private TextView textoC;
    private String paisSeleccionado;
    private Button Enviar;

    private EditText nombre,direccion,telefono;
    private EditText cognom;
    private TextView fechass;
    private String nom;
    private String apellido;
    private String fechas,dire,tele;

    private RecyclerView recyclerViewClientes;
    private RecyclerViewAdaptadorClientes AdaptadorClientes;
    private SQLiteDatabase database;

    private ArrayList<Clientes> clientes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        // Obtener una instancia de la base de datos
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        //MENU
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SpinnerPais();

        botonCalendar = (ImageButton) findViewById(R.id.imagenCalendar);
        Enviar = (Button)findViewById(R.id.envio);

        textoC = (TextView) findViewById(R.id.texto);

        botonCalendar.setOnClickListener(this);

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup Generos = (RadioGroup) findViewById(R.id.generos);

                //Recoger los datos que hemos metido
                nombre = (EditText) findViewById(R.id.nom);
                cognom = (EditText) findViewById(R.id.apellido);
                fechass = (TextView) findViewById(R.id.texto);
                direccion = (EditText) findViewById(R.id.infoDir);
                telefono = (EditText) findViewById(R.id.infoTele);

                nom = nombre.getText().toString();
                apellido = cognom.getText().toString();
                fechas = fechass.getText().toString();
                dire = direccion.getText().toString();
                tele = telefono.getText().toString();

                if(Verificar(Generos)==false){
                    Toast.makeText(formulario.this,"Rellena todo",Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(formulario.this);
                    builder.setTitle("Formulario");
                    builder.setMessage("Nombre: " + nom + "\n" + "Apellido: " + apellido + "\n" + "Genero: " + OpcionSeleccionada(Generos) + "Spinner: " + paisSeleccionado + "\n" + "Fecha de Nacimiento: " + fechas+dire+tele);

                    // Agregar botones y lógica para manejar los clics
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lógica cuando el usuario hace clic en "Aceptar"
                            // Por ejemplo: realizar una acción o cerrar el cuadro de diálogo

                            //Intent intent = new Intent(formulario.this, MainActivity2.class);
                            // Iniciar la nueva actividad
                            //startActivity(intent);
                            insertarCliente(nom,apellido,dire,OpcionSeleccionada(Generos),paisSeleccionado,tele,fechas);
                            Toast.makeText(formulario.this, "CLIENTE AGREGADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lógica cuando el usuario hace clic en "Cancelar"
                            // Por ejemplo: cerrar el cuadro de diálogo
                            dialog.dismiss();
                        }
                    });

                    // Mostrar el cuadro de diálogo
                    builder.show();

                    Log.d("Nombre", nom);
                    Log.d("Apellido", apellido);
                    Log.d("Genero", OpcionSeleccionada(Generos));
                    Log.d("Spinner", paisSeleccionado);
                    Log.d("Fecha", fechas);
                    Toast.makeText(formulario.this,"Enviado",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void insertarCliente(String nombre, String apellido, String direccion,String genero,String Pais, String telefono, String fecha) {
        try {
            ContentValues values = new ContentValues();
            values.put("nombre_cliente", nombre);
            values.put("apellido", apellido);
            values.put("apellido", apellido);
            values.put("direccion", direccion);
            values.put("genero", genero);
            values.put("pais", Pais);
            values.put("telefono", telefono);
            values.put("fecha", fecha);

            long idCliente = database.insert("CLIENTE", null, values);

            if (idCliente != -1) {
                // La inserción fue exitosa
                // "idCliente" contiene el valor de la clave primaria del nuevo cliente
                Log.d("INSERT","INSERT EXITOSO");
            } else {
                // La inserción falló
                Log.d("INSERT","INSERT FALLIOD");
            }
        } catch (SQLException e) {
            // Manejar excepciones, por ejemplo, si hay un error de base de datos
            e.printStackTrace();
        }
    }

    public void SpinnerPais(){
        spinner = findViewById(R.id.paises);


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
                paisSeleccionado = listaPaises.get(position).getNom();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // En caso de que no se seleccione nada
            }
        });
    }

    private void ObtenerFecha() {
        /*
        //Configuracion del Idioma en español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());*/

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear  , int selectedMonth, int selectedDay) {
                //Para poner un 0 a los dias y meses que solo tenga un numero
                String selectedMonthStr =String.valueOf(selectedMonth+1);
                String selectedDayStr=String.valueOf(selectedDay);

                if ((selectedMonth+1)<10){
                    selectedMonthStr = "0"+selectedMonthStr;
                }
                if(selectedDay <10){
                    selectedDayStr = "0"+selectedDayStr;
                }

                // Procesa la fecha seleccionada aquí y actualiza el contenido del EditText
                String selectedDate = selectedDayStr + "/" + selectedMonthStr + "/" + selectedYear;
                textoC.setText(selectedDate);
            }
        }, day, month, year);

        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        ObtenerFecha();
    }

    public ArrayList<RadioButton> ObtenerGenero(){
        ArrayList<RadioButton> seleccionado = new ArrayList<>();
        seleccionado.add(findViewById(R.id.masc));
        seleccionado.add(findViewById(R.id.fem));
        seleccionado.add(findViewById(R.id.heli));

        return seleccionado;
    }

    public String OpcionSeleccionada(RadioGroup...grups){
        String imp ="";
        for (RadioGroup respuestas : grups){
            int idRes = respuestas.getCheckedRadioButtonId();
            RadioButton boto = (RadioButton) findViewById(idRes);
            imp = imp +boto.getText().toString()+"\n";
        }
        return imp;
    }


    public boolean Verificar (RadioGroup...grup){
        boolean result = true;
        for (RadioGroup TotalBotones : grup){
            if (TotalBotones.getCheckedRadioButtonId() == -1) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }
}