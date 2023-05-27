package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        Button activityCadastro = findViewById(R.id.cadastroId);
        activityCadastro.setOnClickListener(view -> {
            Intent intentCadastro = new Intent(getApplicationContext(), Cadastro.class);
            startActivity(intentCadastro);
        });

        TextView activityLogin = findViewById(R.id.loginId);
        activityLogin.setOnClickListener(view ->{
            Intent intentLogin = new Intent(getApplicationContext(), Login.class);
            startActivity(intentLogin);
        });
    }
}