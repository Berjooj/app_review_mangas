package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.InitContext;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.ApplicationService;

public class SplashPage extends AppCompatActivity implements InitContext {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);

        this.setInstance();

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();

        if (repositorioUsuario.getUsuario() != null) {
            mostrarLandingPage();
        } else {
            mostrarLogado();
        }
    }

    private void mostrarLandingPage() {
        Intent intent = new Intent(SplashPage.this, LandingPage.class);
        startActivity(intent);
        finish();
    }

    private void mostrarLogado() {
        Intent intent = new Intent(SplashPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setInstance() {
        ApplicationService service = ApplicationService.getInstance();
        service.setContext(this);
    }
}