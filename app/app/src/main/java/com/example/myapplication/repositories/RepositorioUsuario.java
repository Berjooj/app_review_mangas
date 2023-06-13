package com.example.myapplication.repositories;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import com.example.myapplication.models.Usuario;

public class RepositorioUsuario {

    private static RepositorioUsuario instance;
    private Usuario usuario;





    public RepositorioUsuario() {
        this.usuario = new Usuario();
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
        this.usuario = usuario;
    }

    public Usuario updateUsuario(Usuario usuario){
        return usuario;
    }
}
