package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button activityCadastro = findViewById(R.id.cadastroId);
        activityCadastro.setOnClickListener(view -> {
            Intent intentCadastro = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(intentCadastro);
        });

        TextView activityLogin = findViewById(R.id.loginId);
        activityLogin.setOnClickListener(view ->{
            Intent intentLogin = new Intent(getApplicationContext(), MainActivity3.class);
            startActivity(intentLogin);
        });
    }
}