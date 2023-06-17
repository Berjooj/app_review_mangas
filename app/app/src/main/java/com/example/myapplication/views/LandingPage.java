package com.example.myapplication.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.InitContext;
import com.example.myapplication.services.ApplicationService;

public class LandingPage extends AppCompatActivity implements InitContext {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        this.setInstance();

        Button activityCadastro = findViewById(R.id.cadastroId);
        activityCadastro.setOnClickListener(view -> {
            Intent intentCadastro = new Intent(getApplicationContext(), Cadastro.class);
            startActivity(intentCadastro);
        });

        TextView activityLogin = findViewById(R.id.loginId);
        activityLogin.setOnClickListener(view -> {
            Intent intentLogin = new Intent(getApplicationContext(), Login.class);
            startActivity(intentLogin);
        });
    }

    @Override
    public void setInstance() {
        ApplicationService service = ApplicationService.getInstance();
        service.loader = new LoadingDialog(LandingPage.this);
        service.setContext(this);
    }
}