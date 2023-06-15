package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.InitContext;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.FavoritoService;

import org.json.JSONException;

public class SplashPage extends AppCompatActivity implements InitContext {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);

        this.setInstance();

        Handler handler = new Handler();

        handler.postDelayed(() -> {
            RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();

            if (repositorioUsuario.getUsuario() != null) {
                mostrarLogado();
            } else {
                mostrarLandingPage();
            }
        }, 2000);
    }

    private void mostrarLandingPage() {
        Intent intent = new Intent(SplashPage.this, LandingPage.class);
        startActivity(intent);
        finish();
    }

    private void mostrarLogado() {
        RepositorioFavoritos repositorioFavoritos = RepositorioFavoritos.getInstance();

        if (repositorioFavoritos.getObras().size() == 0) {
            FavoritoService.getFavoritos(
                    onSuccess -> {
                        this.initHomeActivity();
                    },
                    onError -> {
                        Toast.makeText(this, "Erro ao carregar a lista de favoritos", Toast.LENGTH_SHORT).show();
                    }
            );
        } else {
            this.initHomeActivity();
        }
    }

    private void initHomeActivity() {
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