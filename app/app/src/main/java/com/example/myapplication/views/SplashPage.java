package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.ServicosUsuario;

public class SplashPage extends AppCompatActivity {
    RepositorioUsuario repoUsuario;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);
        this.repoUsuario = RepositorioUsuario.getInstance();
        ApplicationService.context = this;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Usuario u = repoUsuario.getUsuario();
                if (u == null) {
                    mostrarLandingPage();
                } else {
                    int id = pref.getInt("id", 0);
                    String nome = pref.getString("nome", null);
                    String email = pref.getString("email", null);
                    String senha = pref.getString("senha", null);
                    int idFotoPerfil = pref.getInt("id_foto_perfil", 0);
                    String token = pref.getString("token", null);
                    repoUsuario.setUsuario(new Usuario(id, nome, email, senha, idFotoPerfil, token));

                    mostrarLogado();
                }
            }
        }, 2000);
    }

    private void mostrarLandingPage() {
        Intent intent = new Intent(
                SplashPage.this, LandingPage.class
        );
        startActivity(intent);
        finish();
    }

    private void mostrarLogado() {
        Intent intent = new Intent(
                SplashPage.this, HomePage.class
        );
        startActivity(intent);
        finish();
    }
}