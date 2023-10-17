package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boto = (Button) findViewById(R.id.Iniciar);

        boto.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        EditText user = (EditText) findViewById(R.id.User);

        String users = user.getText().toString();

        Intent intent = new Intent(this, MainActivity2.class);

        intent.putExtra("Usuario",users);

        startActivity(intent);
    }
}