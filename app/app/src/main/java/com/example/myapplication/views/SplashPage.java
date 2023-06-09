package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.InitContext;
import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.FavoritoService;

import org.json.JSONException;

import java.time.Duration;
import java.time.LocalDateTime;

public class SplashPage extends AppCompatActivity implements InitContext {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);

        this.setInstance();

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();

        if (repositorioUsuario.getUsuario() != null) {
            mostrarLogado();
        } else {
            mostrarLandingPage();
        }
    }

    private void mostrarLandingPage() {
        Intent intent = new Intent(SplashPage.this, LandingPage.class);
        startActivity(intent);
        finish();
    }

    private void mostrarLogado() {
        ApplicationService service = ApplicationService.getInstance();

        LocalDateTime currentDate = LocalDateTime.now();

        // Exibição do resultado
        service.initApp(onServiceDone -> {
            LocalDateTime postDate = LocalDateTime.now();
            Duration duration = Duration.between(currentDate, postDate);

            long seconds = duration.getSeconds();

            Log.wtf("Tempo de busca", seconds + " segundos.");

            this.initHomeActivity();
        }, error -> {
            this.retry();
        });
    }

    private void retry() {
        finish();

        Intent intentSplashPage = new Intent(getApplicationContext(), SplashPage.class);
        intentSplashPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentSplashPage);
    }

    private void initHomeActivity() {
        Intent intent = new Intent(SplashPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setInstance() {
        ApplicationService service = ApplicationService.getInstance();
        service.loader = new LoadingDialog(SplashPage.this);
        service.setContext(this);
    }
}