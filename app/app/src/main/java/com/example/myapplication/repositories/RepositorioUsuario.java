package com.example.myapplication.repositories;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.models.Usuario;
import com.example.myapplication.services.ApplicationService;
import com.google.gson.reflect.TypeToken;

public class RepositorioUsuario {

    private static RepositorioUsuario instance;
    private ApplicationService appService;
    private Usuario usuario;

    public RepositorioUsuario() {
        this.usuario = new Usuario();

        this.appService = ApplicationService.getInstance();

        SharedPreferences pref = this.appService.getContext().getSharedPreferences("AuthUser", MODE_PRIVATE);

        if (pref.contains("UserData")) {
            try {
                this.usuario = appService.gson.fromJson(
                        pref.getString("UserData", null),
                        new TypeToken<Usuario>() {
                        }.getType()
                );
            } catch (Exception exception) {
                Toast.makeText(this.appService.getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("UserData", exception.getMessage());
                this.usuario = null;
            }
        } else {
            this.usuario = null;
        }
    }

    public static RepositorioUsuario getInstance() {
        if (instance == null) {
            instance = new RepositorioUsuario();
        }

        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.appService = ApplicationService.getInstance();

        SharedPreferences pref = this.appService.getContext().getSharedPreferences("AuthUser", MODE_PRIVATE);

        pref.edit().putString("UserData", this.appService.gson.toJson(usuario)).apply();

        this.usuario = usuario;
    }

    public Usuario updateUsuario(Usuario usuario) {
        return usuario;
    }

    public void logout() {
        SharedPreferences pref = this.appService.getContext().getSharedPreferences("AuthUser", MODE_PRIVATE);

        pref.edit().clear().apply();
    }
}
